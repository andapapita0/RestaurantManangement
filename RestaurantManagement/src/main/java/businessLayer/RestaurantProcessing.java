package businessLayer;

import java.util.ArrayList;

/**
 * this interface contains all the methods that the restaurant class implements, they represent
 *  the actions that the administrator and the waiter can perform within the app.
 * @author Anda
 *
 */
public interface RestaurantProcessing {
	//administrator
	public void addMenuItem(MenuItem menuItem);
	public void deleteMenuItem(MenuItem menuItem);
	public void editMenuItem(MenuItem menuItem, String s, int p);
	//waiter
	public void createNewOrder(Order o, ArrayList<MenuItem> m);
	public int computePriceForOrder(Order o);
	public void generateBill(Order o);

}
