<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Objects" %>

<html>
    <head>
        <title>個人情報入力フォームを作ろう</title>
    </head>
    <body>
         <%
         // Servletからのデータを取得
         String userName = (String) request.getAttribute("userName");
         String userEmail = (String) request.getAttribute("userEmail");
         String userAddress = (String) request.getAttribute("userAddress");
         String userPhone= (String) request.getAttribute("userPhone");


         // データが存在しない場合は空文字に置き換え
         userName = Objects.toString(userName, "");
         userEmail = Objects.toString(userEmail, "");
         userAddress = Objects.toString(userAddress, "");
         userPhone = Objects.toString(userPhone, "");
         %>
    
        <h2>個人情報入力フォーム</h2>
        <form action="<%= request.getContextPath() %>/confirm" method="post">
	        <table>
	            <tr>
	                <td>氏名</td>
	                <td>
	                    <input type="text" name="user_name" value=<%= userName %>>
	                </td>
	            </tr>
	            <tr>
	                <td>メールアドレス</td>
	                <td>
	                    <input type="text" name="user_email" value=<%= userEmail %>>
	                </td>
	            </tr>
	            	<td>住所</td>
	                <td>
	                    <input type="text" name="user_address" value=<%= userAddress %>>
	                </td>
	            </tr>
	            <tr>
	            	<td>電話番号</td>
	                <td>
	                    <input type="text" name="user_phone" value=<%= userPhone %>>
	                </td>
	            </tr>
	        </table>
	        <br>
            <input type="submit" value="送信">
        </form>
    </body>
</html>
