<%@ page import="java.util.Enumeration" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2018/7/25
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring Page Redirection</title>
</head>
<body>

<h2>Redirected Page</h2>
<p>${ID}+${no}=${ID + no}</p>
<%--得不到map--%>
<p>得不到map: ${map.size()}</p>

</body>
</html>

<%
    System.out.println("Request:");
    Enumeration Names = request.getAttributeNames();
    while(Names.hasMoreElements()) {
        String Name = (String)Names.nextElement();
        System.out.print(Name + ": ");
        System.out.println(request.getAttribute(Name));
    }
    System.out.println("\nParameter:");
    Enumeration Params = request.getParameterNames();
    while(Params.hasMoreElements()) {
        String Param = (String)Params.nextElement();
        System.out.print(Param + ": ");
        System.out.println(request.getParameter(Param));
    }
%>

