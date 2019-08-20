import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/count")
public class Counter extends HttpServlet {
    int count = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        count++;
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>This page has been visited "+count+" times</h1>");

        String reset = req.getParameter("reset");
        if(reset.equals("yes")) {
            count = 0;
        }
    }
}
