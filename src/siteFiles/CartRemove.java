package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cartRemove")
public class CartRemove extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		String z = request.getParameter("item");

		
		
		helper.removeFromCart(z);
		response.sendRedirect("Cart");

		//displayCart(request, response);
	}
	
	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		
		
		String z = request.getParameter("item");


		
		
		helper.removeFromCart(z);
		
		
		helper.printHtml("header.html");
		
		pw.println("<h2>Item Removed</h2>");
		
		pw.println("<form method='get' action='Home'>Add more items<input type='submit' value='go'></form>");
         
		
		
		
		helper.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		//displayCart(request, response);
	}
}
