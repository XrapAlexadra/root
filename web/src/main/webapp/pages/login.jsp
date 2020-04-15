<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Вход</title>
</head>
<body><c:if test="${requestScope.error != null}">
    <h5 style="color: deeppink">${requestScope.error}</h5>
    <br>
</c:if>
<form method="post" action="${pageContext.request.contextPath}/login">
    <fieldset style="width: 45px">
        <legend>Вход</legend>
        <label>
            <input type="text" name="login" size="20" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Логин"
                   required>
        </label>
        <br>
        <label>
            <input type="password" name="pass" size="20" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" placeholder="Пароль"
                   autocomplete="off" required>
        </label>
        <br>
        <input type="submit" value="Отправить">
    </fieldset>
</form>
</body>
</html>
