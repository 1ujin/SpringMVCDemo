<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Static Page</title>
</head>
<body>

<h2>A simple HTML page</h2>
<p>WEB-INF ֮��� jsp �ļ�</p>
<%
    request.setAttribute("request��������ӵ�����", 1L);
    // redirect�ض�������������������Ӧ����ַ��
    // redirect�ض�����ͨ��getParameter()��ȡ�������ǿͻ��˵�����˴������ݣ�����ֻ�ܴ����ַ�����RedirectAttributes���Դ��ݶ���
    // forwardΪ����ת������һ������һ����Ӧ, ��ַ���䣬����һ��Servlet�����Ӧ�壡��ǰServlet����������Ӧͷ����ͷ�����壩
    // includeΪ���������ͬʱ��������ͼ�����ݣ�������Servlet��ͬ�����Ӧ�壨��ͷ�����壩
    // ת��forward�Ͱ���include: https://www.cnblogs.com/JamKong/p/4928083.html
    // ����parameter������attribute: https://blog.csdn.net/qq_36557815/article/details/78826617
    request.getRequestDispatcher("./finalPage/1/2?method=plus").forward(request, response);
%>
</body>
</html>
