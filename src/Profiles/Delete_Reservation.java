package Profiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Delete_Reservation
 */
@WebServlet("/Delete_Reservation")
public class Delete_Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete_Reservation() {
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
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database", "root", "");
			RequestDispatcher rd;
			//delete reservation
			
			String query = "DELETE FROM `reservations` WHERE `reservations`.`Reservation_id` = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			

			//finding which hotel was added
			String query_reservations = "select * from reservations";
			
			PreparedStatement ps_reservations = connection.prepareStatement(query_reservations);
			
			ResultSet rs = ps_reservations.executeQuery();
			while(rs.next()) {
				if(request.getParameter(rs.getString("Reservation_id")) != null) {
					ps.setString(1, rs.getString("Reservation_id"));
					ps.executeUpdate();
					
				}
			}
			
			
			rd = request.getRequestDispatcher("Profile");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			out.println("Exeption Thrown");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("SQL Exeption Thrown");
			e.printStackTrace();
		}
	}

}
