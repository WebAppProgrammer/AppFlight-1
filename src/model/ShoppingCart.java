package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<Integer, ShoppingCartEntry> entries; 
	private String total = "";

	public ShoppingCart() {
		super();
		entries= new HashMap<Integer, ShoppingCartEntry>();
	} 
	
	public Map<Integer, ShoppingCartEntry> getEntries() {
		return entries;
	}

	public void setEntries(Map<Integer, ShoppingCartEntry> entries) {
		this.entries = entries;
	}

	public String getTotal() {
		double grand_total=0.0; 
		for(ShoppingCartEntry e: this.entries.values()){
			grand_total+=e.getTotal(); 
		}		
		return DecimalFormat.getCurrencyInstance().format(grand_total);
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public boolean AddEntry(int flight_id, ShoppingCartEntry entry){
		boolean retVal = true;
		if(this.entries.containsKey(flight_id)){
			retVal = false;
		}else{
			this.entries.put(flight_id, entry);
		}
		return  retVal;
	}

	public boolean RemoveEntry(int flight_id){
		boolean retVal = true;
		if(this.entries.containsKey(flight_id)){
			this.entries.remove(flight_id);
		}else{
			System.out.println("Flight ID not found on shopping cart.");
			System.out.println(this.entries);
			retVal = false;
		}
		return  retVal;	
	}

	@Override
	public String toString() {
		return "ShoppingCart [entries=" + entries + ", total=" + total + "]";
	}
}
