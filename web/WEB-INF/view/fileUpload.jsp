<%--
  Created by IntelliJ IDEA.
  User: lujin
  Date: 2019/7/22
  Time: 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Spring MVC上传文件示例</title>
</head>
<body>
<form:form method="POST" modelAttribute="fileUpload" enctype="multipart/form-data">
    请选择一个文件上传 :
    <input type="file" name="file"/>
    <input type="submit" value="提交上传"/>
</form:form>
</body>
</html>
