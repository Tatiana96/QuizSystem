<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<a href="/categories" class="list-group-item">Тесты</a>
<a href="/AllStatistica" class="list-group-item">Статистика</a>

<%String logins=(String) session.getAttribute("login");

 if(logins!=null){%>
	<a href="edit" class="list-group-item">Настройки профиля</a>
	<a href="statistica" class="list-group-item">Моя статистика</a>
	<a href="transaction" class="list-group-item">Мои транзакции</a>
	<a href="propose" class="list-group-item">Предложить свой вопрос</a>
<%}%>

<a href="about" class="list-group-item">О проекте</a>