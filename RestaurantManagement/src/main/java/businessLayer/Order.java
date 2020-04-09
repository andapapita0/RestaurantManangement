package businessLayer;

/**
 * : this class represents what an order should look like. Its attributes are an id, the date 
 * and the table number. It has two overridden methods: one computes the hash code and the 
 * other one, called equals, checks if there already is another identical order in the table.
 * @author Anda
 *
 */
public class Order {
	public int orderId = 1; //make it string??
	public String date;
	public int table;
	
	public Order(String date, int table) {
		super();
		this.orderId ++;
		this.date = date;
		this.table = table;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", table=" + table + "]";
	}

	public void setDate(String d) {
		this.date = d;
	}

	public int getTable() {
		return table;
	}

	public void setTable(int table) {
		this.table = table;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return orderId != order.orderId && date != order.date && table != order.table;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + orderId;
		hash = 31 * hash + (date == null ? 0 : date.hashCode());
		hash = 31 * hash +  (int) table;
		return hash;
	}
}
