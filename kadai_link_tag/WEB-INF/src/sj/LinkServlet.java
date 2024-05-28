package sj;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinkServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			getJspData(request, response);
	}
	
	private void getJspData(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	        request.setCharacterEncoding("UTF-8");
	        String userName = request.getParameter("user");
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.include(request, response);
            out.println("\n");
	        out.println("Servletからデータを受信しました。" + userName +"さん、こんにちは！" );
	    }	
}
