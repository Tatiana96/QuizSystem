<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String admin = (String) session.getAttribute("admin");

 if(admin!= null){%>
<a href="AllQuestion" class="list-group-item">Просмотреть все вопросы</a>
<a href="categories" class="list-group-item">Просмотреть все тесты</a>
<a href="NewQuestion" class="list-group-item">Просмотреть новые вопросы</a>
<a href="AllTransactions" class="list-group-item">Просмотреть поступившие средства</a>
<%}%>


