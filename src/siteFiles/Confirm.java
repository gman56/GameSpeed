package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
				
		String name = request.getParameter("fullname");
        String address = request.getParameter("addr");
        String card = request.getParameter("cred");

       //make confirmation # here???
        
        Calendar date = Calendar.getInstance();
        
        
        String[] x = name.split("\\s+");
        String lastName = x[1]; 
        String firstThree = lastName.substring(0,3);
        
        String lastFour = card.substring(card.length()-4);
        
        String confirm = firstThree + lastFour;
        
        UserInfo entry = new UserInfo(name, address, confirm, date);
        UserInfoHashMap.userDetails.put(helper.username(), entry);
        
        
        
		
		displayConfirm(request, response);
	}
	
	protected void displayConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request,pw);
		
		ArrayList<OrderItem> test = new ArrayList<OrderItem>();
		
		UserInfo x = UserInfoHashMap.userDetails.get(helper.username());
		String confirm = x.getConfirm();
		Calendar date = x.getDate();
		
		
		helper.printHtml("header.html");
		pw.print("<h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order Confirmed</a>");
		pw.print("</h2><div class='entry'>");
		if(helper.CartCount()>0){
		pw.print("<table  class='gridtable'>");
		double total = 0;
		ArrayList<OrderItem> ordered = OrdersHashMap.orders.get(helper.username());

		Iterator<OrderItem> iter = ordered.iterator();

		while (iter.hasNext()) {
		    OrderItem oi = iter.next();
		    pw.print("<tr>");
			pw.print("<th>"+oi.getName()+"</th><th>: "+oi.getPrice()+"</th>" );
			pw.print("</tr>");
			total = total +oi.getPrice();
			
			if(1==1)
			{
				test.add(oi);
				CustomerPurchasedHashMap.userPurchases.put(confirm, test);
			}

		    
		}
	
		
		
		pw.print("<tr><th>Total</th><th>"+total+"</th>");
		pw.print("</table>");
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		pw.print("</div>");	
        pw.println("<hr>");
        pw.println("<h2> Order Date: " + dateFormat.format(date.getTime()) + "</h2>");
        pw.println("<br>");
        pw.println("<h2> Confirmation Number: " + confirm + "</h2>");
        pw.println("<br>");
        
       // CustomerPurchasedHashMap.userPurchases.put(helper.username(), ordered); //storing cart items to purchased
        
        
		helper.clearCart();
		
		
		helper.printHtml("footer.html");
	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		
		displayConfirm(request, response);
	}
}
