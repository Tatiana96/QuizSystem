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
            <h1>Регистрация</h1>
            <form id="form" class="unauthorized login-form"  name="login-form" method="post">
                <input type="text" placeholder="Имя" id="firstname" required><br>
                <input type="text" placeholder="Фамилия" id="secondname" required><br>
                <input placeholder="Email" id="Email" type="email"><br>
                <input type="text" placeholder="Логин" id="login" required><br>
                <input type = "password" placeholder="Пароль" id="password" required><br>
                <div id="ErrMsg"></div>
                <input type="button" onclick="SingUp()" value="Зарегестрироваться">
            </form>
        </div>


        <script>
            function SingUp()
            {
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/LoginAuth/chekAuth",
                    data: {
                        login: $("#login").val(),
                        password: $("#password").val(),
                        firstname: $("#firstname").val(),
                        secondname: $("#secondname").val(),
                        Email: $("#Email").val()
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.flag == true) {
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

