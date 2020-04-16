<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<c:if test="${requestScope.alreadyExist != null}">
    <h5 style="color: blue">Пользователь с логином "${requestScope.alreadyExist}" уже существует. Используйте другой.</h5>
</c:if>
<form  method="post" action="${pageContext.request.contextPath}/auth">
<fieldset style="width: 45px">
    <legend>Регистрация</legend>
    <label>
        <input type="text" name="login" size="20" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Логин" autocomplete="off" required >
    </label>
    <br>
    <label>
        <input type="password" name="pass" size="20" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Пароль" autocomplete="off" required >
    </label>
    <br>
    <input type="submit" value="Отправить">
</fieldset>
</form>
</body>
</html>
