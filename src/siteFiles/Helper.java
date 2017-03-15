package siteFiles;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Helper {
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 

	public Helper(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}

	public void printHtml(String file) {    //HERE IS THE login/cart
		String result = HtmlToString(file);
		if (file.contains("header.html")) {
			if((session.getAttribute("usertype") != null) && session.getAttribute("usertype").equals("manager") && session.getAttribute("username")!=null)
			{
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result
						+ "<li><div style='visibility: hidden'>Textsadaddasdasdasdsaajksabbakjdbsdsadasd </div></li>"
						+ "<li><a>Hello Manager, "+username+"</a></li>"
						+ "<li><a href='manageProducts'>Manage</a></li>"
						+ "<li><a href='Logout'>Logout</a></li>";
			}
			else if((session.getAttribute("usertype") != null) && session.getAttribute("usertype").equals("retailer") && session.getAttribute("username")!=null)
			{
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result
						+ "<li><div style='visibility: hidden'>Textsadadasdsadasd </div></li>"
						+ "<li><a>Hello Salesman, "+username+"</a></li>"
						+ "<li><a href='createAccounts'>Create Accounts/Manage Orders</a></li>"
						+ "<li><a href='Logout'>Logout</a></li>";
			}
			
			
			else if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result
						+ "<li><div style='visibility: hidden'>Textsadadasdasadsadasadadassadasdaddasdsadasd </div></li>"
						+ "<li><a>Hello, "+username+"</a></li>"
						+ "<li><a href='myAccount'>Account</a></li>"
						+ "<li><a href='Logout'>Logout</a></li>";
			}
			else
				result = result + "<li><div style='visibility: hidden'>asTextdwsadasdasdasdadasdsasadasdajkanknfadjknanfkvsadadasdsadasd </div></li>"+
				"<li><a href='Login'>Login</a></li>";
			result = result
					+ "<li><a href='Cart'>Cart("+CartCount()+")</a></li></ul></div></div><div id='page'>";
			pw.print(result);
		} else
			pw.print(result);
	}
	

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}


	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} catch (Exception e) {
		}
		return result;
	}

	
	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	public boolean isManager()
	{
	
		if (session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("manager"))
		{
			return true;
		}
		else
			return false;
		
	}
	
	public boolean isSalesman()
	{
		if (session.getAttribute("usertype")!=null && session.getAttribute("usertype").equals("retailer"))
		{
			return true;
		}
		else
			return false;
		
	}
	
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm = new HashMap<String, User>();
		if (usertype.equals("customer")) {
			hm.putAll(UserHashMap.customer);
		} else if (usertype.equals("retailer")) {
			hm.putAll(UserHashMap.retailer);
		} else if (usertype.equals("manager")) {
			hm.putAll(UserHashMap.manager);
		}
		User user = hm.get(username());
		return user;
	}
	
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}
	
	
	public ArrayList<OrderItem> getPurchased(String confirm)
	{
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if(CustomerPurchasedHashMap.userPurchases.containsKey(confirm))
			order= CustomerPurchasedHashMap.userPurchases.get(confirm);
		return order;

	}
	
	
	public void clearCart()
	{
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
		{
			order= OrdersHashMap.orders.get(username());
			order.clear();
			
		}
	}
	public void removeFromCart(String name)
	{
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
		{
			order= OrdersHashMap.orders.get(username());
			
			Iterator<OrderItem> iter = order.iterator();
			
			while(iter.hasNext())
			{
				OrderItem str = iter.next();
				if(str.getName().contains(name))
				{
					iter.remove();
				}
			}
			
			/*
			for (OrderItem i : order) 
			{
				if(i.getName().contains(name))
				{
					order.remove(i);
				}
				
			}
			*/
			OrdersHashMap.orders.replace(username(), order);
			
		}
		
	}
	
	

	
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/*
	public void removeProduct(String name, String type, String maker, String acc)
	{
		OrderItem x = new OrderItem(acc, 0, acc, acc);
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(OrdersHashMap.orders.containsKey(username()))
		{
			//orderItems=OrdersHashMap.orders.get(username());
			OrdersHashMap.orders.remove(username());
			//orderItems.
			//try removing specific item instead of all orderitems
		}

	}
*/

		
	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);  
			//creating orderHashmap if none exists
		}
		
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		
		if(type.equals("consoles")){
			Console console = null;
			if(maker.equals("microsoft")){
				console = ConsoleHashMap.microsoft.get(name);
			}
			else if(maker.equals("sony")){
				console = ConsoleHashMap.sony.get(name);
			}
			else if(maker.equals("nintendo")){
				console = ConsoleHashMap.nintendo.get(name);
			}else{
				HashMap<String, Console> hm = new HashMap<String, Console>();
				hm.putAll(ConsoleHashMap.microsoft);
				hm.putAll(ConsoleHashMap.sony);
				hm.putAll(ConsoleHashMap.nintendo);				
				console = hm.get(name);
				
			}
			OrderItem orderitem = new OrderItem(console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("games")){
			Game game = null;
			if(maker.equals("ps")){
				game = GameHashMap.ps.get(name);
			}
			else if(maker.equals("wii")){
				game = GameHashMap.wii.get(name);
			}
			else if(maker.equals("xbox")){
				game = GameHashMap.xbox.get(name);
			}else{
				HashMap<String, Game> hm = new HashMap<String, Game>();
				hm.putAll(GameHashMap.ps);
				hm.putAll(GameHashMap.wii);
				hm.putAll(GameHashMap.xbox);				
				game = hm.get(name);
			}
			OrderItem orderitem = new OrderItem(game.getName(), game.getPrice(), game.getImage(), game.getRetailer());
			orderItems.add(orderitem);
		}
		
		if(type.equals("tablets")){
			Tablet tablet = null;
			if (maker.equals("apple")) {
				tablet = TabletHashMap.apple.get(name);
			} else if (maker.equals("microsoft")) {
				tablet = TabletHashMap.microsoft.get(name);
			} else if (maker.equals("samsung")) {
				tablet = TabletHashMap.samsung.get(name);
			}else{
				HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
				hm.putAll(TabletHashMap.apple);
				hm.putAll(TabletHashMap.microsoft);
				hm.putAll(TabletHashMap.samsung);				
				tablet = hm.get(name);
			}
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		
		if(type.equals("accessories")){
			Console console = null;
			if(maker.equals("microsoft")){
				console = ConsoleHashMap.microsoft.get(acc);
			}
			else if(maker.equals("sony")){
				console = ConsoleHashMap.sony.get(acc);
			}
			else if(maker.equals("nintendo")){
				console = ConsoleHashMap.nintendo.get(acc);
			}
			
			Accessory accessory = console.getAccessories().get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	
	public String currentDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Date date = new Date();
		return dateFormat.format(date).toString(); 
	}
	
	public HashMap<String, Console> getConsoles(){
			HashMap<String, Console> hm = new HashMap<String, Console>();
			hm.putAll(ConsoleHashMap.microsoft);
			hm.putAll(ConsoleHashMap.sony);
			hm.putAll(ConsoleHashMap.nintendo);
			return hm;
	}
	
	public HashMap<String, Game> getGames(){
		HashMap<String, Game> hm = new HashMap<String, Game>();
			hm.putAll(GameHashMap.ps);
			hm.putAll(GameHashMap.wii);
			hm.putAll(GameHashMap.xbox);
			return hm;
	}
	
	public HashMap<String, Tablet> getTablets(){
			HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
			hm.putAll(TabletHashMap.apple);
			hm.putAll(TabletHashMap.microsoft);
			hm.putAll(TabletHashMap.samsung);
			return hm;
	}
	
	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Console> entry : getConsoles().entrySet()){			
			ar.add(entry.getValue().getName());
		}

		return ar;
	}
	
	public ArrayList<String> getProductsGame(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Game> entry : getGames().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	public ArrayList<String> getProductsTablets(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablets().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	

}
