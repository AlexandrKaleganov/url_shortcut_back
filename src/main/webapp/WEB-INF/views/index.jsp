<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en">
<%
    String header = "Apache Tomcat";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <script charset="UTF-8" type="text/javascript">
        <%@include file="/WEB-INF/views/js/index.js"%>
    </script>
    <title>index</title>
</head>
<body>
<h2><%= header %></h2>
<p>Today <%= new java.util.Date() %></p>
</body>
</html>
