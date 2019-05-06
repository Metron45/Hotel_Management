package Profiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String query = "select * from credentials where Username=? and Password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database", "root","");
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			out.print("<table>"
					+ "<tr> <th> Name:"+ rs.getString("Name") +"</th> </tr>"
					+ "<tr> <th> Address:"+ rs.getString("Address") +"</th> </tr>"
					+ "<tr> <th> Phone:"+ rs.getString("Phone") +"</th> </tr>"
					+ "<tr> <th> Description:"+ rs.getString("Description") +"</th> </tr>"
					+ "</table>");
			if(rs.getString("Account_Type").equals("Hotel")) {
				

			}
			
		} catch (ClassNotFoundException e) {
			out.println("Exeption Thrown");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("SQL Exeption Thrown");
			e.printStackTrace();
		}
	}
}
