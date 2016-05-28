<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		</div>

		<div class="page-body col-md-8 col-xs-8">
				<h1>Тесты</h1>
				<div class="row" >
					<c:forEach var="category" items="${categories}">
						<div class ="col-md-4">
							<h4><a href="/tests?category=${category}">${category}</a></h4>
						</div>
					</c:forEach>

				</div>
			<input type="button" value="Пройти мультитест" onclick="document.location.href = 'http://localhost:8080/MultyTest'">

			<% String admin1 = (String) session.getAttribute("admin");

				if(admin1!= null){%>

			<div class ="col-md-4">
				<h4><a href="/NewCategory">Добавить новую категорию</a></h4>
			</div>
			<%}%>
		</div>
				
	</div>
</div>
</body>
</html>