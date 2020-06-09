<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2020/3/24
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="utf-8">
    <title>菜单管理</title>
    </head>
    <!-- 如果使用frameset 的包含页面  主页面不能有 body-->
    <frameset cols="250,*" border="1">
    <frame src="${ctx }/sys/toMenuLeft.action" name="left">
    <frame src="${ctx }/sys/toMenuRight.action" name="right">
    </frameset>

    </html>
