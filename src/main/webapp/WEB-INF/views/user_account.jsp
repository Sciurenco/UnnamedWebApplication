<%@ taglib prefix="text" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>User page</h1>
<h2>Hello, this is your page</h2>

    <p>name: ${userName}</p>
    <p>surname: ${userSurname}</p>
    <p>country: ${userCountry}</p>
    <p>city: ${userCity}</p>

    <p><a href="delete/${userLogin}">delete</a></p>
    <p><a href="update/${userLogin}">update</a></p>

</body>
</html>