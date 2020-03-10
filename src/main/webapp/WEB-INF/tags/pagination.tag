<%@tag language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <fmt:setLocale value="${cookie['locale'].value}" scope="application"/> --%>
<fmt:setLocale value="${locale}" scope="application"/>
<fmt:setBundle basename="lang" var="value" scope="application"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${currentPage == 1}">
                <c:choose>
                    <c:when test="${numberOfPages == 0}">
                        <li class="page-item disabled">
                            <a class="page-link " href="${pageContext.request.contextPath}/blog/main/${currentPage-1}"><ftm:message key="prev_page"
                                                                                                  bundle="${value}"/> </a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage}">${currentPage}<span
                                    class="sr-only"></span></a>
                        </li>
                        <li class="page-item disabled"><a class="page-link"
                                                          href="${pageContext.request.contextPath}/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                        <li class="page-item disabled">
                            <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage+1}"><ftm:message key="next_page"
                                                                                                 bundle="${value}"/></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link " href="${pageContext.request.contextPath}/blog/main/${currentPage-1}"><ftm:message key="prev_page"
                                                                                                  bundle="${value}"/> </a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage}">${currentPage}<span
                                    class="sr-only"></span></a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage+1}"><ftm:message key="next_page"
                                                                                                 bundle="${value}"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${currentPage == numberOfPages}">
                <li class="page-item ">
                    <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage-1}"><ftm:message key="prev_page"
                                                                                         bundle="${value}"/> </a>
                </li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage-1}">${currentPage-1}</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">${currentPage}<span class="sr-only"></span></a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage+1}"><ftm:message key="next_page"
                                                                                         bundle="${value}"/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled">
                    <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage-1}" tabindex="-1"><ftm:message key="prev_page"
                                                                                                       bundle="${value}"/> </a>
                </li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage-1}">${currentPage-1}</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">${currentPage}<span class="sr-only"></span></a>
                </li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/blog/main/${currentPage+1}"><ftm:message key="next_page"
                                                                                         bundle="${value}"/></a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>