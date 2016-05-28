<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String users[] = (String[]) session.getAttribute("users");%>
    <a href="#" class="list-group-item" style="background-color: #f8f8f8; border-color: #e7e7e7; color: #555">Топ игроков</a>
    <%for (String user:users){%>
        <a href="#" class="list-group-item"><%=user%></a>
    <%};
%>


