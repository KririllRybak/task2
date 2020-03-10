<%@tag language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<div class="row">
    <div class="card h-75 mt-5  text-white col-2 bg-dark position-fixed border-info ">
        <div class="card-header text-center text-info">
            <h4><ftm:message key="categories" bundle="${value}"></ftm:message> </h4>
        </div>
        <div class="card-body text-info " id="sticky-sidebar" style=" overflow-y: auto">
            <div class="row">
                <c:forEach var="category" items="${categories}">
                    <a href="${pageContext.request.contextPath}/blog/category/${category.id}" class="btn btn-primary btn-info" type="submit">
                        <c:out value=" ${category.name}"/></a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>