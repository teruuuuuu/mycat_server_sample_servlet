import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import servletinterface.HttpServlet;
import servletinterface.HttpServletRequest;
import servletinterface.HttpServletResponse;
import servletinterface.ServletException;

public class ShowBBS extends HttpServlet {
    private ArrayList<Message> messageList = new ArrayList<Message>();

    private String escapeHtml(String src) {
        return src.replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("¥", "&quot;")
            .replaceAll("'", "&#39");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>テスト掲示板</title>");
        out.println("<head>");
        out.println("<body>");
        out.println("<h1>テスト掲示板</h1>");
        out.println("<form action='/SampleServlet/PostBBS' method='POST'>");
        out.println("タイトル：<input type='text' name='title' size='60'><br/>");
        out.println("ハンドル名：<input type='text' name='handle'><br/>");
        out.println("<textarea name='message' rows='4' cols='60'>"
                    + "</textarea><br/>");
        out.println("<input type='submit'/>");
        out.println("</form>");
        out.println("<hr/>");
        for (Message message : Message.messageList) {
        	out.println("<p>[" + escapeHtml(message.title) + "]</p>");
            out.println("<p>" + escapeHtml(message.handle) + " さん</p>");
            out.println("<p>");
            out.println(escapeHtml(message.message).replace("\r\n", "<br/>"));
            out.println("</p><hr/>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
