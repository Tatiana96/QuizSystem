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

		</div>

		<div class="page-body col-md-8 col-xs-8">
				<h1>${nameTest}</h1>
				
				<div class="row param">
					<div class="col-md-5 txt">Категория теста:</div>
					<div class="col-md-7"> ${category}</div>
				</div>
				<div class="row param">
					<div class="col-md-5 txt">Стоимость</div>
					<div class="col-md-7">${cost} рублей</div>
				</div>
				<div class="row param">
					<div class="col-md-5 txt">Описание теста:</div>
					<div class="col-md-7">${about}</div>
				</div>
				<div class="row param">
					<div class="col-md-5 txt">Cтатистика:</div>
					<div class="col-md-7">
						<table>
						  <tr>
						    <td>Всего прошли</td>
						    <td>${total}</td>
						  </tr>
						  <tr>
						    <td>Успешно прошли</td>
						    <td>${best}</td>
						  </tr>
						</table>
					</div>
				</div>

			<input type="button" onclick="GenerKod()" value="Получить код теста"><br>
			<div id="Msg"></div>

			<% if(login!=null){
				Integer costUser = Integer.parseInt((String)balance);
				Integer costTest = Integer.parseInt((String)session.getAttribute("costTest"));
				if (costUser >= costTest) {%>
					<a href="/solve?nameTest=${nameTest}">Пройти</a><br>
					<div id="comment">
						<form>
							Введите свой комментарий: <input type="text" id="com">
							<input type="button" onclick="Add()" id="add" value="Добавить"><br>
						</form>
						<table class = "table table-striped">
							<tbody>
							<tr>
								<th>Комментарии других пользователей</th>

							</tr>
							<c:forEach var="comment" items="${comments}" >
								<tr>
									<td>${comment}</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				<%}else {%>
						<a href="/addFunds">Пополнить счет</a>
					<%}
				}%>


			<script>
				function Add(){
					var test = '${nameTest}';
					$.ajax({
						type: "POST",
						url: "http://localhost:8080/CommentAdd",
						data: {
							content: $("#comm").val(),
							test: test
						},
						dataType: "json",
						success: function (data) {
							document.location.href = "http://localhost:8080/";
						}
					});
					return false;
				}

				function GenerKod(){
					var test = '${nameTest}';
					$.ajax({
						type: "POST",
						url: "http://localhost:8080/GetKod",
						data: {
							name: test
						},
						dataType: "json",
						success: function (data) {
								$("#Msg").html("Код теста: "+data.msg);
								$("#Msg").hide().slideDown("slow");
						}
					});
					return false;
				}
			</script>
				
		</div>
				
	</div>
</div>
</body>
</html>