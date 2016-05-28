<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<h1>${nameTest}</h1>				
				<ul id="start">
					<c:forEach var="question" items="${questions}" >
						<li>${question.content}</li>
						<form method="post" id="${question.id}">
							<c:forEach var="answer" items="${question.answers}" >
								<input type="${question.typeQuestion}"  name="answer" value="${answer}">${answer}<br>
							</c:forEach>
						</form>
					</c:forEach>
				</ul>
				<input type="button" value="Ответить" onclick="Respond()">
			</div>

		<script>
			function Respond()
			{
				var questions = new Array();
				var j=0;
				$("#start").children().filter("form").each(function(i,elem) {
					var obj = new Object();
					obj["id"] = $(this).attr("id");
					$(this).children().filter($('input:checked')).each(function(k,el){

						obj["answer"] = $(this).val();
						console.log( $(this).val())
					})
					questions[j] = obj;
					j++;
				});
				 var nameTest = '${nameTest}';

				$.ajax({
					type: "POST",
					url: "http://localhost:8080/Answer/chekAnswer",
					data: {
						question: questions,
						size: j,
						nameTest: nameTest
					},
					dataType: "json",
					success: function (data) {
						//console.log("ok")
						document.location.href = "http://localhost:8080/testFinish?nameTest="+nameTest;
					}
				});
			}

		</script>

        </div>
	</div>
</body>
</html>