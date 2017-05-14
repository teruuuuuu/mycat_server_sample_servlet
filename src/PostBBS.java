import java.io.IOException;

import servletinterface.HttpServlet;
import servletinterface.HttpServletRequest;
import servletinterface.HttpServletResponse;
import servletinterface.ServletException;


public class PostBBS extends HttpServlet{

	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        Message newMessage = new Message(request.getParameter("title"),
        		                         request.getParameter("handle"),
                                         request.getParameter("message"));
        Message.messageList.add(0, newMessage);
        response.sendRedirect("/SampleServlet/ShowBBS");
    }
}
