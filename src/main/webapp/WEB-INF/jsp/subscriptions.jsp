<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<u:html title="Subscriptions">

    <div class="row justify-content-md-center">
        <div class="col-2 ml-3">
            <u:leftbar/>
        </div>
        <div class="col-9">
            <div class="card border-0 bg-light shadow my-5">
                <div class="container-fluid p-5">
                    <div class="row m-auto">
                        <c:forEach var="user" items="${redirectedData.subscriptions}">
                            <div class="col-6">
                                <div class="card my-2">
                                    <div class="row no-gutters">
                                        <c:url var="alt" value="/img/empty.jpg"/>
                                        <div class="col-md-4 text-center my-auto">
                                            <c:if test="${user.img == null}">
                                                <img src="${alt}"
                                                     class="avatar img-circle img-thumbnail" alt="${alt}">
                                            </c:if>
                                            <c:if test="${user.img != null}">
                                                <c:choose>
                                                    <c:when test="${user.img eq ''}">
                                                        <img src="${alt}"
                                                             class="avatar img-circle img-thumbnail" alt="${alt}">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="data:image/jpeg;base64, ${user.img}"
                                                             class="avatar img-circle img-thumbnail" alt="${alt}">
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </div>
                                        <div class="card-body">
                                            <div class="card">
                                                <div class="card-body">
                                                    <a href="${pageContext.request.contextPath}/blog/user/${user.login}" class="card-link"><h5
                                                            class="card-title">${user.login}</h5></a>
                                                    <p class="font-weight-normal font-italic">${user.aboutMe}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

</u:html>