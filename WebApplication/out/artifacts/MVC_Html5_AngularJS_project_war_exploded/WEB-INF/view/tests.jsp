<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
	<title>Викторина</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Cimpatible" content="IE=edge">

	<link rel="stylesheet" href="/resources/css/reset.css" />
	<link rel="stylesheet" href="/resources/css/style.css" />
	<link rel="stylesheet" href="/resources/css/bootstrap_min.css" />

	<script type="text/javascript" src="/resources/js/jquery-1.11.3.js"></script>
</head>
<body>
<div class ="container">
	<%@ include file='/resources/partials/topNavigation.jsp'%>

	<div class="row body">

		<div class="col-md-4 col-xs-4">
			<div class="list-group">
				<%@ include file='/resources/partials/leftMenu.jsp'%>
			</div>

			<div class="list-group">
				<%@ include file='/resources/partials/adminMenu.jsp'%>
			</div>

			<div class="list-group">
				<%@ include file='/resources/partials/TopUsers.jsp'%>
			</div>

		</div>

		<div class="page-body col-md-8 col-xs-8">
			<h1>${nameCat}</h1>
			<ul style="margin: 20px;">
				<c:forEach var="test" items="${tests}">
					<li><a href="/test?category=${nameCat}&nameTest=${test}">${test}</a></li>
				</c:forEach>
			</ul>



			<% String admin2 = (String) session.getAttribute("admin");

				if(admin2!= null){%>

			<div class ="col-md-4">
				<h4><a href="/NewTest?category=${nameCat}">Добавить новый тест</a></h4>
			</div>
			<%}%>


				
		</div>

	</div>
</div>
</body>