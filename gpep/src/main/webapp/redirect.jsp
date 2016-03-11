<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<html>
<head>
<title>SELECT Operation</title>
</head>
<body>
 
<sql:setDataSource var="snapshot" driver="org.postgresql.Driver"
     url="jdbc:postgresql://localhost:5433/FREE"
     user="postgres"  password="0000"/>
 
<sql:query dataSource="${snapshot}" var="result">
SELECT groupe from utilisateur where login='<%=request.getUserPrincipal().getName() %>';
</sql:query>
 
 <c:forEach var="row" items="${result.rows}">
	<c:if test="${row.groupe==1}">
		<% response.sendRedirect("/GPEP/pages/ADMIN/menu.jsf");  %>
	</c:if>
	<c:if test="${row.groupe==2}">
		<% response.sendRedirect("/GPEP/pages/AGENT/menu.jsf");  %>
	</c:if>
	<c:if test="${row.groupe==3}">
		<% response.sendRedirect("/GPEP/pages/RESPCENTRAL/menu.jsf");  %>
	</c:if>
	<c:if test="${row.groupe==4}">
		<% response.sendRedirect("/GPEP/pages/RESPTERRITORIAL/menu.jsf");  %>
	</c:if>
</c:forEach>
