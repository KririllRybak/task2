<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<u:html title="Category">
    <div class="row justify-content-md-center">
        <div class="col-2 ml-3">
            <u:leftbar/>
        </div>
        <div class="col-9">
            <div class="card border-0 bg-light shadow my-5">
                <div class="container-fluid p-5">
                    <p>
                        <a class="btn btn-primary mb-3" data-toggle="collapse"
                           href="#collapseExample${authorizedUser.id}" role="button"
                           aria-expanded="false" aria-controls="collapseExample${authorizedUser.id}">
                            <ftm:message key="create_category" bundle="${value}"/>
                        </a>
                    </p>
                    <div class="collapse mb-3" id="collapseExample${authorizedUser.id}">
                        <form method="post" action="${pageContext.request.contextPath}/blog/createCategory">
                            <label><ftm:message key="input_category" bundle="${value}"/></label>
                            <div class="row">
                                <div class="col">
                                    <input name="categoryName" type="text" class="form-control">
                                </div>
                                <div class="col">
                                    <button class="btn btn-primary" type="submit"><ftm:message key="create_category" bundle="${value}"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <p class="text-info font-weight-normal">
                                ${redirectedData.isExists}
                                ${redirectedData.success}
                                ${redirectedData.delete}
                        </p>
                    </div>
                    <div class="row m-auto">
                        <c:forEach var="category" items="${categories}">
                            <div class="alert alert-success alert-dismissible mx-2" role="alert">
                                <strong>${category.name}</strong>
                                <form method="post" action="${pageContext.request.contextPath}/blog/deleteCategory">
                                    <input type="hidden" name="categoryId" value="${category.id}">
                                    <button type="submit" class="btn close"
                                            aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

</u:html>