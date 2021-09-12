<%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2019/9/2
  Time: 1:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form:form modelAttribute="login" action="login" method="post">
    <fieldset>
        <legend>Login</legend>
        <p>
            <form:label path="name">User Name: </form:label>
            <form:input path="name" id="name" cssErrorClass="ui-state-error"/>
        </p>
        <p>
            <form:label path="password">Password: </form:label>
            <form:password path="password" id="password" cssErrorClass="ui-state-error"/>
        </p>
        <p id="buttons">
            <input id="reset" type="reset" tabindex="4">
            <input id="submit" type="submit" tabindex="5">
        </p>
    </fieldset>
</form:form>
</body>
</html>
