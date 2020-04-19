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
      <c:if test="${pageNo!=1}">
          <a href="getAllGoods?pageNo=1">
      </c:if>
              第一页
      <c:if test="${pageNo!=1}">
          </a>
      </c:if>

      <c:if test="${pageNo!=1}">
          <a href="getAllGoods?pageNo=${pageNo-1}">
      </c:if>
             上一页
      <c:if test="${pageNo!=1}">
          </a>
      </c:if>

<c:if test="${pageNo!=pageCount}">
    <a href="getAllGoods?pageNo=${pageNo+1}">
</c:if>
        下一页
<c:if test="${pageNo!=pageCount}">
    </a>
</c:if>

<c:if test="${pageNo!=pageCount}">
    <a href="getAllGoods?pageNo=${pageCount}">
</c:if>
        最后一页
<c:if test="${pageNo!=pageCount}">
    </a>
</c:if>
共${pageCount}页,当前为第${pageNo}页,<a href="addGoods.jsp">添加商品</a>   ${message}

    <table border=1>
        <tr>
            <td>产品编号</td>
            <td>产品名称</td>
            <td>产品价格</td>
        </tr>
        <c:forEach var="goods" items="${requestScope.goodList}">
            <tr>
                <td>${goods.goodsId}</td>
                <td>${goods.goodsName}</td>
                <td>${goods.price}</td>
                <td>
                    <a href="addToCart?goodsId=${goods.goodsId}">将产品添加到购物车里</a>
                    <a href="showModifyPage?goodsId=${goods.goodsId}">修改商品</a>
                    <a href="deleteGoods?goodsId=${goods.goodsId}">删除商品</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
