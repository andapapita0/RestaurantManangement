package businessLayer;


@SuppressWarnings("serial")
/**
 * this abstract class represents the format of a menu item that can be on the menu list. 
 * Its attributes are the name and the price of the menu item. 
 * @author Anda
 *
 */
public abstract class MenuItem implements java.io.Serializable{
	
	protected String menuItem;
	protected int price;
	

	public String getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "MenuItem [menuItem=" + menuItem + ", price=" + price + "]";
	}
	/**
	 * method that gives the price of a menu item
	 * @return the price of a menu item
	 */
	public abstract int computePrice();
	
}

