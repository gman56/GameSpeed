package siteFiles;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/autocomplete")
public class AutoCompleteServlet extends HttpServlet {
	 @Override
	    protected void doGet(final HttpServletRequest request,
	            final HttpServletResponse response) throws ServletException,
	            IOException {

	       
		 ConcurrentHashMap<String, Console> a = ConsoleHashMap.microsoft;
		 ConcurrentHashMap<String, Console> b = ConsoleHashMap.sony;
		 ConcurrentHashMap<String, Console> c = ConsoleHashMap.nintendo;		 
		 ConcurrentHashMap<String, Console> consoles = new ConcurrentHashMap<String, Console>(); 
		 //all consoles
		 
		 consoles.putAll(a); consoles.putAll(b); consoles.putAll(c);		 
		 Collection<Console> con = consoles.values();
		 	
		 
		 ConcurrentHashMap<String, Game> x = GameHashMap.ps;
		 ConcurrentHashMap<String, Game> y = GameHashMap.wii;
		 ConcurrentHashMap<String, Game> z = GameHashMap.xbox;
		 ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<String, Game>(); 

		 
		 games.putAll(x); games.putAll(y); games.putAll(z);		 
		 Collection<Game> game = games.values();
		 
		 try {
             String term = request.getParameter("term");
             System.out.println("Data from ajax call " + term);

             ArrayList<String> list = new ArrayList<String>();
            
             
             //JsonArray arrayObj=new JsonArray();
             
             
             
             for (final Console product : con) {
 	            if (product.getName().toLowerCase().startsWith(term.toLowerCase())) {
 	                list.add(product.getName());
 	            }
 	        }
 	        for (final Game product2 : game) {
 	            if (product2.getName().toLowerCase().startsWith(term.toLowerCase())) {
 	                list.add(product2.getName());
 	            }
 	        }
             
             
 	     String searchList = new Gson().toJson(list);
         response.getWriter().write(searchList);
             
             
             
           /*  
             for(String item: list)
             {
            	 if(item.toLowerCase().startsWith(term.toLowerCase()))
            	 {
            		 arrayObj.add(item);
            	 }
             }
           */
 	        
          // String searchList = new Gson().toJson(arrayObj);
          // response.getWriter().write(searchList);
             

             
     } catch (Exception e) {
             System.err.println(e.getMessage());
     }

	       
		 
		 
		 
/*
	        final String param = request.getParameter("term");
	        final ArrayList<AutoCompleteData> result = new ArrayList<AutoCompleteData>();
	        for (final Console product : con) {
	            if (product.getName().startsWith(param.toLowerCase())) {
	                result.add(new AutoCompleteData(product.getName(), product));
	            }
	        }
	        for (final Game product2 : game) {
	            if (product2.getName().startsWith(param.toLowerCase())) {
	                result.add(new AutoCompleteData(product2.getName(), product2));
	            }
	        }
	        
	        response.getWriter().write(new Gson().toJson(result));	
*/        
	 }
	 
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	 {
			
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			
	        String typed = request.getParameter("search").toLowerCase();

	        
	        
	        ConcurrentHashMap<String, Console> a = ConsoleHashMap.microsoft;
			 ConcurrentHashMap<String, Console> b = ConsoleHashMap.sony;
			 ConcurrentHashMap<String, Console> c = ConsoleHashMap.nintendo;		 
			 ConcurrentHashMap<String, Console> consoles = new ConcurrentHashMap<String, Console>(); 
			 //all consoles
			 
			 consoles.putAll(a); consoles.putAll(b); consoles.putAll(c);		 
			 Collection<Console> con = consoles.values();
			 	
			 
			 ConcurrentHashMap<String, Game> x = GameHashMap.ps;
			 ConcurrentHashMap<String, Game> y = GameHashMap.wii;
			 ConcurrentHashMap<String, Game> z = GameHashMap.xbox;
			 ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<String, Game>(); 

			 
			 games.putAll(x); games.putAll(y); games.putAll(z);		 
			 Collection<Game> game = games.values();
	        
			 
			 for(Console k : con)
			 {
				 if(k.getName().toLowerCase().contains(typed.toLowerCase()))
				 {
					if(k.getRetailer().contains("Sony"))
					{
						response.sendRedirect("PlaystationConsoleList");

					}
					if(k.getRetailer().contains("Nintendo"))
					{
						response.sendRedirect("NintendoConsoleList");

					}
					if(k.getRetailer().contains("Microsoft"))
					{
						response.sendRedirect("XboxConsoleList");

					}
				 }
			}
			 for(Game g : game)
			 {
				 if(g.getName().toLowerCase().contains(typed.toLowerCase()))
				 {
					if(g.getRetailer().contains("sony"))
					{
						response.sendRedirect("GamesList?maker=sony");

					}
					if(g.getRetailer().contains("nintendo"))
					{
						response.sendRedirect("GamesList?maker=nintendo");

					}
					if(g.getRetailer().contains("microsoft"))
					{
						response.sendRedirect("GamesList?maker=microsoft");

					}
				 }
			}
			 
				 
	 }
	        
	        
	        
	        
			
}
	 
	 



