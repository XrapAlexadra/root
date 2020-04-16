<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Корзина</title>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.basket.orders.size()==0 || sessionScope.basket == null}">
        <c:if test="${requestScope.orderInProcess == null}">
            <h5>Ваша корзина пуста</h5>
        </c:if>
    </c:when>
    <c:otherwise>
        <form id="setOrder" method="post" action="${pageContext.request.contextPath}/setOrder"></form>
        <form id="delFromBasket" method="get" action="${pageContext.request.contextPath}/delFromBasket"></form>
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
                        <input form="setOrder" type="number" min="1" max="${item.quantity}" value="1" name="quantity">
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
<c:if test="${requestScope.orderInProcess != null}">
    <table>
        <caption>Заказанные товары</caption>
        <tr>
            <th>Номер</th>
            <th>ID заказа</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Количество</th>
            <th>Статус</th>
            <th></th>
        </tr>
        <c:forEach items="${requestScope.orderInProcess}" var="item" varStatus="infdex">
            <tr>
                <th><c:out value="${infdex.count}"/></th>
                <th><c:out value="${item.id}"/></th>
                <th><c:out value="${item.productName}"/></th>
                <th><c:out value="${item.productPrice}"/></th>
                <th><c:out value="${item.quantity}"/></th>
                <th><c:out value="${item.status}"/></th>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
