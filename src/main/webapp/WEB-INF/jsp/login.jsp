<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<fmt:bundle basename="build"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="">News pit</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<div class="card justify-content-center align-items-center" >
    <article class="card-body">
        <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
        <hr>
        <p class="text-success text-center">
        ${successfulRegistration}
        </p>
        <p class="text-danger text-center">
            ${wrongData}
            ${smtWrong}
        </p>
        <form action="${pageContext.request.contextPath}/blog/login" method="get">
            <div class="form-group col-sm-15">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="login" class="form-control" placeholder="login" type="text">
                </div>
            </div>
            <div class="form-group col-sm-15">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="******" type="password">
                </div>
            </div>
            <div class="form-group col-sm-15 ">
                <button type="submit" class="btn btn-primary btn-block"> Login  </button>
            </div>
                <c:url value="registation.jsp" var="itemUrl"/>
            <p class="text-capitalize text-center">If you are a new user - register</p>
        </form>
        <div class="col-sm-15">
        <form action="${pageContext.request.contextPath}/blog/registrationPage" method="get">
            <input type="submit" class="btn btn-primary btn-block" value="Registration"/>
        </form>
        </div>
    </article>
	<footer class="page-footer font-small blue">

  <div class="footer-copyright text-center py-3">© 2019 Copyright:
    <p>Build: <span>${applicationScope.number} </span></p>
    <p>Time: <span>${applicationScope.time} </span></p>
  </div>

</footer>
</div>
</body>
</html>
