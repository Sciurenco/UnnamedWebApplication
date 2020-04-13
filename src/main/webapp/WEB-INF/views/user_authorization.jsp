<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User authorization</title>
</head>
<body>

<h1>Authorization</h1>

<h3>
    <p>Registration status: ${authorizationStatus}</p>
</h3>
<%--@elvariable id="userAuthorization" type="java"--%>
<form:form modelAttribute="userAuthorization"  method="post">
    <label>Login</label><form:input path="userLogin" type="text" name="login" minlength="4" maxlength="30" pattern="^[a-zA-Z0-9]+$"/><br>
    <label>Password</label><form:input path="userPassword" type="password" name="password" minlength="4" maxlength="30" pattern="^[a-zA-Z0-9]+$"/><br>
    <input type="submit" value="send"/>
</form:form>
</body>
</html>