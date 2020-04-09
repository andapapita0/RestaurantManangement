package businessLayer;
/**
 * this class represents the format of a product which can be introduced in the menu table. 
 * Each object of type Product will have as attributes the ones of the MenuItem class. 
 * @author Anda
 *
 */
@SuppressWarnings("serial")
public class BaseProduct extends MenuItem {
	
	public BaseProduct(String m, int pr) {
		super.menuItem = m;
		super.price = pr;
	}
	/**
	 * this method gives the price of the product
	 */
	public int computePrice() {
		return super.price;
	}

	
}
