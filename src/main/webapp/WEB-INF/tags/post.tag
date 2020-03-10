<%@tag language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="post" required="true" rtexprvalue="true"
             type="by.epam.training.blog.domain.application_entity.ApplicationPost" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope="application"/>

<div class="card border-primary my-2">
    <div class="card-header">
        <div class="row">
            <div class="col-5">
                <c:url var="alt" value="/img/empty.jpg"/>
                <c:if test="${post.author.img == null}">
                    <img id="photo" src="${alt}" width="40" height="40" alt="${alt}"
                         class="m-auto rounded-circle">
                </c:if>
                <c:if test="${post.author.img != null}">
                    <c:choose>
                        <c:when test="${post.author.img eq ''}">
                            <img id="photo" src="${alt}" width="40" height="40" alt="${alt}"
                                 class="m-auto rounded-circle">
                        </c:when>
                        <c:otherwise>
                            <img id="photo" src="data:image/jpeg;base64, ${post.author.img}" width="40" height="40"
                                 alt="${alt}"
                                 class="m-auto rounded-circle">
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <a href="${pageContext.request.contextPath}/blog/user/${post.author.login}" class="card-link"><c:out value="${post.author.login}"/></a>
            </div>
            <div class="col-6">
                <div class="text-right">
                    <div class="btn-group btn-group-sm">
                        <c:choose>
                            <c:when test="${authorizedUser.role.roleId eq 0}">
                                <div class="col">
                                    <form method="post" action="${pageContext.request.contextPath}/blog/postChanges" class="form-inline">
                                        <input type="hidden" name="postId" value="${post.id}">
                                        <button class="btn btn-primary btn-danger" name="edit" value="delete">
                                            <ftm:message key="del" bundle="${value}"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="col">
                                    <form method="post" action="${pageContext.request.contextPath}/blog/postChanges" class="form-inline">
                                        <input type="hidden" name="postId" value="${post.id}">
                                        <button class="btn btn-primary btn-success" name="edit" value="update">
                                            <ftm:message key="edit_post" bundle="${value}"/>
                                        </button>
                                    </form>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${redirectedData.edit}">
                                    <div class="col">
                                        <form method="post" action="${pageContext.request.contextPath}/blog/postChanges" class="form-inline">
                                            <input type="hidden" name="postId" value="${post.id}">
                                            <button class="btn btn-primary btn-danger" name="edit" value="delete">
                                                <ftm:message key="del" bundle="${value}"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col">
                                        <form method="post" action="${pageContext.request.contextPath}/blog/postChanges" class="form-inline">
                                            <input type="hidden" name="postId" value="${post.id}">
                                            <button class="btn btn-primary btn-success" name="edit" value="update">
                                                <ftm:message key="edit_post" bundle="${value}"/>
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="card-img img-fluid">
        <u:carousel post="${post}"/>
    </div>
    <div class="card-body">
        <h5 class="card-title"><c:out value="${post.title}"/></h5>
        <p>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample${post.id}" role="button"
               aria-expanded="false" aria-controls="collapseExample${post.id}">
                <ftm:message key="show_text" bundle="${value}"/>
            </a>
        </p>
        <div class="collapse" id="collapseExample${post.id}">
            <p class="card-text"><c:out value="${post.text}"/></p>
        </div>
    </div>
    <ul class="list-group list-group-flush m-1">
        <li class="list-group-item">
            <h4><ftm:message key="categories" bundle="${value}"/>:</h4>
            <c:forEach var="category" items="${post.categories}">
                <c:out value=" ${category.name}"/>
            </c:forEach>
        </li>
    </ul>
    <div class="card-footer text-muted">
        <p>
            <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample1${post.id}" role="button"
               aria-expanded="false" aria-controls="collapseExample1${post.id}">
                <ftm:message key="show_comment" bundle="${value}"/>
            </a>
        </p>
        <div class="collapse" id="collapseExample1${post.id}">
            <form name="test" method="post" action="${pageContext.request.contextPath}/blog/addComment">
                <div class="form-group">
                    <label for="comment"><ftm:message key="comment" bundle="${value}"/>:</label>
                    <input type="hidden" name="postId" value="${post.id}">
                    <textarea maxlength="250" class="form-control" rows="5" id="comment" name="comment"></textarea>
                </div>
                <p><input class="btn btn-primary btn-success" type="submit"
                          value="<ftm:message key="submit" bundle="${value}"/>">
                    <input class="btn btn-primary btn-danger" type="reset"
                           value="<ftm:message key="res" bundle="${value}"/>"></p>
            </form>

            <c:forEach var="comment" items="${post.comments}">
                <div class="alert alert-success alert-dismissible mx-2" role="alert">
                    <c:url var="alt" value="/img/empty.jpg"/>
                    <c:if test="${comment.author.img == null}">
                        <img id="photo" src="${alt}" width="40" height="40" alt="${alt}"
                             class="m-auto rounded-circle">
                    </c:if>
                    <c:if test="${comment.author.img != null}">
                        <c:choose>
                            <c:when test="${comment.author.img eq ''}">
                                <img id="photo" src="${alt}" width="40" height="40" alt="${alt}"
                                     class="m-auto rounded-circle">
                            </c:when>
                            <c:otherwise>
                                <img id="photo" src="data:image/jpeg;base64, ${comment.author.img}" width="40" height="40"
                                     alt="${alt}"
                                     class="m-auto rounded-circle">
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:out value="${comment.author.login}"/>
                    <c:if test="${comment.author.id eq authorizedUser.id}">
                        <form method="post" action="${pageContext.request.contextPath}/blog/deleteComment">
                            <input type="hidden" name="commentId" value="${comment.id}">
                            <button type="submit" class="btn close"
                                    aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </form>
                    </c:if>
                    <p class="card-text"><c:out value="${comment.comment}"/></p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
