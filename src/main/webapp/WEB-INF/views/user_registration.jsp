<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User registration</title>
</head>
<body>

<h1>Registration</h1>

<h3>
    <p>Registration status: ${registrationStatus}</p>
</h3>
<%--@elvariable id="userRegistration" type="java"--%>
<form:form  modelAttribute="userRegistration" method="post">
    <label>Login</label><form:input path="userLogin" type="text" name="login" minlength="4" maxlength="30" pattern="^[a-zA-Z0-9]+$"/><br>
    <label>Password</label><form:input path="userPassword" type="password" name="password" minlength="4" maxlength="30" pattern="^[a-zA-Z0-9]+$"/><br>
    <label>Surname</label><form:input path="userSurname" type="text" name="surname" maxlength="30" pattern="^[a-zA-Z]+$"/><br>
    <label>Name</label><form:input path="userName" type="text" name="name" maxlength="30" pattern="^[a-zA-Z]+$"/><br>
    <label>Country</label><form:input path="userCountry" type="text" name="country" maxlength="30" pattern="^[a-zA-Z]+$"/><br>
    <label>City</label><form:input path="userCity" type="text" name="city" maxlength="25" pattern="^[a-zA-Z]+$"/><br>
    <input type="submit" value="send"/>
</form:form>
</body>
</html>