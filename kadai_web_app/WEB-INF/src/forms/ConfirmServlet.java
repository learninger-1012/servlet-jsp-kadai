package forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmServlet extends HttpServlet {
    // POSTメソッドのリクエスト受信時に実行されるメソッド
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
   	 
        // リクエスト・レスポンスの設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // JSPからのリクエストデータ取得
        String name = request.getParameter("user_name");
        String email = request.getParameter("user_email");
        String address = request.getParameter("user_address");  
        String phone = request.getParameter("user_phone");
        
        // データが存在しない場合は空文字に置き換え
        name = Objects.toString(name, "");
        email = Objects.toString(email, "");
        address = Objects.toString(address, "");
        phone = Objects.toString(phone, "");
        
        // リクエストスコープにデータ保存
        request.setAttribute( "name", name );
        request.setAttribute( "email", email );
        request.setAttribute( "address", address );
        request.setAttribute( "phone", phone );

        
        // データが存在しない場合は空文字に置き換え
        name = Objects.toString(name, "");
        email = Objects.toString(email, "");
        address = Objects.toString(address, "");
        phone = Objects.toString(phone, "");

        // バリデーションNG時のメッセージを格納するリスト
        ArrayList<String> errorList = new ArrayList<String>();

        // お名前のバリデーション
        if( "".equals(name.trim()) ) { // 未入力
            // お名前が未入力の場合
            errorList.add("お名前を入力してください。");
        }

        // メールアドレスのバリデーション
        if( "".equals(email.trim()) ) { // 未入力
            errorList.add("メールアドレスを入力してください。");
        } else if( !email.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9.]+$") ) { // 入力形式
            errorList.add("メールアドレスの入力形式が正しくありません。");
        }

        // お問い合わせ内容のバリデーション
        if( "".equals(address.trim()) ) { // 未入力
            errorList.add("住所を入力してください。");
        } 
        
        // メールアドレスのバリデーション
        if( "".equals(phone.trim()) ) { // 未入力
            errorList.add("電話番号を入力してください。");
        } 

        // エラーリストが空かどうか
        if( !errorList.isEmpty() ) {
            // エラーがある場合はリストをリクエストスコープに登録
            request.setAttribute("errorList", errorList);
        }else{
            // セッションの取得
            HttpSession session = request.getSession();

            // セッションへのデータ登録
            session.setAttribute( "name", name );
            session.setAttribute( "email", email );
            session.setAttribute( "address", address );
            session.setAttribute( "phone", phone );

            // クッキーに保存
            setCookie(response, "name", name );
            setCookie(response, "email", email );
            setCookie(response, "address", address );
            setCookie(response, "phone", phone );
        }
        // フォワードによる画面遷移
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/confirmPage.jsp");
        dispatcher.forward(request, response);        

    }
        
        private void setCookie(HttpServletResponse response, String name, String value ) {
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(60 * 60 * 24 * 1); // 有効期限は1日
            response.addCookie(cookie);
        }
}
