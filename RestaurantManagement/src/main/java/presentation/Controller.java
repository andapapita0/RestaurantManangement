package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;

/**
 * this class acts on dataLayer, businessLayer and GUI user classes. It controls the data 
 * flow that goes into the database and updates the MainView whenever data changes and the 
 * other way around. 
 * @author anda
 *
 */
public class Controller {
	private AdministratorGUI a = new AdministratorGUI();
	private WaiterGUI w = new WaiterGUI();
	private ChefGUI c = new ChefGUI();
	private Restaurant r;
	private String d;
	private RestaurantSerializator rs;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	Order o ;
	Hashtable<Order, ArrayList<MenuItem>> orders;
	
	/**
	 * constructor of the controller
	 * @param a
	 * @param w
	 */
	public Controller(AdministratorGUI a, WaiterGUI w){
		d = dateFormat.format(date);
		o = new Order(d, 0);
		
		this.a = a;
		this.w = w;
		this.r = new Restaurant();
		r.registerObserver(c);
		orders = r.getOrders();
		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		MenuItem m1 = new BaseProduct("paine", 2);
		MenuItem m2 = new BaseProduct("guacamole", 6);
		Order o1 = new Order(d, 1);
		list.add(m1);
		list.add(m2);
		orders.put(o1, list);
		
		this.rs = new RestaurantSerializator();
		a.addViewListener(new ViewListener());
		a.addInsertListener(new InsertListener());
		a.addDeleteListener(new DeleteListener());
		a.addUpdateListener(new UpdateListener());
		
		w.addCreateOrderListener(new CreateOrderListener());
		w.addAddItemListener(new AddItemListener());
		w.addComputeTotalListener(new ComputeTotalListener());
		w.addBillListener(new BillListener());
		w.addViewOrdersListener(new ViewOrdersListener());
	}
	
	class ViewListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
			menu = rs.deserialize();
			
			r.setMenuList(menu);
			a.createMenuTable(r.getMenuList());
		}
	}
	
	class InsertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			if(!a.getUserInput7().equals("") || !a.getUserInput2().equals("")) {
				MenuItem m  = new BaseProduct(a.getUserInput7(), Integer.parseInt(a.getUserInput2()));
				list.add(m);
			}
			if(!a.getUserInput8().equals("") || !a.getUserInput9().equals("")) {
				MenuItem m1 = new BaseProduct(a.getUserInput8(), Integer.parseInt(a.getUserInput9()));
				list.add(m1);
			}
			
			if(!a.getUserInput10().equals("") || !a.getUserInput11().equals("")) {
				MenuItem m1 = new BaseProduct(a.getUserInput10(), Integer.parseInt(a.getUserInput11()));
				list.add(m1);
			}
			
			CompositeProduct cp = new CompositeProduct(a.getUserInput1(), list);
			
			r.addMenuItem(cp);
			rs.serialize(r.getMenuList());
			//r.print();
			JOptionPane.showMessageDialog(a, "Menu item inserted successfully");
		}
	}
	
	class DeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			list = r.getMenuList();
			for(int i = 0 ; i<list.size(); i++) {
				if(list.get(i).getMenuItem().equals(a.getUserInput3()))
					r.deleteMenuItem(list.get(i));
			}
			rs.serialize(list);
			
			JOptionPane.showMessageDialog(a, "Menu item deleted successfully");
		}
	}
	
	class UpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			list = r.getMenuList();
			for(int i = 0 ; i < list.size(); i++) {
				if(list.get(i).getMenuItem().equals(a.getUserInput6()))
					r.editMenuItem(list.get(i), a.getUserInput4(), a.getUserInput5());
			}
			rs.serialize(list);
			r.print();
		}
	}
	
	class CreateOrderListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int price;
			ArrayList<MenuItem> list2 = new ArrayList<MenuItem>();
			
			o.setTable(w.getUserInput1());
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			list = r.getMenuList();
			for(int i = 0 ; i < list.size(); i++) {
				if(w.getUserInput2().equals(list.get(i).getMenuItem())) {
					price = list.get(i).getPrice();
					MenuItem m = new BaseProduct(w.getUserInput2(), price);
					list2.add(m);
					r.createNewOrder(o, list2);
					System.out.println(r.toString());
					return;
				}
			}
				
			JOptionPane.showMessageDialog(w, "The desired dish is not on the menu list!");
		}
	}
	
	class AddItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			o.setTable(w.getUserInput1());
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			list = r.getMenuList();
			for(int i = 0 ; i < list.size(); i++) {
				if(w.getUserInput3().equals(list.get(i).getMenuItem())) {
					r.addMenuItemtoOrder(o, list.get(i));
					System.out.println(r.toString());
					return;
				}
			}
			JOptionPane.showMessageDialog(w, "The desired dish is not on the menu list!");
		}
	}
	
	class ComputeTotalListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			o.setTable(w.getUserInput4());
			int price = r.computePriceForOrder(o);
			w.setLabel1(price);
		}
	}
	
	class ViewOrdersListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			r.setOrders(orders);
			System.out.println(r.toString());
			w.createOrdersTable(r.getOrders());
		}
	}
	
	class BillListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			o.setTable(w.getUserInput4());
			r.generateBill(o);
		}
	}
}
