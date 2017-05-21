
import java.io.IOException;

import jp.co.teruuu.mycat.servlet.http.Cookie;
import jp.co.teruuu.mycat.servletinterface.HttpServlet;
import jp.co.teruuu.mycat.servletinterface.HttpServletRequest;
import jp.co.teruuu.mycat.servletinterface.HttpServletResponse;
import jp.co.teruuu.mycat.servletinterface.ServletException;


public class PostBBS extends HttpServlet{

	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        Message newMessage = new Message(request.getParameter("title"),
        		                         request.getParameter("handle"),
                                         request.getParameter("message"));
        Message.messageList.add(0, newMessage);
        setHandleName(response, request.getParameter("handle"));
        response.sendRedirect("/SampleServlet/ShowBBS");
    }
	
	private void setHandleName(HttpServletResponse response, String handleName){
		response.addCookie(new Cookie("bbsHandleName", handleName));
	}
}
