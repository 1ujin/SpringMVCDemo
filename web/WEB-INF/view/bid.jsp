<%@ page import="java.util.Enumeration" language="java" contentType="text/html;charset=UTF-8" pageEncoding="GBK" %><%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2018/7/25
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<%--<h2>Student Information</h2>--%>
<%--<form method="put" action="/addBid">--%>
<%--<input type="text" name="no" />--%>
<%--<input type="submit" />--%>
<%--</form>--%>
<%--此处method可以省略--%>
<form:form method="PUT" action="/addBid" modelAttribute="bid">
    <table>
        <tr>
            <td>
                <form:label path="no">No</form:label>
            </td>
            <td>
                <form:input path="no"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="title">Title</form:label>
            </td>
            <td>
                <form:input path="title"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="url">URL</form:label>
            </td>
            <td>
                <form:input path="url"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="date">Date</form:label>
            </td>
            <td>
                <form:input path="date"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="source">Source</form:label>
            </td>
            <td>
                <form:input path="source"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="checks">Checks</form:label>
            </td>
            <td>
                <form:checkboxes path="checks" items="${checkItems}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>

<%
    Enumeration Names = request.getHeaderNames();
    while (Names.hasMoreElements()) {
        String Name = (String) Names.nextElement();
        System.out.print(Name + ": ");
        System.out.println(request.getHeader(Name));
    }
    Enumeration Attributes = request.getAttributeNames();
    while (Attributes.hasMoreElements()) {
        String Attribute = (String) Attributes.nextElement();
        System.out.print(Attribute + ": ");
        System.out.println(request.getAttribute(Attribute));
    }
%>
