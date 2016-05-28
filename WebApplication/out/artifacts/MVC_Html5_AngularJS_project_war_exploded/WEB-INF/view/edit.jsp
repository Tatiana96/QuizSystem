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
			<h1>Изменение профиля</h1>
			<form id="form" class="unauthorized login-form"  name="login-form" method="post">
				Введите новое имя: <input type="text" id="firstname" ><br>
				Введите новую фамилию: <input type="text" id="secondname" ><br>
				Введите новый Email: <input name="Email" id="email"><br>
				Введите старый пароль: <input type = "password" id="lastpassword" required><br>
				Введите новый пароль, если хотите его изменить<input type = "password" id="newpassword"><br>
				<div id="ErrMsg"></div>
				<input type="button" onclick="Edit()" value="Изменить">
			</form>
		</div>

		<script>
			function Edit()
			{
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/edit/dataEdit",
					data: {
						lastpassword: $("#lastpassword").val(),
						newpassword: $("#newpassword").val(),
						firstname: $("#firstname").val(),
						secondname: $("#secondname").val(),
						email: $("#email").val()
					},
					dataType: "json",
					success: function (data) {
						if (data.flag == true) {
							console.log('true');
							$("#ErrMsg").hide();
							document.location.href = "http://localhost:8080/";
						} else {
							$("#ErrMsg").html("Введены неверные данные");
							$("#ErrMsg").hide().slideDown("slow");
						}
					}
				});
				return false;
			}
		</script>
				
	</div>
</div>
</body>
</html>