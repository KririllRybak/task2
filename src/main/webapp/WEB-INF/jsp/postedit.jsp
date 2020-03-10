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
<u:html title="Edit post">
    <div class="container-fluid">
        <div class="card m-5">
            <div class="card-body">
                <form class="form" action="${pageContext.request.contextPath}/blog/editPost" method="post">
                    <div class="text-center font-italic">
                        <h3>
                            <ftm:message key="edit_post" bundle="${value}"/>
                            <hr align="center" width="500" size="2" color="#000000"/>
                        </h3>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4><ftm:message key="title" bundle="${value}"/></h4></label>
                            <input type="text" class="form-control" name="title"
                                   placeholder="Заголовок 1" value="${redirectedData.post.title}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <label><h4><ftm:message key="input_text" bundle="${value}"/></h4></label>
                            <textarea class="form-control" rows="5" name="text">${redirectedData.post.text}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="postId" value="${redirectedData.post.id}">
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
</u:html>