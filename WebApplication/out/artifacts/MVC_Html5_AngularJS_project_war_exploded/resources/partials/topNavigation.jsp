<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="/">Викторина</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<%	String login=(String) session.getAttribute("login");
				String balance = (String) session.getAttribute("balance");

				if(login==null){%>
			<li><a href="login">Войти</a></li>
			<li><a href="singup">Зарегистрироваться</a></li>
			<%}else{%>
			<li><a href="#">Привет, <%=login%></a></li>
			<li><a href="/addFunds">Баланс: <%=balance%></a></li>
			<li><a href="logout">Выйти</a></li>
			<%}%>
		</ul>
	</div><!-- /.navbar-collapse -->
</nav>