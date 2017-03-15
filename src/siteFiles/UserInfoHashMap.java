package siteFiles;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserInfoHashMap {

	public static ConcurrentHashMap<String, UserInfo> userDetails = new ConcurrentHashMap<String, UserInfo>();
	
	public UserInfoHashMap() {
		
	}	
}
