 <%--
  Created by IntelliJ IDEA.
  User: Рамиль
  Date: 03.04.2017
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add account</title>
</head>
<body>
<c:url var = "accountURL" value="/account"/>

<form:form method="POST" action="${accountURL}" commandName="accountDto">
    <h1>Registration</h1>
    <form:input path="name" placeholder="Name"/><br>
    <form:input path="balance" placeholder="Balance"/><br>
    <form:errors path="balance" cssStyle="color: red"/><br>
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <form:button type="submit">Sign Up</form:button>
    <br>
</form:form>
</body>
</html>
