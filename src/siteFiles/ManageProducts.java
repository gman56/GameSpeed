package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/manageProducts")
public class ManageProducts extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		
		if(!helper.isManager()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "You are not authorized to view this page");
			response.sendRedirect("Login");
			return;
		}
		
		
		displayRegistration(request, response, pw);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String product = request.getParameter("producttype"); //console/game/accessory
		String type = request.getParameter("actType"); //add/delete/update
		String name = request.getParameter("productName").trim().toLowerCase();
		String price = request.getParameter("price").trim();
		String retailer = request.getParameter("retailer").trim().toLowerCase();
		String condition = request.getParameter("condition").trim().toLowerCase();
		String pic = request.getParameter("img");
		
		
		
		boolean microsoft = false;
		boolean sony = false;
		boolean nintendo = false;
		
		boolean ps = false;
		boolean xbox = false;
		boolean wii = false;
		
		
		
		if(retailer.equals("microsoft"))
		{
			microsoft=true;
		}
		if(retailer.equals("sony"))
		{
			sony=true;
		}
		if(retailer.equals("nintendo"))
		{
			nintendo=true;
		}
		if(retailer.equals("ps"))
		{
			ps=true;
		}
		if(retailer.equals("xbox"))
		{
			xbox=true;
		}
		if(retailer.equals("wii"))
		{
			wii=true;
		}
		
		
		
		if(type.equals("add"))
		{
		
			if(product.equals("console"))
			{
				
				HashMap<String, Accessory> asc = new HashMap<String,Accessory>();
				Console x = new Console(name,Double.valueOf(price),pic,retailer,condition,10,asc);
				
				if(nintendo==true)
				{
					ConsoleHashMap.nintendo.put(name.replaceAll("\\s+", ""), x);
				}
				else if(sony == true)
				{
					ConsoleHashMap.sony.put(name.replaceAll("\\s+", ""), x);

				}
				else if(microsoft== true)
				{
					ConsoleHashMap.microsoft.put(name.replaceAll("\\s+", ""), x);

				}
			
			}
			else if(product.equals("game"))
			{
				
				Game x = new Game(name,Double.valueOf(price),pic,retailer,condition,10);
				
				if(ps==true)
				{
					GameHashMap.ps.put(name.replaceAll("\\s+", ""), x);
				}
				else if(wii == true)
				{
					GameHashMap.wii.put(name.replaceAll("\\s+", ""), x);

				}
				else if(xbox == true)
				{
					GameHashMap.xbox.put(name.replaceAll("\\s+", ""), x);

				}
				
				
			}
			else if(product.equals("accessory"))
			{
				
			}
		}
		else if(type.equals("delete"))
		{
			
			if(product.equals("console"))
			{
				
				if(nintendo==true)
				{
					if(ConsoleHashMap.nintendo.containsKey(name))
					{
						ConsoleHashMap.nintendo.remove(name);
					}
				}
				}
				else if(sony == true)
				{
					if(ConsoleHashMap.sony.containsKey(name))
					{
						ConsoleHashMap.sony.remove(name);
					}

				}
				else if(microsoft== true)
				{
					if(ConsoleHashMap.microsoft.containsKey(name))
					{
						ConsoleHashMap.microsoft.remove(name);
					}

				}
				
			}
			else if(product.equals("game"))
			{
				
			}
			else if(product.equals("accessory"))
			{
				
			}
			
			
		
		
	
		
		
		
		
		displayRegistration(request, response, pw);
	}
		
	
	//name,price,image,retailer,condition,discount
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw)
			throws ServletException, IOException {

		Helper helper = new Helper(request, pw);
		helper.printHtml("header.html");
		pw.print("<div class='post' style='float: right; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add to Products</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:500px; margin:25px; margin-left: auto;margin-right: auto;'>");
		pw.print("<form method='post' action='manageProducts'>"
				+ "<table style='width:100%' style='padding:10px' bgcolor='black'><tr><td>"
				+ "<h3>Product Type</h3></td><td><select name='producttype' style='height:40px;' class='input'><option value='console' selected>Console</option><option value='game'>Game</option><option value='accessory'>Accessory</option></select>"
				+ "</td></tr><td>"
				+ "<h3>Add/Delete/Update</h3></td><td><select name='actType' style='height:40px;' class='input'><option value='add' selected>Add</option><option value='delete'>Delete</option><option value='update'>Update</option></select>"
				+ "</td></tr><tr><td>"				
				+ "<h3>Name</h3></td><td><input type='text' name='productName' value='' class='input'></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Price</h3></td><td><input type='text' name='price' value='' class='input'></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Retailer</h3></td><td><input type='text' name='retailer' value='' class='input'></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Condition</h3></td><td><input type='text' name='condition' value='' class='input'></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Image</h3></td><td><input type='file' id='filechooser' name='img' value='' class='input'></input>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Add item' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
		helper.printHtml("footer.html");
	}
}
