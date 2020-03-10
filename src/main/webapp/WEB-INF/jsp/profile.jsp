<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<u:html title="Profile">
    <div class="container">
        <div class="card border-0 bg-light shadow my-5">
            <div class="m-3">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="list-group">
                            <li class="list-group-item text-center">
                                <c:url var="alt" value="/img/empty.jpg"/>
                                <c:if test="${authorizedUser.img == null}">
                                    <img src="${alt}"
                                         class="avatar img-circle img-thumbnail" alt="${alt}">
                                </c:if>
                                <c:if test="${authorizedUser.img != null}">
                                    <c:choose>
                                        <c:when test="${authorizedUser.img eq ''}">
                                            <img src="${alt}"
                                                 class="avatar img-circle img-thumbnail" alt="${alt}">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="data:image/jpeg;base64, ${authorizedUser.img}"
                                                 class="avatar img-circle img-thumbnail" alt="${alt}">
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </li>
                            <li class="list-group-item">
                                <form method="post" action="${pageContext.request.contextPath}/blog/updateProfile" enctype="multipart/form-data">
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="customFile" name="file">
                                        <label class="custom-file-label" for="customFile"><ftm:message key="choose_file" bundle="${value}"/></label>
                                    </div>
                                    <input name="file" type="submit" class="btn btn-block btn-success mt-2"
                                           value="Edit">
                                </form>
                                <script>
                                    $(".custom-file-input").on("change", function () {
                                        var fileName = $(this).val().split("\\").pop();
                                        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                    });
                                </script>
                            </li>
                        </div>
                        <br>
                        <ul class="list-group">
                            <li class="list-group-item text-muted"><ftm:message key="active" bundle="${value}"/> <i class="fa fa-dashboard fa-1x"></i></li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong><ftm:message key="post" bundle="${value}"/></strong></span>
                                    ${fn:length(authorizedUser.appPosts)}
                            </li>
                            <li class="list-group-item text-right"><span
                                    class="pull-left"><strong><ftm:message key="subscribers" bundle="${value}"/></strong></span> ${fn:length(authorizedUser.subscribers)}
                            </li>
                            <li class="list-group-item text-right"><span
                                    class="pull-left"><strong><ftm:message key="subscriptions" bundle="${value}"/></strong></span> <a
                                    href="${pageContext.request.contextPath}/blog/subscriptions">${redirectedData.subscriptions}</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-8">
                        <div class="col-sm-10"><h1 class="display-3">${authorizedUser.login}</h1></div>
                        <div class="alert alert-success" role="alert">
                            <h4 class="alert-heading"><ftm:message key="about_me" bundle="${value}"/></h4>
                            <p class="font-italic">
                                <c:choose>
                                    <c:when test="${authorizedUser.aboutMe==null}">
                                        <ftm:message key="empty" bundle="${value}"/>
                                    </c:when>
                                    <c:otherwise>
                                        ${authorizedUser.aboutMe}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <hr>
                            <p class="text-left font-weight-bold"><ftm:message key="acc_creation" bundle="${value}"/>:</p>
                            <p class="font-weight-light">${authorizedUser.creationStamp}</p>
                            <p class="text-left font-weight-bold"><ftm:message key="change_acc" bundle="${value}"/>:</p>
                            <p class="font-weight-light">${authorizedUser.changeTime}</p>
                        </div>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="info-tab" data-toggle="tab" href="#info" role="tab"
                                   aria-controls="info" aria-selected="true"><ftm:message key="info" bundle="${value}"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="post-tab" data-toggle="tab" href="#post" role="tab"
                                   aria-controls="post" aria-selected="false"><ftm:message key="post" bundle="${value}"/></a>
                            </li>
                        </ul>
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade show active" id="info" role="tabpanel"
                                 aria-labelledby="info-tab">
                                <div class="card">
                                    <div class="card-body">
                                        <form class="form" action="${pageContext.request.contextPath}/blog/updateProfile" method="post">
                                            <p class="text-danger">${incorrectConfirm}</p>
                                            <div class="form-group">
                                                <div class="col-xs-6">
                                                    <label for="email"><h4><ftm:message key="email" bundle="${value}"/></h4></label>
                                                    <input type="email" class="form-control" name="email" id="email"
                                                           value="${authorizedUser.email}"
                                                           title="enter your email.">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-6">
                                                    <label for="password"><h4><ftm:message key="pass" bundle="${value}"/></h4></label>
                                                    <input type="password" class="form-control" name="password"
                                                           id="password"
                                                           placeholder="password" title="enter your password.">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-6">
                                                    <label for="password"><h4><ftm:message key="confirm" bundle="${value}"/></h4></label>
                                                    <input type="password" class="form-control" name="confirm"
                                                           placeholder="password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-xs-6">
                                                    <label><h4><ftm:message key="change_about_me" bundle="${value}"/></h4></label>
                                                    <textarea class="form-control" rows="5" name="aboutMe"></textarea>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <br>
                                                    <button class="btn btn-lg btn-success" type="submit"><i
                                                            class="glyphicon glyphicon-ok-sign"></i> <ftm:message key="save_post" bundle="${value}"/>
                                                    </button>
                                                    <button class="btn btn-lg" type="reset"><i
                                                            class="glyphicon glyphicon-repeat"></i> <ftm:message key="res" bundle="${value}"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="post" role="tabpanel" aria-labelledby="post-tab">
                                <div class="container-fluid">
                                    <div class="card">
                                        <div class="card-body" id="sticky-sidebar" style=" overflow-y: auto">
                                            <c:forEach var="post" items="${authorizedUser.appPosts}">
                                                <div class="p-2">
                                                    <u:post post="${post}"/>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</u:html>
