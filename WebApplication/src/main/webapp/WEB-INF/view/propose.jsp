<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <title>Викторина</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Cimpatible" content="IE=edge">

    <link rel="stylesheet" href="/resources/css/reset.css" />
    <link rel="stylesheet" href="/resources/css/bootstrap_min.css" />
    <link rel="stylesheet" href="/resources/css/style.css" />


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
            <h1>Свой вопрос</h1>

                Введите содержание вопроса: <input type="text" id="question"><br>
                Выберите тип ответа на вопрос:
                    <form method="post" id="typeQuestion">
                        <input type="radio"  name="answer" value="checkbox">CheckBox (Выбор нескольких ответов)<br>
                        <input type="radio"  name="answer" value="radio">RadioButton (Выбор одного ответа)<br>
                        <input type="radio"  name="answer" value="text">Input (Развернутый ответ)<br>
                    </form>

                Введите ответы:
                    <form method="post" id="answers">
                        <input type="text" ><br>
                        <input type="text" ><br>
                        <input type="text" ><br>
                        <input type="text"> <br>
                        <input type="button" onclick="Add()" id="add" value="Добавить"><br>
                    </form>
                Введите правильный ответ(ы):
                    <form method="post" id="correctanswers">
                        <input type="text" ><br>
                        <input type="button" onclick="AddA()" id="addA" value="Добавить"><br>
                    </form>
                Выберите тест, к которому должне относиться вопрос:
                    <form method="post" id="tests">
                        <select id="test">
                            <option disabled>Выберите тест</option>
                            <c:forEach var="test" items="${tests}" >
                                <option value="${test}">${test}</option>
                            </c:forEach>
                        </select>
                    </form>
                <div id="ErrMsg"></div>
                <input type="button" onclick="propose()" value="Предложить">



            <script>
                function propose(){

                    var contentQuestion = $('#question').val();

                    var typeQuestion = $('#typeQuestion').children().filter($('input:checked')).val()

                    var test = $("#test option:selected").text();

                    var answers = new Array();
                    var j=0;
                    $("#answers").children().filter('input[type="text"]').each(function(i,elem) {

                        var obj = new Object();
                        obj["answer"] = $(this).val();
                        answers[j]=obj;
                        j++;
                    });
                    var sizeAnswers = j;

                    var correctAnswers = new Array();
                    j=0;
                    $("#correctanswers").children().filter('input[type="text"]').each(function(i,elem) {
                        var obj = new Object();
                        obj["answer"] = $(this).val();
                        correctAnswers[j]=obj;
                        j++;
                    });
                    var sizeCorrectAnswers = j;

                    $.ajax({
                        type: "POST",
                        url: "http://localhost:8080/propose",
                        data: {
                            contentQuestion: contentQuestion,
                            typeQuestion: typeQuestion,
                            answers: answers,
                            correctAnswers:correctAnswers,
                            test: test,
                            sizeCorrectAnswers:sizeCorrectAnswers,
                            sizeAnswers:sizeAnswers
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

                function Add(){
                    $("<input type='text' ><br>").insertBefore($("#add"));
                }
                function AddA(){
                    $("<input type='text' ><br>").insertBefore($("#addA"));
                }
            </script>

        </div>

    </div>
</div>
</body>
</html>
