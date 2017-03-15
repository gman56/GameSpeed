package siteFiles;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class OrdersHashMap {

	public static ConcurrentHashMap<String, ArrayList<OrderItem>> orders = new ConcurrentHashMap<String, ArrayList<OrderItem>>();
	
	public OrdersHashMap() {
		
	}	
}
