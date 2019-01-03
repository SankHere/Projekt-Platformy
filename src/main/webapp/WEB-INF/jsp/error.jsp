<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 02.12.2017
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="shared/header.jsp"></jsp:include>
<html>
<title>
    Nieznany błąd
</title>
<body>
<div id="main" class="container">


        <h1>Domyślny widok błędu w Spring Boot</h1>
        <div>


            Wiadomość: <span>${message}</span></br>
            Wyjątek: <span>${exception}</span></br>
            <!--
                StackTrace: <span>${stackTrace}</span>
                -->


        </div>

</div>
</body>
</html>

<jsp:include page="shared/footer.jsp"></jsp:include>
