<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<c:choose>
    <c:when test="${sessionScope.user ==  null}">
        <form name="1"  method="get" action="${pageContext.request.contextPath}/pages/login.jsp">
            <input type="submit" value="Войти">
        </form>
        <form name="2"  method="get" action="${pageContext.request.contextPath}/pages/auth.jsp">
            <input type="submit" value="Зарегистрироваться">
        </form>
    </c:when>
    <c:otherwise>
        <h5>Вы вошли как ${sessionScope.user.login}</h5>
        <form name="3"  method="get" action="${pageContext.request.contextPath}/exit">
            <input type="submit" value="Выйти">
        </form>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/productList" title="Список товаров"><h3>Список товаров</h3></a>
</body>
</html>
