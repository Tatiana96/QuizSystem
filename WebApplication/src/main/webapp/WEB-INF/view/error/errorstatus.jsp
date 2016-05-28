<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Something wrong</jsp:attribute>
    <jsp:body>
        <!-- Page Content -->
        <div class="container">

            <!-- Page Heading/Breadcrumbs -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">404
                        <small>Page Not Found</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="/">Home</a>
                        </li>
                        <li class="active">404</li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <c:url value="/about.html" var="about"/>
            <c:url value="/file.html" var="file"/>
            <c:url value="/jdbc.html" var="jdbc"/>
            <c:url value="/email.html" var="email"/>
            <c:url value="/rest.html" var="rest" />
            <c:url value="/orm.html" var="orm" />
            <c:url value="/runtimeException.html" var="runtimeException" />
            <c:url value="/jstl.html" var="jstl" />
            <div class="row">

                <div class="col-lg-12">
                    <div class="jumbotron">
                        <h1><span class="error-404">404</span>
                        </h1>
                        <p>Ошибочка 404. Страница настроена в web.xml error-page. Можете перейти к:</p>
                        <ul>
                            <li>
                                <a href="/">Home</a>
                            </li>
                            <li>
                                <a href="${about}">About</a>
                            </li>
                            <li>
                                Tutorial
                                <ul>
                                    <li>
                                        <a href="${file}">Загрузка файла PDF и Excel</a>
                                    </li>
                                    <li>
                                        <a href="${jdbc}">JDBC c JDBCTemplates</a>
                                    </li>
                                    <li>
                                        <a href="${email}">Java Email API and Spring Mail</a>
                                    </li>
                                    <li>
                                        <a href="${rest}">Rest Services</a>
                                    </li>
                                    <li>
                                        <a href="${orm}">Spring MVC и Hibernate 5</a>
                                    </li>
                                    <li>
                                        <a href="${runtimeException}">Runtime Exception</a>
                                    </li>
                                    <li>
                                        <a href="${jstl}">JSTL Example</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>

            <hr>
        </div>
    </jsp:body>
</page:template>
