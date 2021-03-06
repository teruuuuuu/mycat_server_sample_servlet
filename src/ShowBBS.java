
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import jp.co.teruuu.mycat.servlet.http.Cookie;
import jp.co.teruuu.mycat.servletinterface.HttpServlet;
import jp.co.teruuu.mycat.servletinterface.HttpServletRequest;
import jp.co.teruuu.mycat.servletinterface.HttpServletResponse;
import jp.co.teruuu.mycat.servletinterface.HttpSession;
import jp.co.teruuu.mycat.servletinterface.ServletException;


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
        out.println("カウント：" + this.getSessionCount(request) + "<br/>");
        out.println("タイトル：<input type='text' name='title' size='60'><br/>");
        out.println("ハンドル名：<input type='text' name='handle' value='" + getHandleNameCookie(request) + "' ><br/>");
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
    
    private String getHandleNameCookie(HttpServletRequest request){
    	Cookie[] cookies = request.getCookie();
    	for(Cookie cookie: cookies)
    		if("bbsHandleName".equals(cookie.getName().trim()))
    			return cookie.getValue();
    	return "";
    }
    
    private String getSessionCount(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	if(session.getAttributes("count") == null){
    		session.setAttributes("count", 0);
    	}
    	int next_count = (int)session.getAttributes("count") + 1;
    	session.setAttributes("count", next_count);
    	return String.valueOf(next_count);
    }
}
