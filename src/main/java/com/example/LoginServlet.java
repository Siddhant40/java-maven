import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ServletExample extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        
        String UserName = req.getParameter("user");
        String password = req.getParameter("password");
        
        pw.println("<html>");
        pw.println("<body>");
        
        pw.println("User Name:" + "<span style='color:blue;'><b>" + UserName + "</b></span><br/><br/>");
        pw.println("Password:" + "<span style='color:blue;'><b>" + password + "</b></span>");
        
        pw.println("</body>");
        pw.println("</html>");
        
        pw.close();
    }
}
