<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tytuł strony</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />

</head>


<body class="card">
<jsp:include page="../shared/header.jsp">
    <jsp:param name="pageName" value="home"/>
</jsp:include>
<div id="main" class="card-body">

    <div>
        <h1>Nieznany błąd</h1>
        <div>
            URL żądania: ${url}<br/>
            Wyjątek: ${exception.message}
            <!--
                Wyjątek ten będzie widoczny w źródle strony, ale nie dla użytkownika
            <c:forEach items="${exception.stackTrace}" var="st">
                ${st}
            </c:forEach>
            -->
        </div>
    </div>
</div>
<jsp:include page="../shared/footer.jsp"/>
</body>
</html>