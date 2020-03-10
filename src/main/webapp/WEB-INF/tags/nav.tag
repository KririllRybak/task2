<%@tag language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope="application"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar sticky-top navbar-expand-sm bg-dark navbar-dark justify-content-end">
    <a class="navbar-brand rounded-circle" href="${pageContext.request.contextPath}/blog/main">
        <c:url value="/img/mainbrand.png" var="brand"/>
        <img src="${brand}" width="30" height="30" class="d-inline-block align-top" alt="">
        <ftm:message key="app_name" bundle="${value}"/>
    </a>
    <form action="${pageContext.request.contextPath}/blog/search" method="get" class="form-inline my-2 my-lg-0">
        <input name="content" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-light my-2 my-sm-0"><ftm:message key="search" bundle="${value}"/></button>
    </form>
    <div class="collapse navbar-collapse flex-grow-0 ml-auto mr-1 " id="navbarSupportedContent">
        <ul class="navbar-nav text-right">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false">
                    <ftm:message key="lang" bundle="${value}"/>
                </a>
                <div class="dropdown-menu">
                    <c:url value="/img/en.png" var="en"/>
                    <c:url value="/img/ru.png" var="ru"/>
                    <c:url value="/img/fr.png" var="fr"/>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/blog/changeLang/en"><img id="en" src="${en}" width="45" height="45" alt="">
                       <span class="font-weight-bold"> English</span></a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/blog/changeLang/ru"><img id="ru" src="${ru}" width="45" height="45" alt="">
                        <span class="font-weight-bold">Русский</span></a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/blog/changeLang/fr"><img id="fr" src="${fr}" width="45" height="45" alt="">
                        <span class="font-weight-bold">Français</span></a>
                </div>
            </li>
        </ul>
        <ul class="navbar-nav text-right">
            <li class="nav-item ">
                <a class="btn btn-outline-light mx-sm-1  my-sm-0" href="${pageContext.request.contextPath}/blog/postEditor"> <ftm:message
                        key="post_create" bundle="${value}"/></a>
            </li>
        </ul>
        <c:forEach items="${menu}" var="item">
            <c:if test="${item.name=='profile'}">
                <c:url value="${item.url}" var="itemUrl"/>
                <ul class="navbar-nav text-right">
                    <a class="navbar-brand" href="${itemUrl}">
                        <c:url var="alt" value="/img/empty.jpg"/>
                        <c:if test="${icon == null}">
                            <img id="photo" src="${alt}" width="30" height="30" alt="${alt}"
                                 class="m-auto rounded-circle">
                        </c:if>
                        <c:if test="${icon != null}">
                            <c:choose>
                                <c:when test="${icon eq ''}">
                                    <img id="photo" src="${alt}" width="30" height="30" alt="${alt}"
                                         class="m-auto rounded-circle">
                                </c:when>
                                <c:otherwise>
                                    <img id="photo" src="data:image/jpeg;base64, ${icon}" width="30" height="30"
                                         alt="${alt}"
                                         class="m-auto rounded-circle">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </a>
                </ul>
            </c:if>
            <c:if test="${not (item.name=='profile')}">
                <c:url value="${item.url}" var="itemUrl"/>
                <c:url value="${item.name}" var="itemName"/>
                <ul class="navbar-nav text-right m-auto">
                    <li class="nav-item "><a class="nav-link" href="${itemUrl}"><ftm:message key="${itemName}"
                                                                                             bundle="${value}"/></a>
                    </li>
                </ul>
            </c:if>
        </c:forEach>
    </div>
</nav>