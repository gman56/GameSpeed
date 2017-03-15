package siteFiles;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TabletHashMap {
	public static ConcurrentHashMap<String, Tablet> apple = new ConcurrentHashMap<String, Tablet>();
	public static ConcurrentHashMap<String, Tablet> microsoft = new ConcurrentHashMap<String, Tablet>();
	public static ConcurrentHashMap<String, Tablet> samsung = new ConcurrentHashMap<String, Tablet>();
	
	public static String string_apple = "Apple";
	public static String string_microsoft = "Microsoft";
	public static String string_samsung = "Samsung";
	
	public TabletHashMap() {
		if(apple.isEmpty()){

		}
		if(microsoft.isEmpty()){
	
		}
		if(samsung.isEmpty()){

		}
	}
}
