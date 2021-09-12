<%@ page import="java.util.Enumeration" language="java" contentType="text/html;UTF-8" pageEncoding="GBK" %><%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2018/7/25
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Submitted Student Information</h2>
<table>
    <tr>
        <td>No:&nbsp;</td>
        <%--此处变量名与Controller中设置的属性名一样--%>
        <td>${no}</td>
    </tr>
    <tr>
        <td>Title:&nbsp;</td>
        <td>${mb.title}</td>
    </tr>
    <tr>
        <td>URL:&nbsp;</td>
        <td>${url}</td>
    </tr>
    <tr>
        <td>Date:&nbsp;</td>
        <td>${date}</td>
    </tr>
    <tr>
        <td>Source:&nbsp;</td>
        <td>${source}</td>
    </tr>
    <tr>
        <td>Checks:&nbsp;</td>
        <td>
            <%
                String[] checks = (String[]) request.getAttribute("checks");
                for (String check : checks) {
                    out.println(check);
                }
            %>
        </td>
    </tr>
</table>

<%
    System.out.println("\nHeader:");
    Enumeration Names = request.getHeaderNames();
    while (Names.hasMoreElements()) {
        String Name = (String) Names.nextElement();
        System.out.print(Name + ": ");
        System.out.println(request.getHeader(Name));
    }
    System.out.println("\nRequest:");
    Enumeration Attributes = request.getAttributeNames();
    while (Attributes.hasMoreElements()) {
        String Attribute = (String) Attributes.nextElement();
        System.out.print(Attribute + ": ");
        System.out.println(request.getAttribute(Attribute));
    }
    System.out.println("\nParameter:");
    Enumeration Params = request.getParameterNames();
    while(Params.hasMoreElements()) {
        String Param = (String)Params.nextElement();
        System.out.print(Param + ": ");
        System.out.println(request.getParameter(Param));
    }
%>
</body>
</html>
