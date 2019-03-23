<%--
  Created by IntelliJ IDEA.
  User: geoge
  Date: 19/3/22
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${spittleList}" var="spittle">
    <div class="spittleMessage">
        <c:out value="${spittle.message}"/>
    </div>
    <span class="spittleTime">
        <c:out value="${spittle.time}"></c:out>
    </span>
    <!--
    <span class="spittleLocation">
    //这里没有填写位置的信息，如果舍Null,会报异常
    </span>
    -->
</c:forEach>
</body>
</html>
