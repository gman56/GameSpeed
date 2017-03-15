package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Review")
public class Reviews extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);
		String productName = request.getParameter("name");

		
		helper.printHtml("header.html");
		pw.print("<div id='content'><div class='post'>");
		pw.print("<div class='entry'>");

		
		pw.print("<h2>Product Reviews for "+productName+"");
		
		if(ReviewHashMap.userReviews.containsKey(productName))
		{
			//pw.print("<h2>its there</h2>");
			
		for(ReviewClass x: ReviewHashMap.userReviews.get(productName))
		{
			
			
			pw.print("<table  class='reviewTable' style='width:80%' style='padding:10px' bgcolor='black'>"
			 + "<tr>"
			 + "<td class='def'>User Name:</td>"
			 + "<td>"+x.getUserName()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Product Name:</td>"
				 + "<td>"+x.getItemName()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Type:</td>"
				 + "<td>"+x.getType()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Brand:</td>"
				 + "<td>"+x.getBrand()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Age:</td>"
				 + "<td>"+x.getAge()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Gender:</td>"
				 + "<td>"+x.getGender()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Occupation:</td>"
				 + "<td>"+x.getOccupation()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Rating:</td>"
				 + "<td>"+x.getRating()+"</td>"
			  +"</tr>"
			  +"<tr>"
			  + "<td class='def'>Comments:</td>"
				 + "<td>"+x.getComments()+"</td>"
			  +"</tr>"
			+"</table>");
			
			pw.print("<br>");
			
		}
		helper.printHtml("footer.html");



		
		}	
	}
	
}	
	
/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String usertype = "customer";
		if(!helper.isLoggedin())
			usertype = request.getParameter("usertype");
		
	
		
	}
*/
	

