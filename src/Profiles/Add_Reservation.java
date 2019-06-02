package Profiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Add_Reservation
 */
@WebServlet("/Add_Reservation")
public class Add_Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Reservation() {
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
						
			//insert reservation
			HttpSession httpSession = request.getSession(false);
			if(httpSession != null){
				
			
				String query = "insert into reservations(Hotel_Id,Client_Id,Date_Start,Date_End,People) values(?,?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(query);
				
				//finding which hotel was added
				String query_hotels = "select * from credentials where Account_Type=?";
				PreparedStatement ps_hotels = connection.prepareStatement(query_hotels);
				ps_hotels.setString(1, "Hotel");
				ResultSet rs = ps_hotels.executeQuery();

				while(rs.next()) {
					if(request.getParameter(rs.getString("ID_User")) != null) {
						ps.setString(1, rs.getString("ID_User"));
					}
				}
				//ps.setString(1, rs.getString("ID_User"));
				//rest of additions
				ps.setString(2, (String) httpSession.getAttribute("ID_User"));
				ps.setDate(3, java.sql.Date.valueOf(request.getParameter("Date_Start")));
				ps.setDate(4, java.sql.Date.valueOf(request.getParameter("Date_End")));
				ps.setString(5, request.getParameter("People"));
				ps.executeUpdate();
			}
			//return to Profile
			RequestDispatcher rd;
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
