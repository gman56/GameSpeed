package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		
		displayCheckout(request, response);
	}
	
	
	protected void displayCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		
		if(OrdersHashMap.orders.containsKey(helper.username()))
		{

		helper.printHtml("header.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Checkout</a>");
		pw.print("</h2><div class='entry'>");
         pw.println("<hr>");
         pw.println("<form name='info' method='post' action='Confirm'>");
         pw.println("<font color='red'>Full Name </font> <br> <input type='text' name='fullname'> <br>");
         pw.println("<font color='red'>Address </font> <br> <input type='text' name='addr'> <br>");
         pw.println("<font color='red'>Credit Card </font> <br> <input type='text' name='cred'> <br>");
         pw.println("<input type='submit' value='Place Order'>");
         pw.println("</form>");
		//pw.print("<a href='Confirm' class='btnbuy56'>Confirm</a>");
		}
		
		pw.print("</div></div></div>");		
		helper.printHtml("footer.html");
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayCheckout(request, response);
	}
}
