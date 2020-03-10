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
<u:html title="User">
    <c:url value="${pageContext.request.contextPath}/blog/user/${redirectedData.someUser.login}" var="subscribe"/>
    <div class="container">
        <div class="card border-0 bg-light shadow my-5">
            <div class="m-3">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="list-group">
                            <li class="list-group-item">
                                <c:url var="alt" value="/img/empty.jpg"/>
                                <c:if test="${redirectedData.someUser.img == null}">
                                    <img src="${alt}"
                                         class="avatar img-circle img-thumbnail" alt="${alt}">
                                </c:if>
                                <c:if test="${redirectedData.someUser.img != null}">
                                    <c:choose>
                                        <c:when test="${redirectedData.someUser.img eq ''}">
                                            <img src="${alt}"
                                                 class="avatar img-circle img-thumbnail" alt="${alt}">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="data:image/jpeg;base64, ${redirectedData.someUser.img}"
                                                 class="avatar img-circle img-thumbnail" alt="${alt}">
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </li>
                        </div>
                        <br>
                        <ul class="list-group">
                            <li class="list-group-item text-muted"><ftm:message key="active" bundle="${value}"/> <i class="fa fa-dashboard fa-1x"></i></li>
                            <li class="list-group-item text-right"><span class="pull-left"><strong><ftm:message key="post" bundle="${value}"/></strong></span>
                                    ${fn:length(redirectedData.someUser.appPosts)}
                            </li>
                            <li class="list-group-item text-right"><span
                                    class="pull-left"><strong><ftm:message key="subscribers" bundle="${value}"/></strong></span> ${fn:length(redirectedData.someUser.subscribers)}
                            </li>
                        </ul>
                    </div>
                    <div class="col-sm-8">
                        <div class="col-sm-10">
                            <div class="row">
                                <div class="col-8">
                                    <h1 class="display-3">${redirectedData.someUser.login}</h1>
                                </div>
                                <div class="col-4">
                                    <c:choose>
                                        <c:when test="${redirectedData.btn==0}">
                                            <form action="${subscribe}" method="post">
                                                <div class="text-center mt-4">
                                                    <input type="hidden" name="subscription" value="unsub">
                                                    <button type="submit" class="btn btn-primary btn-danger"><ftm:message key="unsub" bundle="${value}"/></button>
                                                </div>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="${subscribe}" method="post">
                                                <div class="text-center mt-4">
                                                    <input type="hidden" name="subscription" value="sub">
                                                    <button class="btn btn-primary  btn-success"><ftm:message key="sub" bundle="${value}"/></button>
                                                </div>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
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
                                        <div class="alert alert-success" role="alert">
                                            <h4 class="alert-heading"><ftm:message key="about_me" bundle="${value}"/></h4>
                                            <p class="font-italic">
                                                <c:choose>
                                                    <c:when test="${redirectedData.someUser.aboutMe==null}">
                                                        <ftm:message key="empty" bundle="${value}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${redirectedData.someUser.aboutMe}
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                            <hr>
                                            <p class="text-left font-weight-bold"><ftm:message key="acc_creation" bundle="${value}"/>:</p>
                                            <p class="font-weight-light">${redirectedData.someUser.creationStamp}</p>
                                            <p class="text-left font-weight-bold"><ftm:message key="change_acc" bundle="${value}"/>:</p>
                                            <p class="font-weight-light">${redirectedData.someUser.changeTime}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="post" role="tabpanel" aria-labelledby="post-tab">
                                <div class="container-fluid">
                                    <div class="card">
                                        <div class="card-body p-1" id="sticky-sidebar" style=" overflow-y: auto">
                                            <c:forEach var="post" items="${redirectedData.someUser.appPosts}">
                                                <div class="p-1">
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