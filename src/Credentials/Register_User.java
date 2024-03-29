package Credentials;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register_User
 */
@WebServlet("/Register_User")
public class Register_User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register_User() {
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
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String user_type = request.getParameter("account_type");
		
		//PrintWriter out = response.getWriter();
		//out.println(user_type);
		
		try {
			String query = "insert into credentials(Username,Password,Account_Type,Name,Address,Phone,Description) values(?,?,?,?,?,?,?)";
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database", "root", "");
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			if(user_type == null) {
				ps.setString(3, "Client");
			}else {
				ps.setString(3, "Hotel");
			}
			ps.setString(4, request.getParameter("name"));
			ps.setString(5, request.getParameter("address"));
			ps.setString(6, request.getParameter("phone"));
			ps.setString(7, request.getParameter("description"));
			ps.executeUpdate();
			
			PrintWriter out = response.getWriter();
			out.println("Registration Successful");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
