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

@WebServlet("/leaveReview")
public class LeaveReview extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String productName = request.getParameter("name");
		String consoleType = request.getParameter("type");
		String brand = request.getParameter("maker");


		
		
		displayRegistration(request, response, pw,productName,brand,consoleType);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Helper helper = new Helper(request, pw);

		String userName = helper.username();
		String productName = request.getParameter("name");
		String type = request.getParameter("type");
		String brand = request.getParameter("maker");
		String age = request.getParameter("age");
		String gender = request.getParameter("gend");
		String occupation = request.getParameter("occupation");
		String comments = request.getParameter("comments");
		String rating = request.getParameter("rating");
		
		if(ReviewHashMap.userReviews.containsKey(productName))
		{
			ArrayList<ReviewClass> z = ReviewHashMap.userReviews.get(productName);
	        ReviewClass entry = new ReviewClass(userName ,productName, type, brand, age, gender, occupation, comments, rating);
	        z.add(entry);
	        ReviewHashMap.userReviews.replace(productName, z);
			response.sendRedirect("Home"); return;

	        
		}
		else
		{
		
			ArrayList<ReviewClass> z = new ArrayList<ReviewClass>();
			ReviewClass entry = new ReviewClass(userName ,productName, type, brand, age, gender, occupation, comments, rating);
			z.add(entry);
			ReviewHashMap.userReviews.put(productName, z);
			response.sendRedirect("Home"); return;
		}
		
	
		
	}

	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, String name, String brand, String type)
			throws ServletException, IOException {

		Helper helper = new Helper(request, pw);
		helper.printHtml("header.html");
		pw.print("<div class='post' style='float: right; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Leave a Review</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:500px; margin:25px; margin-left: auto;margin-right: auto;'>");
		pw.print("<form method='post' action='leaveReview'>"
				+ "<table style='width:100%' style='padding:10px' bgcolor='black'><tr><td>"
				+ "<h3>Age</h3></td><td><input type='text' name='age' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Gender</h3></td><td><select name='gend' style='height:40px;' class='input'><option value='male' selected>Male</option><option value='female'>Female</option></select>"
				+ "</td></tr><tr><td>"
				+ "<h3>Occupation</h3></td><td><input type='text' name='occupation' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Rating</h3></td><td><select name='rating' style='height:40px;' class='input'><option value='1' selected>1</option><option value='2'>2</option><option value='3' selected>3</option><option value='4' selected>4</option><option value='5' selected>5</option></select>"
				+ "</td></tr><tr><td>"
				+ "<h3>Comments</h3></td><td><input type='text' name='comments' value='' class='input' required></input>"
				+ "</td></tr></table>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Submit Review' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+"<input type='hidden' name='type' value='"+type+"'>"
				+"<input type='hidden' name='name' value='"+name+"'>"
				+"<input type='hidden' name='maker' value='"+brand+"'>"
				+ "</form>" + "</div></div></div>");
		helper.printHtml("footer.html");
	}
}



