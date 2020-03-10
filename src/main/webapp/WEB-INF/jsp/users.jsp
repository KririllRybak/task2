<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope="application"/>
<u:html title="Users">
    <div class="container-fluid">
        <div class="card border-0 bg-light shadow my-5 p-4">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="users-tab" data-toggle="tab" href="#users" role="tab"
                       aria-controls="users"
                       aria-selected="true"><fmt:message bundle="${value}" key="users"/> </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="log-tab" data-toggle="tab" href="#log" role="tab"
                       aria-controls="log" aria-selected="false"><fmt:message bundle="${value}" key="log"/> </a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="users" role="tabpanel" aria-labelledby="users-tab">
                    <div class="container-fluid">
                        <div class="row">
                            <c:forEach var="user" items="${redirectedData.users}">
                                <div class="col-6">
                                    <div class="card  m-2">
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
                                            <div class="col-8">
                                                <div class="card-body">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <ul class="nav nav-tabs" id="${user.id}" role="tablist">
                                                                <li class="nav-item">
                                                                    <a class="nav-link active" id="info-tab${user.id}"
                                                                       data-toggle="tab"
                                                                       href="#info${user.id}" role="tab"
                                                                       aria-controls="info"
                                                                       aria-selected="true"><ftm:message key="info"
                                                                                                         bundle="${value}"/></a>
                                                                </li>
                                                                <li class="nav-item">
                                                                    <a class="nav-link" id="role-tab${user.id}"
                                                                       data-toggle="tab"
                                                                       href="#role${user.id}" role="tab"
                                                                       aria-controls="role"
                                                                       aria-selected="false"><ftm:message key="role"
                                                                                                          bundle="${value}"/></a>
                                                                </li>
                                                            </ul>
                                                            <div class="tab-content p-1" id="${user.id}">
                                                                <div class="tab-pane fade show active"
                                                                     id="info${user.id}"
                                                                     role="tabpanel"
                                                                     aria-labelledby="home-tab${user.id}">
                                                                    <div class="row">
                                                                        <div class="col">
                                                                            <h5 class="card-title">${user.login}</h5>
                                                                        </div>
                                                                        <div class="col">
                                                                            <form method="post"
                                                                                  action="${pageContext.request.contextPath}/blog/deleteUser"
                                                                                  class="form-inline">
                                                                                <input type="hidden" name="userId"
                                                                                       value="${user.id}">
                                                                                <button class="btn btn-sm btn-primary btn-danger"
                                                                                        name="edit" value="delete">
                                                                                    <ftm:message key="del"
                                                                                                 bundle="${value}"/>
                                                                                </button>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="tab-pane fade" id="role${user.id}"
                                                                     role="tabpanel"
                                                                     aria-labelledby="role-tab${user.id}">
                                                                    <form action="${pageContext.request.contextPath}/blog/changeRole" method="post">
                                                                        <h5><p class="font-weight-normal"><ftm:message
                                                                                key="role" bundle="${value}"/>:
                                                                            <span class="font-italic">${user.role.name}</span>
                                                                        </p>
                                                                        </h5>
                                                                        <div class="form-group">
                                                                            <input type="hidden" name="userId"
                                                                                   value="${user.id}">
                                                                        </div>
                                                                        <label for="roleSelect"><ftm:message
                                                                                key="change_role"
                                                                                bundle="${value}"/></label>
                                                                        <div class="form-group">
                                                                            <select name="role"
                                                                                    class="form-control form-control-sm"
                                                                                    id="roleSelect">
                                                                                <c:forEach var="role"
                                                                                           items="${redirectedData.roles}">
                                                                                    <option value="${role.roleId}">${role.name}</option>
                                                                                </c:forEach>
                                                                            </select>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <button type="submit"
                                                                                    class="mt-2 btn btn-sm btn-primary btn-info">
                                                                                <ftm:message key="edit_post"
                                                                                             bundle="${value}"/>
                                                                            </button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
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
                <div class="tab-pane fade" id="log" role="tabpanel" aria-labelledby="log-tab">
                    <div class="container-fluid">
                        <table class="table  table-bordered my-3">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col"><fmt:message key="login" bundle="${value}"/></th>
                                <th scope="col"><fmt:message key="entry_time" bundle="${value}"/></th>
                                <th scope="col"><fmt:message key="out_time" bundle="${value}"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${entries}" var="entry">
                                <tr>
                                    <td>${entry.login}</td>
                                    <td>${entry.enter}</td>
                                    <c:if test="${not empty entry.out}">
                                        <td>${entry.out}</td>
                                    </c:if>
                                    <c:if test="${ empty entry.out}">
                                        <td><fmt:message key="online" bundle="${value}"/></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</u:html>