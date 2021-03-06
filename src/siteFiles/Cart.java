package siteFiles;
////pw.print("<td>"+i+".</td><td>"+oi.getName()+"</td><td>: "+oi.getPrice()+"</td><td> <a href='Remove' class='btnbuy56'>Remove</a></td>" );


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
				
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		helper.storeProduct(name, type, maker, access);
		
		displayCart(request, response);
	}
	
	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		if(!helper.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		helper.printHtml("header.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+helper.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		if(helper.CartCount()>0){
		pw.print("<table  class='gridtable'>");
		int i = 1; 
		double total = 0;
		String keepTrack="";
		for (OrderItem oi : helper.getCustomerOrders()) {
			pw.print("<tr>");
			//pw.print("<form action='cartRemove'>");
            //pw.println("<input type='hidden' name='name' value=''><td>"+oi.getName()+"</td><td>: $"+oi.getPrice()+"</td><td><input type='submit' value='Delete'></td>");
            
            pw.println("<form action='cartRemove' method='post'><input type='hidden' name='item' value='"+oi.getName()+"'><th>"+oi.getName()+"</th><th>: $"+oi.getPrice()+"</th><th> <input type='submit' value='delete'></th></tr></form>");
			
			//pw.print("</tr>");
			//pw.print("</form>");
			total = total +oi.getPrice();
			i++;
		}
		pw.print("<tr><th></th><th>Total</th><th>"+total+"</th>");
		pw.print("<tr><td></td><td></td><td><a href='CheckOut' class='btnbuy56'>Check Out</a></td>");
		pw.print("</table>");
		}else{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		pw.print("</div></div></div>");		
		helper.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayCart(request, response);
	}
}
