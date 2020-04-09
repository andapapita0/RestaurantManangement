package businessLayer;
import java.util.List;
import java.util.ArrayList;

/**
 * this class is identical with the previous one, but in addition to the inherited attributes, 
 * it contains a list of base products from which the composite product is composed.
 * @author Anda
 *
 */
@SuppressWarnings("serial")
public class CompositeProduct extends MenuItem {
	private List<MenuItem> baseProducts;
	
	public CompositeProduct(String m, List<MenuItem> l) {
		super.menuItem = m;
		baseProducts = l;
		super.price = this.computePrice();
		//baseProducts = new ArrayList<MenuItem>();
	}
	
	public CompositeProduct(ArrayList<MenuItem> list) {
		baseProducts = list;
	}
	/**
	 * method that computes the price of the composed product
	 */
	public int computePrice() {
		int p = 0;
		for(MenuItem bp: baseProducts) {
			p = p + bp.computePrice();
		}
		return p;
	}
	
	
	
}
