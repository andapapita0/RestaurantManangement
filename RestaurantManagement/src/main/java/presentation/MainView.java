package presentation;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

/**
 * : this class creates the interface the user works in regard to introducing the necessary 
 * and desired values in the menu and order tables, and also showing the tables’ records. It 
 * extends the JFrame class. 
 * @author anda
 *
 */
public class MainView extends JFrame {
	private AdministratorGUI a;
	private WaiterGUI w;
	private ChefGUI c;
	
	public MainView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("Restaurant");
		
		a = new AdministratorGUI();
		w = new WaiterGUI();
		c = new ChefGUI();
		
		a.addTab("Waiter", w);
		a.setMnemonicAt(1, KeyEvent.VK_2);
		
		a.addTab("Chef", c);
		a.setMnemonicAt(2, KeyEvent.VK_3);
		
		Controller c = new Controller(a, w);
		this.add(a);
	}
	
	public static void main(String [] args) {
		MainView v = new MainView();
		v.setVisible(true);
	}
}
