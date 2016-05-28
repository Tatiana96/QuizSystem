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
            <h1>Поступившие транзакции</h1>

            <table class = "table table-striped">
                <tbody>
                <tr>
                    <th>Плательщик</th>
                    <th>Дата платежа</th>
                    <th>Сумма платежа</th>
                    <th>Действие</th>
                </tr>

                <c:forEach var="transaction" items="${transactions}" >
                    <tr id="${transaction.id}">
                        <td>${transaction.idlogin}</td>
                        <td>${transaction.date}</td>
                        <td>${transaction.summa}</td>
                        <td><input type="button" value="Утвердить" onclick="Add(this)"></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <script>
                function Add(a){
                    var id = ($(a).parent()).parent().attr("id")

                    //$("#"+id).remove();
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/AddTransactions",
                        data: {
                            id: id
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.flag == true) {
                                $("#"+id).remove();
                            }
                        }
                    });
                }
            </script>

        </div>

    </div>
</div>
</body>
</html>
