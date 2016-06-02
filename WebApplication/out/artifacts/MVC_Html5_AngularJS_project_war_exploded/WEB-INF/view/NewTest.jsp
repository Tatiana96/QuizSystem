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
            <h1>Новый тест</h1>

            Введите название теста: <input type="text" id="Name"><br>
            Введите описание теста: <input type="text" id="About"><br>
            Введите стоимость теста: <input type="text" id="Cost"><br>

            </br>
            Добавьте вопросы:
            <form method="post" id="questions">
                <select class="qu">
                    <c:forEach var="question" items="${questions}" >
                        <option value="${question}" name="${question}">${question}</option>
                    </c:forEach>
                </select><br>
                <select class="qu">
                    <c:forEach var="question" items="${questions}" >
                        <option value="${question}" name="${question}">${question}</option>
                    </c:forEach>
                </select><br>
                <select class="qu">
                    <c:forEach var="question" items="${questions}" >
                        <option value="${question}" name="${question}">${question}</option>
                    </c:forEach>
                </select><br>
                <input type="button" onclick="AddQ()" id="addQ" value="Добавить вопрос"><br>
            </form>

            <input type="button" onclick="Add()" id="add" value="Добавить тест"><br>

            <script>
                function AddQ(){
                $('<select class="qu"><c:forEach var="question" items="${questions}" ><option value="${question}" name="${question}">${question}</option></c:forEach></select><br>').insertBefore($("#addQ"));
                }

                function Add(){
                    var questions = new Array();

                    var j=0;
                    $("#questions").children().filter('.qu').each(function(i,elem) {
                        var obj = new Object();
                        obj["content"] = $(this).find('option:selected').text();
                        questions[j] = obj;
                        j++;
                    });


                    var category = '${category}';
                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/AddTest",
                        data: {
                            name:  $('#Name').val(),
                            category: category,
                            about:  $('#About').val(),
                            cost:  $('#Cost').val(),
                            questions:questions,
                            size:j
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
</div>
</body>
</html>