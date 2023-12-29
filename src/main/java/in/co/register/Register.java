package in.co.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regform")//use form action value in web socket.
public class Register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		
		String myname = req.getParameter("name1");
		String email = req.getParameter("Email1");
		String pass = req.getParameter("pass1");
		String genderString = req.getParameter("gender1");
		String city  = req.getParameter("city1");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_data","root","1234");
			PreparedStatement pS = con.prepareStatement("insert into register values(?,?,?,?,?)");
			pS.setString(1, myname);
			pS.setString(2, email);
			pS.setString(3, pass);
			pS.setString(4, genderString);
			pS.setString(5, city);
			
			
			
			int count = pS.executeUpdate();
			if (count>0) {
				resp.setContentType("text/html");
				
				out.print("<h3 style=color:'green'> User Registered Successfully </h3>");
				
				RequestDispatcher rDispatcher  = req.getRequestDispatcher("/Register.jsp");
				rDispatcher.include(req, resp);
				
			} else {
				resp.setContentType("text/html");
				
				out.print("<h3 style=color:'red'> User Not Registered Due to Some Error </h3>");
				
				RequestDispatcher rDispatcher  = req.getRequestDispatcher("/Register.jsp");
				rDispatcher.include(req, resp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.setContentType("text/html");
			
			out.print("<h3 style=color:'red'> Exception occured"+e.getMessage()+"</h3>");
			RequestDispatcher rDispatcher  = req.getRequestDispatcher("/Register.jsp");
			rDispatcher.include(req, resp);
		}
		
		
		
	}

}
