package pl.mwht;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

@WebServlet(name = "ChatServlet")
public class ChatServlet extends HttpServlet {

    Collection<String> chatLog = Collections.synchronizedCollection(new Vector<String>());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String entry = request.getParameter("entry");
        if(entry != null) chatLog.add(entry);
        response.sendRedirect("ChatServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");

        try {
            out.println("<meta http-equiv=\"Refresh\" content=\"10\" />");
            out.println("<form method=\"POST\">\n" +
                    "<input type=\"text\" name=\"entry\" />\n" +
                    "<input type=\"submit\" value=\"Wyslij\" />\n" +
                    "</form><br />");

            Iterator<String> it = chatLog.iterator();
            while (it.hasNext()) {
                out.println(it.next() + "<br />");
            }
        } finally {
            out.flush();
            out.close();
        }
    }
}
