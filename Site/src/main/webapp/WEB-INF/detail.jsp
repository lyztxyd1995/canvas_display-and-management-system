<%--
  Created by IntelliJ IDEA.
  User: yizeliu
  Date: 7/27/18
  Time: 2:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>详情</title>
    <link rel="stylesheet" type="text/css" href="../style/common.css" />
    <link rel="stylesheet" type="text/css" href="../style/detail.css" />
    <!--<link rel="stylesheet" type="text/css" href="style/reset.css" />
        <link rel="stylesheet" type="text/css" href="style/style.css" />-->
</head>
<body class="bgf8">
<div class="header">
    <div class="logo f1">
        <img src="../image/logo.png">
    </div>
    <div class="auth fr">
        <ul>
            <li><a href="#">登录</a></li>
            <li><a href="#">注册</a></li>
        </ul>
    </div>
</div>
<div class="content">
    <div class="section" style="margin-top:20px;">
        <div class="width1200">
            <div class="fl"><img src="/canvas/getImg.do?id=${canvas.id}"/></div>
            <div class="fl sec_intru_bg">
                <dl>
                    <dt>${canvas.name}</dt>
                    <dd>
                        <p>发布人：<span>${canvas.creator}</span></p>
                        <p>发布时间：<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${canvas.createTime}"/></span></p>
                        <p>修改时间：<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${canvas.createTime}"/></span></p>
                    </dd>
                </dl>
                <ul>
                    <li>售价：<br/><span class="price">${canvas.price}</span>元</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="secion_words">
        <div class="width1200">
            <div class="secion_wordsCon">
                <p>${canvas.description}</p>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p><span>M-GALLARY</span>©2017 POWERED BY IMOOC.INC</p>
</div>
</div>
</body>
</html>
