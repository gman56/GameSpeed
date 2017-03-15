package siteFiles;


import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class GameHashMap {

	public static ConcurrentHashMap<String, Game> ps = new ConcurrentHashMap<String, Game>();
	public static ConcurrentHashMap<String, Game> wii = new ConcurrentHashMap<String, Game>();
	public static ConcurrentHashMap<String, Game> xbox = new ConcurrentHashMap<String, Game>();
	
	public static String string_electronicArts = "PlayStation Games";
	public static String string_activision = "Xbox Games";
	public static String string_takeTwoInteractive = "Wii Games";
	
	public GameHashMap() {
		if(wii.isEmpty()){
			Game clone = new Game("Clone Wars",59.99,"clonewarsWII.jpg","nintendo","New",10);
			Game gta = new Game("Grand Theft Auto Wii",59.99,"gtaWII.jpg","nintendo","New",10);
			Game dance = new Game("Just Dance",59.99,"JustdanceWII.jpg","nintendo","New",10);
			Game mario = new Game("Mario Kart",59.99,"mariokartWII.jpg","nintendo","New",10);

			
			wii.put("clone", clone);
			wii.put("gta", gta);
			wii.put("dance", dance);
			wii.put("mario", mario);
		}
		if(ps.isEmpty()){
			Game dead = new Game("Dead Space",54.99,"deadspacePS.JPG","sony","New",10);
			Game nfs = new Game("Need for Speed",54.99,"nfsPS.jpg","sony","New",10);
			Game sniper = new Game("Sniper",54.99,"sniperPS.jpg","sony","New",10);
			Game spider = new Game("Spiderman",54.99,"spiderPS.jpg","sony","New",10);


			ps.put("dead", dead);
			ps.put("nfs", nfs);
			ps.put("sniper", sniper);
			ps.put("spider", spider);

			
		}
		if(xbox.isEmpty()){

			Game crack = new Game("Crack Down",49.99,"crackDownXBOX.png","microsoft","New",10);
			Game fall = new Game("Fallout ",49.99,"falloutXBOX.png","microsoft","New",10);
			Game nba = new Game("NBA 2k12",49.99,"nbaXBOX.jpg","microsoft","New",10);
			Game watch = new Game("Watch Dogs",49.99,"watchdogsXBOX.png","microsoft","New",10);
			Game wwe = new Game("WWE Wrestling 2k16",49.99,"wweXBOX.jpg","microsoft","New",10);

			
			
			xbox.put("crack", crack);
			xbox.put("fall", fall);
			xbox.put("nba", nba);
			xbox.put("watch", watch);
			xbox.put("wwe", wwe);
		}
	}
}
