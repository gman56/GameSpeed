package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/myAccount")
public class Account extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayCart(request, response);
	}
	
	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to see Account");
			response.sendRedirect("Login");
			return;
		}
		
		helper.printHtml("header.html");
		
		
		pw.print("<h2> Enter Order Confirmation To Get Status/Delete </h2>");
		
		
		//pw.print("<table  class='gridtable'>");

		
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if(CustomerPurchasedHashMap.userPurchases.containsKey(helper.username()))
		{
			order= CustomerPurchasedHashMap.userPurchases.get(helper.username());
		}
		
		
        pw.println("<form name='info' method='post' action='orderShow'>");
        pw.println("<font color='red'>Order Confirmation number</font> <br> <input type='text' name='conf'> <br>");
        pw.println("<input type='submit' value='Show Order'>");
        pw.println("</form>");

			
			 
           // pw.println("<form action='orderShow' method='post'><input type='text' name='conf'><input type='submit' value='Get Order'></form>");
			
			
		
		
		
		helper.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayCart(request, response);
	}
}
