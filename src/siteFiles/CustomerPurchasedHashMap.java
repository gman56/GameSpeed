package siteFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerPurchasedHashMap {

	public static ConcurrentHashMap<String, ArrayList<OrderItem>> userPurchases = new ConcurrentHashMap<String, ArrayList<OrderItem>>();
	
	public CustomerPurchasedHashMap() {
		
	}	
}


