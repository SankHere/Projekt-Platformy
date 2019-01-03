<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css" />
    <link rel="stylesheet" type="text/css"
          href="statics/css/main.css" />
</head>
<body class="card">
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="registration"></c:param>
</c:import>
<div id="main" class="card-body container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 ">
            <h1>Właśnie zostałeś zarejestrowany w systemie!</h1>
            Twoje konto nie jest jeszcze aktywne (flaga w bazie enabled=false),<br/>
            Po aktywacji konta możesz się  <a href="/login">zalogować</a>
        </div>
    </div>

</div>
<jsp:include page="shared/footer.jsp"/>
</body>
</html>
