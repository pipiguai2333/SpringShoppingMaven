<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-04-19
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
   <form action="addGoods" method="post">
       商品编号：<input type="text" name="goodsId"><br/>
       商品名称：<input type="text" name="goodsName"><br/>
       商品价格：<input type="text" name="goodsPrice"><br/>
       <input type="submit" name="action" value="添加">
   </form>
</body>
</html>
