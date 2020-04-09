package presentation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLayer.MenuItem;

/**
 * this class creates the interface the administrator works with regard to introducing and 
 * modifying the necessary and desired values in the menu and also showing the tables’ 
 * records. It extends the JTabbedPane class, as it represents a tab in the main window. Its 
 * attributes are JButton, JTable, JTextField elements.
 * @author anda
 *
 */
@SuppressWarnings("serial")
public class AdministratorGUI extends JTabbedPane {
	
	private JTable menu = new JTable();
	private JButton b1 = new JButton(" View menu items ");
	private JButton b2 = new JButton("Add new menu item");
	private JButton b3 = new JButton(" Delete menu item");
	private JButton b4 = new JButton(" Update menu item");
	private JTextField t1 = new JTextField("insert composite prod. name");
	private JLabel l1 = new JLabel("base prod.");
	private JTextField t7 = new JTextField(5);
	private JLabel l2 = new JLabel("price");
	private JTextField t2 = new JTextField(5);
	
	private JTextField t3 = new JTextField("delete by name"); 
	private JTextField t6 = new JTextField("dish to be updated");
	private JTextField t4 = new JTextField("new dish name");
	private JTextField t5 = new JTextField("new dish price");
	private JLabel l3 = new JLabel("base prod.");
	private JTextField t8 = new JTextField(5);
	private JLabel l4 = new JLabel("price");
	private JTextField t9 = new JTextField(5);
	private JLabel l5 = new JLabel("base prod.");
	private JTextField t10 = new JTextField(5);
	private JLabel l6 = new JLabel("price");
	private JTextField t11 = new JTextField(5);
	
	public AdministratorGUI() {
		this.setBounds(50,100,500,200);
		
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
		table.setBorder(BorderFactory.createTitledBorder("Menu items table"));
		BoxLayout bl = new BoxLayout(table, BoxLayout.Y_AXIS);
		table.setLayout(bl);
		menu.setPreferredScrollableViewportSize(new Dimension(450, 63));
		menu.setFillsViewportHeight(true);
		JScrollPane ne = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		main.add(b1,c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 2;
		main.add(b2, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 2;
		main.add(t1, c); //name
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 3;
		main.add(l1, c); //l1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 3;
		main.add(t7, c); //bp1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 3;
		main.add(l2, c); //l2
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 3;
		c.gridy = 3;
		main.add(t2, c); //pr1
		
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 4;
		main.add(l3, c); //l1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 4;
		main.add(t8, c); //bp1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 4;
		main.add(l4, c); //l2
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 3;
		c.gridy = 4;
		main.add(t9, c); //pr1
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 5;
		main.add(l5, c); //l1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 5;
		main.add(t10, c); //bp1
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 2;
		c.gridy = 5;
		main.add(l6, c); //l2
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 3;
		c.gridy = 5;
		main.add(t11, c); //pr1
		
		
		
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 6;
		main.add(b3, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 1;
		c.gridy = 6;
		main.add(t3, c);
		c.insets = new Insets(10, 5, 5, 5);
		c.gridx = 0;
		c.gridy = 7;
		main.add(b4, c);
		c.gridx = 1;
		c.gridy = 7;
		main.add(t6, c);
		c.gridx = 2;
		c.gridy = 7;
		main.add(t4, c);
		c.gridx = 3;
		c.gridy = 7;
		main.add(t5, c);
		
		this.addTab("Administrator", main);
		this.setMnemonicAt(0, KeyEvent.VK_1);
		
		//this.addTab("Waiter", w.main);

	}
	
	public void addViewListener(ActionListener view) {
		this.b1.addActionListener(view);
	}
	
	public void addInsertListener(ActionListener view) {
		this.b2.addActionListener(view);
	}
	
	public void addDeleteListener(ActionListener view) {
		this.b3.addActionListener(view);
	}
	
	public void addUpdateListener(ActionListener view) {
		this.b4.addActionListener(view);
	}
	
	public void createMenuTable(List<MenuItem> list) {
		String[] cols = {"dish_name", "price"};

		DefaultTableModel tm = new DefaultTableModel();
		tm.setColumnIdentifiers(cols);
		
		for(MenuItem m : list) {
			Object[] temp = new Object[cols.length];
			temp[0] = m.getMenuItem();
			temp[1] = m.getPrice();
			tm.addRow(temp);
		}
		this.menu.setModel(tm);
	}
	
	public String getUserInput1() {
		return t1.getText();
	}
	
	public String getUserInput2() {
		return t2.getText();

	}
	
	public String getUserInput3() {
		return t3.getText();
	}
	
	public String getUserInput4() {
		return t4.getText();
	}
	
	public int getUserInput5() {
		return Integer.parseInt(t5.getText());
	}
	
	public String getUserInput6() {
		return t6.getText();
	}
	
	public String getUserInput7() {
		return t7.getText();
	}
	
	public String getUserInput8() {
		return t8.getText();
	}
	
	public String getUserInput9() {
		return t9.getText();
	}
	
	public String getUserInput10() {
		return t10.getText();
	}
	
	public String getUserInput11() {
		return t11.getText();
	}

}
