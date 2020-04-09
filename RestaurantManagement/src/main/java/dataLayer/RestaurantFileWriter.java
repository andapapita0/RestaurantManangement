package dataLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import businessLayer.MenuItem;
import businessLayer.Order;

/**
 * this class contains a single method, one that generates the bill for an order.
 * @author anda
 *
 */
public class RestaurantFileWriter {
	
	/**
	 * This method uses the classes File and FileWriter in order to create a text file with
	 *  the bill, which contains all the information of an order.
	 * @param o - the order for the bill
	 * @param list - the list which contains the menu
	 * @param price - the price of the order
	 */
	public void bill(Order o, ArrayList<MenuItem> list, int price) {
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
			bw.write("Total price: " + price + " lei");
			bw.newLine();
			
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
