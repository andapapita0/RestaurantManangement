package presentation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.MenuItem;
import businessLayer.Order;

@SuppressWarnings("serial")
public class WaiterGUI extends JPanel {
	private JTable ordersTable = new JTable();
	private JButton b1 = new JButton("   Create new order   ");
	private JButton b2 = new JButton("Add menu items to order");
	private JButton b3 = new JButton(" Compute total price  ");
	private JButton b4 = new JButton("    Genereate bill    ");
	private JButton b5 = new JButton("    View all orders   ");
	private JTextField t1 = new JTextField("   table nr.    ");
	private JTextField t2 = new JTextField("   menu item    ");
	private JTextField t3 = new JTextField("   menu item    "); 
	private JTextField t4 = new JTextField("   table nr.    "); 
	private JLabel l1 = new JLabel();
	private JTextField t5 = new JTextField("    table nr.    "); //sau table

	public WaiterGUI() {
		
		JPanel main = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 5;
		c.gridx = 0;
		c.gridy = 0;
		
		JPanel table = new JPanel();
		table.setBorder(BorderFactory.createTitledBorder("Orders table"));
		BoxLayout bl = new BoxLayout(table, BoxLayout.Y_AXIS);
		table.setLayout(bl);
		ordersTable.setPreferredScrollableViewportSize(new Dimension(450, 63));
		ordersTable.setFillsViewportHeight(true);
		JScrollPane ne = new JScrollPane(ordersTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ne.setBounds(100,150,500,300);
		ne.setVisible(true);
		table.add(ne);
		main.add(table, c);
		
		c.insets = new Insets(10, 5, 5, 5);
		c.fill = GridBagConstraints.VERTICAL;
		c.ipady = 0;     
		c.weightx = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth=1;
		main.add(b1, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 1;
		main.add(t1, c);
		c.gridx = 2;
		c.gridy = 1;
		main.add(t2, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 2;
		main.add(b2, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 2;
		main.add(t3, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 3;
		main.add(b3, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 3;
		main.add(t4, c);
		c.gridx = 2;
		c.gridy = 3;
		main.add(l1, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 4;
		main.add(b4, c);
		c.gridx = 1;
		c.gridy = 4;
		main.add(t5, c);
		c.gridx = 0;
		c.gridy = 5;
		main.add(b5, c);
		
		this.add(main);
	}

	public void addCreateOrderListener(ActionListener view) {
		this.b1.addActionListener(view);
	}
	
	public void addAddItemListener(ActionListener view) {
		this.b2.addActionListener(view);
	}
	
	public void addComputeTotalListener(ActionListener view) {
		this.b3.addActionListener(view);
	}
	
	public void addBillListener(ActionListener view) {
		this.b4.addActionListener(view);
	}
	
	public void addViewOrdersListener(ActionListener view) {
		this.b5.addActionListener(view);
	}
	
	public String getUserInput2() {
		return t2.getText();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createOrdersTable(Hashtable<Order, ArrayList<MenuItem>> orders) {
		Vector<String> cols = new Vector<String>();
		cols.add("orderId");
		cols.add("date");
		cols.add("table");
		cols.add("menu item");
		cols.add("price");
		DefaultTableModel tm = new DefaultTableModel();
		tm.setColumnIdentifiers(cols);
		
		Set<Entry<Order, ArrayList<MenuItem>>> entrySet = orders.entrySet();
		for(Entry en: entrySet) {
			Order key =  (Order) en.getKey();
			ArrayList<MenuItem> value = (ArrayList<MenuItem>)  en.getValue();
			for(int j = 0; j < value.size(); j++) {
			
				Vector<Object> temp = new Vector<Object>();
				temp.add(key.getOrderId());
				temp.add(key.getDate());
				temp.add(key.getTable());
				temp.add(value.get(j).getMenuItem());
				temp.add(value.get(j).getPrice());
				tm.addRow(temp);
			}
		}
		this.ordersTable.setModel(tm);
	}
	
	public int getUserInput1() {
		String s = t1.getText();
		return Integer.parseInt(s);
	}
	
	public String getUserInput3() {
		String s = t3.getText();
		return s;
	}
	
	public int getUserInput4() {
		String s = t4.getText();
		return Integer.parseInt(s);
	}
	
	public int getUserInput5() {
		String s = t5.getText();
		return Integer.parseInt(s);
	}
	
	public void setLabel1(int p) {
		String s = "";
		s += p + " lei";
		this.l1.setText(s);
	}
}
