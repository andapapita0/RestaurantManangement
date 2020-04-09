package dataLayer;

import java.io.*;
import java.util.ArrayList;

import businessLayer.BaseProduct;
import businessLayer.MenuItem;

/**
 * : this class is the helper class which contains the methods for the serialization process: 
 * serialize and deserialize.
 * @author anda
 *
 */
public class RestaurantSerializator {
	
	/**
	 * this method deserializes the menu
	 * @return an ArrayList with the menu
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<MenuItem> deserialize(){
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		FileInputStream fileIn;
		try {
			fileIn = new FileInputStream("D://menu.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			menu = (ArrayList<MenuItem>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
		}
		return menu;
	}
	
	/**
	 * this method serializes a list which contains the menu
	 * @param menu - the list with the menu
	 */
	public void serialize(ArrayList<MenuItem> menu){
		try {
			FileOutputStream fileOut = new FileOutputStream("D://menu.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(menu);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * this main method saves some initial information(menu items) in the .ser file
	 * @param args
	 */
	public static void main(String [] args) {
		
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		MenuItem m1 = new BaseProduct("paine", 2);
		MenuItem m2 = new BaseProduct("lapte", 3);
		MenuItem m3 = new BaseProduct("zacusca", 5);
		MenuItem m4 = new BaseProduct("guacamole", 6);
		MenuItem m5 = new BaseProduct("cartofi prajiti", 10);
		MenuItem m6 = new BaseProduct("pizza", 20);
		
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.add(m4);
		menu.add(m5);
		menu.add(m6);
		
		RestaurantSerializator rs = new RestaurantSerializator();
		
		rs.serialize(menu);
	}
	

}
