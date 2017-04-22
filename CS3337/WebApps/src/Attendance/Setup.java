package Attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Setup")
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init( ServletConfig config ) throws ServletException{
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentCourse = request.getParameter("courseName");
		request.getSession().setAttribute("currentCourse", currentCourse);
		
		int instructorID = (int) request.getSession().getAttribute("instructorID");
		Connection c = null;
		try{
			String url = "jdbc:mysql://localhost/stars";
			String username = "";
			String password = "";
            
			c = DriverManager.getConnection(url,username,password);
			Statement stmt = c.createStatement();
			
			//Queries all instructors that are in DB
			ResultSet rs = stmt.executeQuery("select deadline from class where course_name='"+currentCourse+"' AND instructor_id='"+instructorID+"'");
			while(rs.next()){
				Time courseDeadline = rs.getTime("deadline");
				request.getSession().setAttribute("courseDeadline", courseDeadline);
			}
		 }catch( SQLException e ){
			 throw new ServletException( e );
	     }
	     finally{
            try{
                if( c != null ) c.close();
            }
            catch( SQLException e ){
                throw new ServletException( e );
            }
	     }
		response.sendRedirect("Swipe");
	}

}
