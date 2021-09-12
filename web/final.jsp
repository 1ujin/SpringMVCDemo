<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Static Page</title>
</head>
<body>

<h2>A simple HTML page</h2>
<p>WEB-INF 之外的 jsp 文件</p>
<%
    request.setAttribute("request中自行添加的属性", 1L);
    // redirect重定向是两次请求两次响应，地址变
    // redirect重定向是通过getParameter()获取数据则是客户端到服务端传递数据，所以只能传递字符串（RedirectAttributes可以传递对象）
    // forward为请求转发，是一个请求一次响应, 地址不变，由下一个Servlet完成响应体！当前Servlet可以设置响应头（留头不留体）
    // include为请求包含，同时有两个视图的内容，由两个Servlet共同完成响应体（留头又留体）
    // 转发forward和包含include: https://www.cnblogs.com/JamKong/p/4928083.html
    // 参数parameter和属性attribute: https://blog.csdn.net/qq_36557815/article/details/78826617
    request.getRequestDispatcher("./finalPage/1/2?method=plus").forward(request, response);
%>
</body>
</html>
