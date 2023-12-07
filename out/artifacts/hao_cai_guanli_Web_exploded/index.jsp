<%@ page import="com.hai.service.BookServiceImpl" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.hai.pojo.Books" %><%--
  Created by IntelliJ IDEA.
  User: 14335
  Date: 2021/12/18
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>第一种上传方式</h1>
  <form action="${pageContext.request.contextPath}/up" enctype="multipart/form-data" method="post">
    <input type="file" name="file">
    <input type="submit">
    ${mage}
  </form>
  <h1>第二种上传方式</h1>
  <form action="${pageContext.request.contextPath}/up2" enctype="multipart/form-data" method="post">
    <input type="file" name="file">
    <input type="submit">
    ${mage}
  </form>
  <h1>下载文件</h1>
  <a href="${pageContext.request.contextPath}/dow">下载</a>
  </body>
</html>
