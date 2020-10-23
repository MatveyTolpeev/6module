<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.service.db.servlets.FileDir" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Title</title>
    <style>
        .d {
            background-image:url(https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Clipboard.png);
            width:16px;
            height:16px;
            display:inline-block;
            vertical-align:bottom;
        }
        .f {
            background-image:url(https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Script.png);
            width:16px;
            height:16px;
            display:inline-block;
            vertical-align:bottom;
        }
        .b {
            background-image:url(https://cdn4.iconfinder.com/data/icons/6x16-free-application-icons/16/Trackback.png);
            width:16px;
            height:16px;
            display:inline-block;
            vertical-align:bottom;
        }
    </style>
</head>
<body>

<p>Current directory: ${name}</p>
<p>Time: ${time}</p>

<br>

<div>
    <c:forEach items="${dirs}" var="filedir">
        <div>
            <c:if test="${filedir.directory == true}">
            <span class="d"></span> <a href="${filedir.name}">${filedir.shortName}</a>
            </c:if>

        <c:if test="${filedir.directory == false}">
            <span class="f"></span> <a href="${filedir.name}">${filedir.shortName}</a>
        </c:if>
        </div>
    </c:forEach>
</div>
<form method="get" action="<c:url value='/login'/>">
    <input type="submit" value="login"/>
</form>
</body>
</html>
