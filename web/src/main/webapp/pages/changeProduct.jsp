<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Форма изменения товара</title>
</head>
<body>
<form method="get" action=${pageContext.request.contextPath}/change>
    <fieldset style="width: 100px">

        ID:
        <label>
            <select name="productId">
                <option value="${requestScope.product.id}">${requestScope.product.id}</option>
            </select>
        </label>
        <br>
        Наименование товара:
        <label>
            <input type="text" name="name" required value="${requestScope.product.name}" pattern="^[A-Za-zА-Яа-яЁё\s]+$"
                   maxlength="30" size="33">
        </label>
        <br>
        Цена:
        <label>
            <input type="text" name="price" required value="${requestScope.product.price}" pattern="[0-9]{1,7}"
                   maxlength="7" size="10">
        </label>
        <br>
        Количество:
        <label>
            <input type="text" name="quantity" required value="${requestScope.product.quantity}" pattern="[0-9]{1,7}"
                   maxlength="7"
                   size="10">
        </label>
        <input type="submit" value="Изменить">
        <input type="reset" value="Сбросить">
    </fieldset>
</form>
</body>
</html>
