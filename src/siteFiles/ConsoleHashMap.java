package siteFiles;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConsoleHashMap{
	public static ConcurrentHashMap<String, Console> microsoft = new ConcurrentHashMap<String, Console>();
	public static ConcurrentHashMap<String, Console> sony = new ConcurrentHashMap<String, Console>();
	public static ConcurrentHashMap<String, Console> nintendo = new ConcurrentHashMap<String, Console>();
	
	public static String string_microsoft = "Microsoft";
	public static String string_sony = "Sony";
	public static String string_nintendo = "Nintendo";
	
	public ConsoleHashMap() {
		HashMap<String, Accessory> accessories;
		if(microsoft.isEmpty()){
			Accessory xboxone_wc = new Accessory("Controller", 40.00, "XBOX controller.jpg", "Microsoft","New",10);
			Accessory xboxone_sh = new Accessory("Turtle Beach Headset", 50.00, "Turtle Beach Headset.jpg", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xboxone_wc", xboxone_wc);
			accessories.put("xboxone_sh", xboxone_sh);			
			Console xboxone = new Console("Xbox One",399.00,"xbox1.jpg","Microsoft","New",10,accessories);
			microsoft.put("xboxone", xboxone);
			
			Accessory xbox360_mr = new Accessory("Speeding Wheel", 40.00, "XBOX360-SpeedWheel.jpg", "Microsoft","New",10);
			Accessory xbox360_wa = new Accessory("Wireless Adapter", 50.00, "xbox360_wa.png", "Microsoft","New",10);
			accessories = new HashMap<String, Accessory>();
			accessories.put("xbox360_mr", xbox360_mr);
			accessories.put("xbox360_wa", xbox360_wa);
			Console xbox360 = new Console("Xbox 360",299.00,"xbox360.jpg","Microsoft","New",10,accessories);			
			microsoft.put("xbox360", xbox360);
		}

		if(sony.isEmpty()){			

			
		
			accessories = new HashMap<String, Accessory>();

			Console ps4 = new Console("PS4",349.00,"PS4-console-bundle.jpg","Sony","New",10,accessories);
			sony.put("ps4", ps4);			
		}

		if(nintendo.isEmpty()){
			
			accessories = new HashMap<String, Accessory>();

			Console wii = new Console("Wii",349.00,"wiiConsole.jpg","Nintendo","New",10,accessories);
			nintendo.put("wii", wii);
			
			accessories = new HashMap<String, Accessory>();

			Console wiiU = new Console("Wii-U",349.00,"wiiU.jpg","Nintendo","New",10,accessories);
			nintendo.put("wiiU", wiiU);
			

		}
	}
}
