package siteFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReviewHashMap {

	public static ConcurrentHashMap<String,  ArrayList<ReviewClass>> userReviews = new ConcurrentHashMap<String, ArrayList<ReviewClass>>();
	
	public ReviewHashMap() {
		
	}	
}
