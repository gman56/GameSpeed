package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orderShow")
public class OrderShow extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		String confirm = request.getParameter("conf");

		
		displayCart(request, response, confirm);
	}
	
	protected void displayCart(HttpServletRequest request, HttpServletResponse response, String confirm) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login");
			response.sendRedirect("Login");
			return;
		}
		
		helper.printHtml("header.html");
		
		pw.print("<br>");
		
		UserInfo z = UserInfoHashMap.userDetails.get(helper.username());
		
		Calendar orderDate = z.getDate();
		Calendar dateLimit = z.getDate();
		boolean canCancel = true;
		
		dateLimit.add(Calendar.DAY_OF_MONTH, -5);
		
		if(orderDate.after(dateLimit))
		{
			canCancel = false;
		}
		
		if(!CustomerPurchasedHashMap.userPurchases.containsKey(confirm))
		{
			pw.print("<h2> Order Does not Exist </h2>");
			
		}
		else if(canCancel == true)
		{
			pw.print("<h2> Your Order </h2>");
			pw.print("<h2>" + confirm + "</h2> ");
			pw.print("<h2> Ordered on " + orderDate.getTime() + "</h2> ");

			
		

		

		
		//ArrayList<OrderItem> ordered = new ArrayList<OrderItem>();
		if(CustomerPurchasedHashMap.userPurchases.containsKey(confirm))
		{
			//ArrayList<OrderItem> ordered = CustomerPurchasedHashMap.userPurchases.get(confirm);
			
			
			pw.print("<table class='gridtable'>");
			
			int x = helper.getPurchased(confirm).size();
			//pw.print("<h2>"+x+"</h2>");//size of order

			
			for(OrderItem oi: helper.getPurchased(confirm))
			{
				pw.print("<tr>");
				pw.print("<th>"+oi.getName()+"</th><th>: "+oi.getPrice()+"</th>" );
				pw.print("</tr>");
			}
			
			pw.print("</table>");

			if(CustomerPurchasedHashMap.userPurchases.containsKey(confirm))
			{
				
				 
	          //  pw.println("<form action='orderDelete' method='post'><input type='hidden' name='conf' value=''><td>"+oi.getName()+"</td><td>: $"+oi.getPrice()+"</td><td> <input type='submit' value='delete'></td></tr></form>");
				
	            pw.println("<form action='orderDelete' method='post'><input type='hidden' name='conf' value='"+confirm+"'><input type='submit' value='Delete Order'></form>");
			}
			
			
		
			
			
			

		}
		
		}
		
		
		helper.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		//displayCart(request, response, null);
	}
}
