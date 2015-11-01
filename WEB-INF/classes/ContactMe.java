import java.io.*;  
import java.sql.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import javax.servlet.RequestDispatcher;

public class ContactMe extends HttpServlet {  

//static int count=3;

public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  


 	response.setContentType("text/html");  

	PrintWriter out = response.getWriter();  
	//out.println("starting connection");          
	String firstName=request.getParameter("firstName");  
	String lastName=request.getParameter("lastName");  
	String address=request.getParameter("address");  
	String state=request.getParameter("state");  
	RequestDispatcher rd = null;
	          
	try{  

	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sams","root","");  

  	PreparedStatement ps=con.prepareStatement("insert into sams.persons values(?,?,?,?,?)");  

	//out.print("Setting values"+firstName+" "+lastName);

	ps.setInt(1,3); 
	ps.setString(2,firstName);  
	ps.setString(3,lastName);  
	ps.setString(4,address);  
	ps.setString(5,state);  
	          
	int i=ps.executeUpdate();  
		if(i>0)            // That means we have successfully entered into database
		{
			//out.print("You are successfully registered...");  
			request.setAttribute(firstName, "firstName");
			request.setAttribute(lastName, "lastName");
			//rd = request.getRequestDispatcher("/WelcomeServlet");
			//rd = request.getRequestDispatcher("/contact_me.html");
			rd = request.getRequestDispatcher("/contact.html");
			rd.include(request,response);

			out.println("<b>Thank you "+firstName + " "+ lastName +"for contacting us. We will contact you soon.</b><br>");
		}
		else{
			out.println("<b>Some problem with fields you entered. You can directly call on 213 399 0828.</b><br>");
			//rd = request.getRequestDispatcher("/contact_me.html");
			rd = request.getRequestDispatcher("/contact.html");
			rd.include(request, response);
		}


	}
	catch (Exception e2) 
	{
		out.print(e2.toString());
		System.out.println(e2);
	}  
          
out.close();  
}  // end of doPost method
  
}  // end of class  