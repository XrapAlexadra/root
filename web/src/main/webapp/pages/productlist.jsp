<%@ page import="com.github.xrapalexandra.kr.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Product List</title>
    <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:if test="${sessionScope.user.role == Role.USER}">
    <form id="choice" method="post" action="${pageContext.request.contextPath}/addorder"></form>
    <table>
        <caption>Спиcок товаров</caption>
        <tr>
            <th>Выбрать</th>
            <th>Номер</th>
            <th>ID</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Количество</th>
         </tr>
        <c:forEach items="${requestScope.productlist}" var="item" varStatus="infdex">
            <tr>
                <th><label>
                    <input form="choice" type="checkbox" name="products[]" value="${item.id}">
                </label></th>
                <th><c:out value="${infdex.count}"/></th>
                <th><c:out value="${item.id}"/></th>
                <th><c:out value="${item.name}"/></th>
                <th><c:out value="${item.price}"/></th>
                <th><c:out value="${item.quantity}"/></th>
            </tr>
        </c:forEach>
    </table>
    <input form="choice" type="submit" value="Добавить в корзину">
    <form method="get" action=${pageContext.request.contextPath}/basket>
        <input type="submit" value="Корзинa">
    </form>
</c:if>
<br>


<c:if test="${sessionScope.user.role == Role.ADMIN}">

    <c:if test="${requestScope.error != null}">
        <h5 style="color: deeppink">${requestScope.error}</h5>
        <br>
    </c:if>
    <form id="change" method="get" action=${pageContext.request.contextPath}/tochange></form>
    <form id="del" method="get" action=${pageContext.request.contextPath}/del></form>
    <table>
        <caption>Спиcок товаров на складе</caption>
        <tr>
            <th>Номер</th>
            <th>ID</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Количество</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${requestScope.productlist}" var="item" varStatus="infdex">
            <tr>
                <th><c:out value="${infdex.count}"/></th>
                <th><c:out value="${item.id}"/></th>
                <th><c:out value="${item.name}"/></th>
                <th><c:out value="${item.price}"/></th>
                <th><c:out value="${item.quantity}"/></th>
                <th>
                    <button form="change" type="submit" name="product_id" value="${item.id}">Изменить</button>
                </th>
                <th>
                    <button form="del" type="submit" name="product_id" value="${item.id}">Удалить</button>
                </th>
            </tr>
        </c:forEach>
    </table>

    <br>

    <form method="get" action=${pageContext.request.contextPath}/addproduct>
        <fieldset style="width: 50px">
            <legend>Добавить товар</legend>
            <label>
                <input type="text" name="name" required pattern="^[A-Za-zА-Яа-яЁё\s]+$" maxlength="30" size="33">
            </label>
            <br>
            <label>
                <input type="text" name="price" required placeholder="0" pattern="[0-9]{1,7}" maxlength="7" size="10">
            </label>
            <label>
                <input type="text" name="quantity" required placeholder="0" pattern="[0-9]{1,7}" maxlength="7"
                       size="10">
            </label>
            <br>
            <input type="submit" value="Добавить">
            <input type="reset" value="Сбросить">
        </fieldset>
    </form>
</c:if>
</body>
</html>
