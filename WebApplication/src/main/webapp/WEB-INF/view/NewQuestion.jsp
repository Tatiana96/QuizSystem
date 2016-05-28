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
            <h1>Новые вопросы</h1>

            <table class = "table table-striped table-bordered">
                <tbody>
                <tr>
                    <th>Вопрос</th>
                    <th>Тип вопроса</th>
                    <th>Ответы</th>
                    <th>Прав. ответы</th>
                    <th>Действие</th>
                </tr>

                <c:forEach var="question" items="${questions}" >
                    <tr id="${question.id}">
                        <td>${question.content}</td>
                        <td>${question.typeQuestion}</td>

                        <td><c:forEach var="answer" items="${question.answers}" >
                            ${answer}
                        </c:forEach></td>
                        <td><c:forEach var="coranswer" items="${question.correctAnswer}" >
                           ${coranswer}
                        </c:forEach></td>
                        <td><input type="button" value="Утвердить" onclick="Add(this)"><input type="button" value="Удалить" onclick="Remove(this)"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <script>

                function Remove(a){
                    var id = ($(a).parent()).parent().attr("id")

                    //$("#"+id).remove();
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/DeleteQuestion",
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
                function Add(a){
                    var id = ($(a).parent()).parent().attr("id")

                    //$("#"+id).remove();
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/AddQuestion",
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
