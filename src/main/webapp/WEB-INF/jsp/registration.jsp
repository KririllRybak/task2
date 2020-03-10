
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="">News pit</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>

<div class="container my-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                     <div class="card-header text-center">
                         <div id="legend">
                    <legend class="">Register</legend>
                             <p class="text-danger text-center">
                                 ${emptyField}
                             </p>
                     </div>
                </div>
                <div class="card-body">
                    <form class="form-horizontal d-flex justify-content-center" action='${pageContext.request.contextPath}/blog/registration' method="POST">
                        <fieldset>
                            <div class="control-group">
                                <p class="text-danger text-center">
                                    ${existingLogin}
                                </p>
                                <label class="control-label" for="username">Login</label>
                                <div class="controls">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        <input type="text" id="username" name="login" placeholder="Your login "
                                               class="input-xlarge">
                                    </div>
                                    <p class="help-block">Login can contain any letters or numbers, without
                                        spaces</p>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="email">E-mail</label>
                                <div class="controls">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        <input type="text" id="email" name="email" placeholder="example@example.com" class="input-xlarge">
                                    </div>
                                    <p class="help-block">Please provide your E-mail</p>
                                </div>
                            </div>

                            <div class="control-group">
                                <p class="text-danger text-center">
                                    ${shortPass}
                                </p>
                                <label class="control-label" for="password">Password</label>
                                <div class="controls">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        <input type="password" id="password" name="password" placeholder="*****"
                                               class="input-xlarge">
                                    </div>
                                    <p class="help-block">Password should be at least 4 characters</p>
                                </div>
                            </div>

                            <div class="control-group">
                                <p class="text-danger text-center">
                                    ${compareFailed}
                                </p>
                                <label class="control-label" for="password_confirm">Password (Confirm)</label>
                                <div class="controls">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                        <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                                               class="input-xlarge">
                                    </div>
                                    <p class="help-block">Please confirm password</p>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-primary btn-block">Register</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
