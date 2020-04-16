<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Администрирование заказов</title>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<table>
    <caption>Заказанные товары</caption>
    <tr>
        <th>Номер</th>
        <th>ID заказа</th>
        <th>Юзер</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Количество</th>
        <th>Статус</th>
        <th>Изменить статус</th>
        <th></th>
    </tr>
    <c:forEach items="${requestScope.allorders}" var="item" varStatus="infdex">
        <tr>
            <th><c:out value="${infdex.count}"/></th>
            <th><c:out value="${item.id}"/></th>
            <th><c:out value="${item.userLogin}"/></th>
            <th><c:out value="${item.productName}"/></th>
            <th><c:out value="${item.productPrice}"/></th>
            <th><c:out value="${item.quantity}"/></th>
            <th><c:out value="${item.status}"/></th>
            <form method="get" action="${pageContext.request.contextPath}/changebasket">
            <th>
                <label>
                    <select name="newstatus">
                        <option value="1">ORDER</option>
                        <option value="2">CONFIRMED</option>
                        <option value="3">PAID</option>
                    </select>
                </label>
            </th>
            <th>
                <button type="submit" name="orderid" value="${item.id}">Изменить</button>
            </th>
            </form>
        </tr>
    </c:forEach>
    <form method="get" action="${pageContext.request.contextPath}/writeoff">
        <label>
            <input type="submit" value="списать товары">
        </label>
    </form>
</table>
</body>
</html>
