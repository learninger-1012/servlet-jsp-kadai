package forms;

 import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompleteServlet extends HttpServlet {
     // GETメソッドのリクエスト受信時に実行されるメソッド
     public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
    	 
         // 次回実行時にフォームをクリアするためcookie初期化
         setCookie(response, "name", "" );
         setCookie(response, "email", "" );
         setCookie(response, "address", "" );
         setCookie(response, "phone", "" );
    	 
         // フォワードによる画面遷移
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/completePage.jsp");
         dispatcher.forward(request, response);
     }
     private void setCookie(HttpServletResponse response, String name, String value ) {
         Cookie cookie = new Cookie(name, value);
         cookie.setMaxAge(60 * 60 * 24 * 1); // 有効期限は1日
         response.addCookie(cookie);
     }
}