<%--
  Created by IntelliJ IDEA.
  User: grzesiek
  Date: 08.10.2017
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>

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
<jsp:include page="shared/header.jsp">
<jsp:param name="pageName" value="home"/>
</jsp:include>
<div id="main" class="card-body">
    <span style="font-size: 30pt">
        Strona główna
    </span>
</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>