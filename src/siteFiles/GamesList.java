package siteFiles;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GamesList")
public class GamesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
				
		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, Game> hm = new HashMap<String, Game>();
		
		if(CategoryName==null){
			hm.putAll(GameHashMap.ps);
			hm.putAll(GameHashMap.xbox);
			hm.putAll(GameHashMap.wii);
			name = "";
		}else{
		if(CategoryName.equals("sony")){
			hm.putAll(GameHashMap.ps);
			name = GameHashMap.string_electronicArts;
		}
		else if(CategoryName.equals("microsoft")){
			hm.putAll(GameHashMap.xbox);
			name = GameHashMap.string_activision;
		}
		else if(CategoryName.equals("nintendo")){
			hm.putAll(GameHashMap.wii);
			name = GameHashMap.string_takeTwoInteractive;
		}
		}
		
		Helper helper = new Helper(request,pw);
		helper.printHtml("header.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Games</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Game> entry : hm.entrySet()){
			Game game = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+game.getName()+"</h3>");
			pw.print("<strong>"+ "$" + game.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/games/"+game.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='games'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy56' value='Buy Now' href='#'></input></form></li>");
			pw.print("<li><a class='btnreview' href='Review?name="+entry.getKey()+"&type=games&maker="+CategoryName+"&access='>Reviews</a></li>");
			pw.print("<li><a class='btnreview' href='leaveReview?name="+entry.getKey()+"&type=consoles&maker="+CategoryName+"&access='>Leave a Review</a></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		helper.printHtml("footer.html");
		
	}

}
