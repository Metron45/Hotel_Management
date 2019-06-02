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
		
		HttpSession httpSession = request.getSession(false);
		if(httpSession != null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_database", "root","");
				//request user info
				String query = "select * from credentials where ID_User=?";
				PreparedStatement ps = connection.prepareStatement(query);				
				ps.setString(1, (String) httpSession.getAttribute("ID_User"));
				ResultSet rs = ps.executeQuery();
				//display user info
				rs.next();
				out.print("<table>"
					+ "<tr> <th> Name:"+ rs.getString("Name") +"</th> </tr>"
					+ "<tr> <th> Address:"+ rs.getString("Address") +"</th> </tr>"
					+ "<tr> <th> Phone:"+ rs.getString("Phone") +"</th> </tr>"
					+ "<tr> <th> Description:"+ rs.getString("Description") +"</th> </tr>"
					+ "</table>"); 
				//add user type to Httpsession
				httpSession.setAttribute("Account_Type", rs.getString("Account_Type"));
				
				
				
				//
				String type = (String) httpSession.getAttribute("Account_Type");
				
				ResultSet reservation_rs;
				if( type.equals("Hotel")) {
					//request Hotel user reservations
					query = "select Client_Id,Data,People from reservations where Hotel_Id=?";
					ps = connection.prepareStatement(query);				
					ps.setString(1, (String) httpSession.getAttribute("ID_User"));
					rs = ps.executeQuery();
					
					//display Client user reservations
					out.print("<table>");
					out.print("<tr>"
							+"<th> Hotel:"+ "</th>"
							+"<th> Address:" + "</th>"
							+"<th> Phone:" + "</th>"
							+"<th> Date:"+ "</th>"
							+"<th> People:"+ "</th>"
							+"</tr>");
					while(rs.next()) {
						query = "select * from credentials where ID_User=?";
						ps = connection.prepareStatement(query);				
						ps.setString(1, rs.getString("Client_Id"));
						reservation_rs = ps.executeQuery();
						reservation_rs.next();
						out.print("<tr>"
								+"<th>"+reservation_rs.getString("Name")+ "</th>"
								+"<th>"+reservation_rs.getString("Address")+ "</th>"
								+"<th>"+reservation_rs.getString("Phone")+ "</th>"
								+"<th>"+rs.getString("Data")+ "</th>"
								+"<th>"+rs.getString("People")+ "</th>"
								+"</tr>");
					}
					out.print("</table>");
					//logout
					request.getRequestDispatcher("Profile.jsp").include(request, response);
				}else {
					//request user reservations
					query = "select Hotel_Id,Data,People from reservations where Client_Id=?";
					ps = connection.prepareStatement(query);				
					ps.setString(1, (String) httpSession.getAttribute("ID_User"));
					rs = ps.executeQuery();
					
					//display Client user reservations
					out.print("<table>");
					out.print("<tr>"
							+"<th> Hotel:"+ "</th>"
							+"<th> Address:" + "</th>"
							+"<th> Phone:" + "</th>"
							+"<th> Date:"+ "</th>"
							+"<th> People:"+ "</th>"
							+"</tr>");
					while(rs.next()) {
						query = "select * from credentials where ID_User=?";
						ps = connection.prepareStatement(query);				
						ps.setString(1, rs.getString("Hotel_Id"));
						reservation_rs = ps.executeQuery();
						reservation_rs.next();
						out.print("<tr>"
								+"<th>"+reservation_rs.getString("Name")+ "</th>"
								+"<th>"+reservation_rs.getString("Address")+ "</th>"
								+"<th>"+reservation_rs.getString("Phone")+ "</th>"
								+"<th>"+rs.getString("Data")+ "</th>"
								+"<th>"+rs.getString("People")+ "</th>"
								+"</tr>");
					}
					out.print("</table>");
					//display adding reservations
					out.print("<form method=\"post\" action=\"Add_Reservation\">");
					
					//display all hotels
					query = "select * from credentials where Account_Type=?";
					ps = connection.prepareStatement(query);
					ps.setString(1, "Hotel");
					rs = ps.executeQuery();
					while(rs.next()) {
						out.print("<input type=\"checkbox\" name=\"Hotel_check\">");
						out.print(rs.getString("Name") 
								+ rs.getString("Address")
								+ rs.getString("Phone")
								);
						out.print("<br>");
					}
					
					out.print("Date:<input type=\"text\" name=\"Date\" required=\"required\">");
					out.print("People:<input type=\"text\" name=\"People\" required=\"required\">");
					out.print("<input type=\"submit\" value=\"Add Reservation\">");
					out.print("</form>");
					request.getRequestDispatcher("Profile.jsp").include(request, response);
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
}
