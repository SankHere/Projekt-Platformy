<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 20.10.2018
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<head>

    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css?<%=new Random().nextLong()%>" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />

</head>

<html>


<body class="card-header">



<nav id="header" class="navbar navbar-expand bg-dark flex-column flex-md-row">
    <div class="navbar-nav-scroll">
        <ul class="nav nav-tabs bg-dark">

            <li class="nav-item">
                <a class="nav-link ${param['pageName'] eq 'home' ?'active':''}"  href="/" >Strona główna</a>
            </li>

            <li class="nav-item">
                <a class="nav-link ${param['pageName'] eq 'registration' ?'active':''}"  href="/registrationForm.html">Rejestracja</a>
            </li>
        </ul>
    </div>

    <ul class="nav-tabs navbar-nav ml-md-auto bg-dark">
        <security:authorize access="isAuthenticated()">

            <label style="color:yellow; margin-top: 20px;">
                Witaj <security:authentication property="principal.username"/>!
            </label>

        <li class="nav-item">
            <a class="nav-link" href="#" onclick="document.getElementById('logout').submit()">Wyloguj się</a>
            <form action="/logout" id="logout" method="post" style="display: none;">
                <security:csrfInput/>
            </form>
        </li>
        </security:authorize>


        <security:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link ${param['pageName'] eq 'loginForm' ?'active':''}" href="/login">Zaloguj się</a>
            </li>
        </security:authorize>
    </ul>

</nav>



</body>
</html>


