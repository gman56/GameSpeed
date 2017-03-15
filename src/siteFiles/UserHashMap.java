package siteFiles;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserHashMap {
	public static ConcurrentHashMap<String, User> customer = new ConcurrentHashMap<String, User>();
	public static ConcurrentHashMap<String, User> retailer = new ConcurrentHashMap<String, User>();
	public static ConcurrentHashMap<String, User> manager = new ConcurrentHashMap<String, User>();
	
	public UserHashMap(){
		if(customer.isEmpty()){
			User user = new User("customer","customer","customer");
			customer.put("customer",user);
		}
		if(retailer.isEmpty()){
			User user = new User("retailer","retailer","retailer");
			retailer.put("retailer",user);		
		}
		if(manager.isEmpty()){
			User user = new User("manager","manager","manager");
			manager.put("manager",user);
		}
	}

}
