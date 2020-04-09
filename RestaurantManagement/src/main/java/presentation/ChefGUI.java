package presentation;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChefGUI extends JPanel implements Observer{
	
	private JLabel label = new JLabel("Waiting for order...");
	public JLabel p = new JLabel();
	
	public ChefGUI() {
		this.add(label);
		this.add(p);
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Chef has been notified about the order");
		System.out.println("The chef has received the order! " + arg1);
		this.p.setText("I have received the order");
	}

}
