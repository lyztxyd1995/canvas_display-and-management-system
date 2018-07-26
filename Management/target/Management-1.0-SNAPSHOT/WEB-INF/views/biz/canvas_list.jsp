<%--
  Created by IntelliJ IDEA.
  User: yizeliu
  Date: 7/26/18
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>油画列表</title>
    <link rel="stylesheet" href="../../../css/index.css">
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
</head>

<body>
<header>
    <div class="container">
        <nav>
            <c:forEach var="item" items="${categories}">
                <a href = "/canvas/list.do?categoryId=${item.id}">${item.name}</a>
            </c:forEach>
        </nav>
        <nav>
            <a href="/category/list.do">分类</a>
        </nav>
    </div>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>油画</h1>
            <p>油画列表</p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>分类</th>
                <th>价格</th>
                <th>创建时间</th>
                <th>最后修改时间</th>
                <th>描述</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${canvases}">
                    <tr>
                        <td>${item.name}</td>
                        <td>${item.categoryId}</td>
                        <td>${item.price}</td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createTime}"/></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.updateTime}"/></td>
                        <td>${item.description}</td>
                        <td><a href="/canvas/updatePrompt.do?id=${item.id}">编辑</a></td>
                        <td><a href="/canvas/delete.do?id=${item.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="/canvas/addPrompt.do"><button>新建</button></a>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
</html>
