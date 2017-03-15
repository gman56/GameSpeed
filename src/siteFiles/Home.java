package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("header.html");
		helper.printHtml("body.html");    
		helper.printHtml("footer.html");
		
		Object num = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
		String id = "user" + String.valueOf(num);
		
	    Cookie ck=new Cookie(id,"yes");//creating cookie object
	    ck.setMaxAge(60 * 60 * 24 * 365 * 10);
	    response.addCookie(ck); 
	    
	    Cookie x[]=request.getCookies();
	    
	    
	    if(x != null)
	    {
	    	for(int i=0;i<x.length;i++)
		    {  
		     if(!x[i].getValue().contains("yes"))
		     {
		    	 	new ConsoleHashMap();
			 		new GameHashMap();
			 		new UserHashMap();
			 		new TabletHashMap();
		     }
		    }  
	    }
	    
	    
	   

	    
	    

	}

}
