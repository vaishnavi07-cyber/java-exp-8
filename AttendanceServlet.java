import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sid = request.getParameter("sid");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO Attendance VALUES (?,?,?)");
            ps.setString(1, sid);
            ps.setString(2, date);
            ps.setString(3, status);
            int i = ps.executeUpdate();
            if (i > 0)
                out.println("<h3>Attendance saved successfully</h3>");
            con.close();
        } catch (Exception e) {
            out.println(e);
        }
    }
}
