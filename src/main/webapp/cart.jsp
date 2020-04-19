<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-18
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>

</head>
<body>
  <a href="getAllGoods">继续购物</a>
  <a href="clearCart">清空购物车</a>

  <c:if test="${!empty cart}">
      <table border="1">
          <tr>
              <td>物品编号</td>
              <td>物品名称</td>
              <td>物品价格</td>
              <td>物品数量</td>
              <td>操作</td>
          </tr>
          <c:forEach items="${cart}" var="item">
              <tr>
                  <form action="processCart" method="post">
                     <input type="hidden" name="goodsId" value="${item.goods.goodsId}" />
                          <td>${item.goods.goodsId}</td>
                          <td>${item.goods.goodsName}</td>
                          <td>${item.goods.price}</td>
                          <td><input type="text" name="quantity" value="${item.quantity}"></td>
                          <td><input type="submit" name="action" value="修改">
                              <input type="submit" name="action" value="删除"></td>
                  </form>
              </tr>
          </c:forEach>
      </table>
  </c:if>

  <c:if test="${empty cart}">
      购物车为空，请直接购物！
  </c:if>

</body>
</html>
