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
            <h1>Пополнение счета</h1>

            <form id="form" class="unauthorized login-form"  name="login-form" method="POST">
                <input type="text" placeholder="Введите сумму" name="summa" id="summa"><br>

                <div id="ErMsg"></div>
                <input type="button" onclick="add()" value="Пополнить">
                <!--<button id="start"  type="submit" data-loading-text="Отправляю.." onclick="LogIn()">Войти</button>-->
            </form>

        </div>
        <script>
            function add()
            {
                //e.stopPropagation();
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/addFunds/add",
                    data: {
                        summa: $("#summa").val()
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

