import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empid = request.getParameter("empid");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
            Statement st = con.createStatement();
            ResultSet rs;
            if (empid != null && !empid.isEmpty())
                rs = st.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empid);
            else
                rs = st.executeQuery("SELECT * FROM Employee");
            out.println("<table border=1><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
            while (rs.next())
                out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getDouble(3) + "</td></tr>");
            out.println("</table>");
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
