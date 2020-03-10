<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<u:html title="Search">
    <div class="row justify-content-md-center">
    <div class="col-2 ml-3">
        <u:leftbar/>
    </div>
    <div class="col-9">
        <div class="card border-0 bg-light shadow my-5">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <h2 class="text-danger text-center">${noContent}</h2>
                    <c:forEach var="post" items="${foundPosts}">
                            <u:post post="${post}"/>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</u:html>
