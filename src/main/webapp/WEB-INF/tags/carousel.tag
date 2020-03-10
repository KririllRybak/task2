<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="post" required="true" rtexprvalue="true"
             type="by.epam.training.blog.domain.application_entity.ApplicationPost" %>
<div id="myCarousel${post.id}" class=" carousel slide" data-ride="carousel">
    <c:choose>
        <c:when test="${fn:length(post.images) eq 1}">
            <c:set var="img" scope="session" value="${post.images}"/>
            <c:if test="${empty img[0]}">
                <c:url value="/img/room.jpg" var="room"/>
                <ul class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                </ul>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img id="photo6" class=" rounded mx-auto d-block" src="${room}" alt="" width="500" height="400">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#myCarousel${post.id}" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#myCarousel${post.id}" data-slide="next">
                    <span class="carousel-control-next-icon "></span>
                </a>
            </c:if>
            <c:if test="${not empty img[0]}">
                <ul class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                </ul>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img id="photo" class=" rounded mx-auto d-block" src="data:image/jpeg;base64, ${img[0]}" alt=""
                             width="500" height="400">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#myCarousel${post.id}" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#myCarousel${post.id}" data-slide="next">
                    <span class="carousel-control-next-icon "></span>
                </a>
            </c:if>
        </c:when>
        <c:when test="${fn:length(post.images) eq 2}">
            <ul class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
            </ul>
            <!-- The slideshow -->
            <div class="carousel-inner">
                <c:set var="img" scope="session" value="${post.images}"/>
                <div class="carousel-item active">
                    <img id="photo1" class=" rounded mx-auto d-block" src="data:image/jpeg;base64, ${img[0]}" alt=""
                         width="500" height="400">
                </div>
                <div class="carousel-item">
                    <img id="photo2" class=" rounded mx-auto d-block" src="data:image/jpeg;base64, ${img[1]}" alt=""
                         width="500" height="400">
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#myCarousel${post.id}" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#myCarousel${post.id}" data-slide="next">
                <span class="carousel-control-next-icon "></span>
            </a>
        </c:when>
        <c:when test="${fn:length(post.images) eq 3}">
            <ul class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ul>
            <!-- The slideshow -->
            <div class="carousel-inner">
                <c:set var="img" scope="session" value="${post.images}"/>
                <div class="carousel-item active">
                    <img id="photo3" src="data:image/jpeg;base64, ${img[0]}" class=" rounded mx-auto d-block" alt=""
                         width="500" height="400">
                </div>
                <div class="carousel-item">
                    <img id="photo4" src="data:image/jpeg;base64, ${img[1]}" class=" rounded mx-auto d-block" alt=""
                         width="500" height="400">
                </div>
                <div class="carousel-item">
                    <img id="photo5" src="data:image/jpeg;base64, ${img[2]}" class=" rounded mx-auto d-block" alt=""
                         width="500" height="400">
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="carousel-control-prev" href="#myCarousel${post.id}" data-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </a>
            <a class="carousel-control-next" href="#myCarousel${post.id}" data-slide="next">
                <span class="carousel-control-next-icon"></span>
            </a>
        </c:when>


    </c:choose>

    <!-- Indicators -->

</div>
