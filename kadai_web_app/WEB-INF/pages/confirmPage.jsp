<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>

<html>
    <head>
        <title>個人情報入力フォームを作ろう</title>
    </head>
    <body>
        <h2>入力内容をご確認ください。</h2>
        <p>問題なければ「確定」、修正する場合は「キャンセル」をクリックしてください。</p>
        <table width="300" border="1">
            <tr>
                <th>項目</th>
                <th>入力内容</th>
            </tr>
            <tr>
                <td>指名</td>
                <td>${name}</td>
            </tr>
            <tr>
                <td>メールアドレス</td>
                <td>${email}</td>
            </tr>
            <tr>
                <td>住所</td>
                <td>${address}</td>
            </tr>
            <tr>
                <td>電話番号</td>
                <td>${phone}</td>
            </tr>
        </table>
         
         <%
         // バリデーションのチェック結果をServletから取得
         ArrayList<String> errorList = (ArrayList<String>) request.getAttribute("errorList");

         // バリデーションNGなら、確定ボタンを無効化するキーワードを設定
         String disableConfirm = (errorList != null ) ? "disabled" : "";
         %>
        <p>
            <%-- 確定ボタン（登録に成功した前提で完了画面に遷移） --%>
            <button
                 onclick="location.href='<%= request.getContextPath() %>/complete';"
                 <%= disableConfirm %>
             >確定</button>

            <%-- キャンセルボタン（前の画面に戻る） --%>
            <button onclick="history.back();">キャンセル</button>
        </p>
         <%
         // バリデーションNGがあればエラーメッセージを表示
         if( errorList != null ) {
             for( String errorMessage : errorList ) {
                 out.println("<font color=\"red\">" + errorMessage + "</font><br>");
             }
         }
         %>
    </body>
</html>
