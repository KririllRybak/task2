<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope = "application"/>
<u:html title="Create post">
    <div class="container-fluid">
        <div class="card m-5">
            <div class="card-body">
                <form class="form" action="${pageContext.request.contextPath}/blog/createPost" method="post" enctype="multipart/form-data">
                    <div class="text-center font-italic">
                        <h3>
                            <ftm:message key="post_create" bundle="${value}"/>
                            <hr align="center" width="500" size="2" color="#0000dd"/>
                        </h3>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4><ftm:message key="title" bundle="${value}"/></h4></label>
                            <input type="text" class="form-control" name="title"
                                   placeholder="Заголовок 1">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4><ftm:message key="input_text" bundle="${value}"/></h4></label>
                            <textarea class="form-control" rows="5" name="text"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <p class="text-dark font-weight-bold font-italic">
                                <h4><ftm:message key="upload_img" bundle="${value}"/></h4>
                            <h6><ftm:message key="post_lable1" bundle="${value}"/></h6>
                            </p>
                            <div class="custom-file">
                                <input type="file" class="custom-file-input" id="customFile" name="imgs"
                                       multiple="multiple">
                                <label class="custom-file-label" for="customFile"><ftm:message key="choose_file" bundle="${value}"/></label>
                            </div>
                            <script>
                                $(".custom-file-input").on("change", function () {
                                    var fileName = $(this).val().split("\\").pop();
                                    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <div class="row">
                                <div class="col-4">
                                    <p class="text-danger font-weight-bold">${redirectedData.EmptyCategory}</p>
                                    <p class="text-dark font-weight-bold font-italic">
                                        <ftm:message key="post_lable2" bundle="${value}"/>
                                    </p>
                                    <select name="selectCategories" multiple>
                                        <c:forEach var="category" items="${redirectedData.categories}">
                                        <option value="${category.name}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <br>
                            <button class="btn btn-lg btn-success" type="submit">
                                <em class="glyphicon glyphicon-ok-sign"></em>
                                <ftm:message key="save_post" bundle="${value}"/>
                            </button>
                            <button class="btn btn-lg" type="reset">
                                <em class="glyphicon glyphicon-repeat"></em> <ftm:message key="res" bundle="${value}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</u:html>