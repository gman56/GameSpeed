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

@WebServlet("/orderDelete")
public class OrderDelete extends HttpServlet {
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
		
		
		pw.print("<h2> Order Deleted </h2>");
		
		
		CustomerPurchasedHashMap.userPurchases.remove(confirm);
		
		
		
		
		
		
			
		
		
		helper.printHtml("footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayCart(request, response, "");
	}
}
