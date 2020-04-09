package businessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * this class implements the methods from the processing interface. Basically, these methods 
 * are the operations that the users of the restaurant system can perform: add menu item to 
 * the menu, create a new order etc. its attributes are the menu list, a hashtable structure 
 * that contains the order and each menu item associated to it and an object of type Observer 
 * for the chef.
 * @author Anda
 *
 */
@SuppressWarnings("serial")
public class Restaurant extends Observable implements java.io.Serializable, RestaurantProcessing  {
	
	private Hashtable<Order, ArrayList<MenuItem>> orders;
	private ArrayList<MenuItem> menu;
	private Observer chef;
	
	public Restaurant() {
		orders = new Hashtable<Order, ArrayList<MenuItem>>();
		menu = new ArrayList<MenuItem>();
	}
	
	public ArrayList<MenuItem> getMenuList(){
		return this.menu;
	}
	
	public Hashtable<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(Hashtable<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}

	public void setMenuList(ArrayList<MenuItem> list){
		this.menu = list;
	}
	
	public void registerObserver(Observer o) {
		chef = o;
	}
	
	public void notifyAllObservers(Observable observable, String msg) {
		chef.update(observable, msg);
		System.out.println();
	}
	/**
	 * this method checks if the menu list is empty or not
	 * @return truth value representing whether the menu is empty or not
	 */
	public boolean isWellFormed() {
		if(menu.size() == 0)
			return false;
		else return true;
	}
	/**
	 * adds a new menu item to the menu list
	 */
	public void addMenuItem(MenuItem menuItem) {
		assert !(menuItem == null);
		int size = menu.size();
		menu.add(menuItem);
		assert menu.size() == size + 1;
	}
	/**
	 * deletes a menu item from the menu list
	 */
	public void deleteMenuItem(MenuItem menuItem) {
		assert !(menuItem == null);
		int size = menu.size();
		menu.remove(menuItem);
		assert menu.size() == size - 1;
	}
	/**
	 * updates a menu item from the menu list
	 */
	public void editMenuItem(MenuItem m, String s, int p) {
		assert menu.contains(m);
		m.setMenuItem(s);
		m.setPrice(p);
		assert !(m == null);
	}
	/**
	 * creates a new order in the hashtable structure
	 */
	public void createNewOrder(Order o, ArrayList<MenuItem> m) {
		assert !(o == null);
		int size = orders.size();
		if(orders.contains(o) == true) {
			System.out.println("order already exists");
			return;
		}
		else {
			orders.put(o, m);
			if(CompositeProduct.class.isInstance(m)) {
				setChanged();
				notifyAllObservers(this, "New order placed!");
			}
		}
		assert orders.size() == size + 1;
	}
	/**
	 * adds a new menu item to an existent order
	 * @param o represents the order
	 * @param m represents the menu item
	 */
	public void addMenuItemtoOrder(Order o, MenuItem m) {
		assert !(m == null);
		if(orders.containsKey(o) == true) {
			orders.get(o).add(m);
		}
		else {
			ArrayList<MenuItem> list = new ArrayList<MenuItem>();
			list.add(m);
			if(CompositeProduct.class.isInstance(m)) {
				setChanged();
				notifyAllObservers(this, "New composite order placed to order!");
			}
			orders.put(o, list);
		}
	}
	/**
	 * computes the total price of an order
	 * @return the total price
	 */
	public int computePriceForOrder(Order o) {
		assert !(o == null);
		int price = 0;
		if(orders.containsKey(o) == true) {
			ArrayList<MenuItem> list = orders.get(o);
			for(int i = 0; i < list.size(); i++) {
				//System.out.println("trouble again");
				price = price + list.get(i).computePrice();
			}
		}
		else {
	
			Set<Map.Entry<Order, ArrayList<MenuItem>>> entrySet = orders.entrySet();
			Iterator<Map.Entry<Order, ArrayList<MenuItem>>> i= entrySet.iterator();
			while(i.hasNext()) {
				Map.Entry<Order, ArrayList<MenuItem>> en = i.next();
			    Order key =  (Order) en.getKey();
			    if(key.equals(o)) {
			    	ArrayList<MenuItem> value = (ArrayList<MenuItem>)  en.getValue();
			    
			    	for(int j = 0; j < value.size(); j++) {
			    		System.out.println(value.get(j).getPrice());
			    		price = price + value.get(j).computePrice();
			    	}
			    }
			}
		}
		return price;
		
	}
	/**
	 * generates the bill for an order
	 * @param o represents the order for which the bill is generated
	 */
	public void generateBill(Order o) {
		assert !(o == null);
		if(orders.containsKey(o) == true) {
		try 
        {
			File file = new File("D:\\bill.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Order number: " + o.getOrderId());
            bw.newLine();
            bw.write("Table number: " + o.getTable());
            bw.newLine();
			bw.write("Date: " + o.getDate());
			bw.newLine();
			bw.write("Total price: " + this.computePriceForOrder(o) + " lei");
			bw.newLine();
			
			ArrayList<MenuItem> list = orders.get(o);
			for(MenuItem m : list) {
				bw.write("        Dish: " + m.getMenuItem() + " X price: " + m.getPrice() + " lei");
				bw.newLine();
			}
            bw.close();
        } catch(IOException e) {
        	e.printStackTrace();
        }
		finally {
			JOptionPane.showMessageDialog(null, "Bill created successfully");
		}
		}
	}
	/**
	 * prints the menu list
	 */
	public void print() {
		for(MenuItem m: menu) {
			System.out.println(m.toString());
		}
	}
	
	public String toString() {
		String rez = "";
		Set<Map.Entry<Order, ArrayList<MenuItem>>> entrySet = orders.entrySet();
		Iterator<Map.Entry<Order, ArrayList<MenuItem>>> i= entrySet.iterator();
		while(i.hasNext()) {
			Map.Entry<Order, ArrayList<MenuItem>> en = i.next();
		    Order key =  (Order) en.getKey();
		    ArrayList<MenuItem> value = (ArrayList<MenuItem>)  en.getValue();
		    for(int j = 0; j < value.size(); j++) {
		    	rez += "Key: " + key.getDate() + key.getTable() + " Value: " + value.get(j).getMenuItem() + value.get(j).getPrice() + "\n";
		    }
		}
		return rez;
	}

	public static void main(String [] args) {
		ArrayList<MenuItem> list = new ArrayList<MenuItem>();
		Restaurant r = new Restaurant();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String d = dateFormat.format(date);
		
		MenuItem m1 = new BaseProduct("paine", 20);
		MenuItem m2 = new BaseProduct("lapte", 20);
		list.add(m1);
		list.add(m2);
		
		Order o = new Order(d, 3);
		r.createNewOrder(o, list);
		int p = r.computePriceForOrder(o);
		System.out.println(r.toString() + p);
		MenuItem m = new BaseProduct("pizza", 20);
		r.addMenuItemtoOrder(o, m);
		p = r.computePriceForOrder(o);
		System.out.println(r.toString() + p);
		
		r.generateBill(o);
	}

}
