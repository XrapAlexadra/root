<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Корзина</title>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.basket.orders.size()==0}">
        <h4>Ваша корзина пуста</h4>
    </c:when>
    <c:otherwise>
        <form id="setOrder" method="post" action="${pageContext.request.contextPath}/setorder"></form>
        <form id="delFromBasket" method="get" action="${pageContext.request.contextPath}/delfrombasket"></form>
        <table>
            <caption>Товары в корзине</caption>
            <tr>
                <th>Номер</th>
                <th>ID</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th></th>
            </tr>
            <c:forEach items="${requestScope.basket}" var="item" varStatus="infdex">
                <tr>
                    <th><c:out value="${infdex.count}"/></th>
                    <th><c:out value="${item.id}"/></th>
                    <th><c:out value="${item.name}"/></th>
                    <th><c:out value="${item.price}"/></th>
                    <th><label>
                        <input form="setOrder" type="number" min="1" max="${item.quantity}" name="quantity">
                    </label></th>
                    <th>
                        <button form="delFromBasket" type="submit" name="delProduct" value="${item.id}">Удалить</button>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <input form="setOrder" type="submit" value="Заказать">
    </c:otherwise>
</c:choose>
</body>
</html>
