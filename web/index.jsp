<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype HTML>
<html>
<head>
    <%@ include file="/public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>
    <script type="text/javascript">

    </script>
</head>
<body>
<%@ include file="/public/navbar.jspf" %>
<!-- 内容区 -->
<!--  话题-->
<div class="container">
    <div class="row">
        <div id="index" class="col l9 s12 m12">
            <br>
            <c:forEach items="${applicationScope.answerList}" var="answer" varStatus="status">


                <div class="card col s12" id="class${answer.id}">
                    <div class="card-content">

                            <%--个人信息--%>
                        <div class="row valign-wrapper">
                            <div class="col l1 m1 s2">
                                    <%--头像--%>
                                <c:if test="${answer.anonymous==false}">
                                    <img src="${answer.user.avatar}" class="responsive-img"
                                         onclick="userHomePage('${answer.user.id}')"></c:if>
                                <c:if test="${answer.anonymous==true}">
                                    <img src="/image/avatar/anonymous.jpg" class="responsive-img"></c:if>
                            </div>
                            <div class="col l11 m11 s10 valign">

                                    <%--当前用户未登录--%>
                                <c:if test="${sessionScope.user==null}"><c:if
                                        test="${answer.anonymous==true}">匿名回答</c:if>
                                    <c:if test="${answer.anonymous==false}"><a href="#" class="black-text"
                                                                               onclick="userHomePage('${answer.user.id}')">${answer.user.nickname}</a></c:if>
                                </c:if>
                                    <%--当前用户登录--%>
                                <c:if test="${sessionScope.user!=null}">
                                    <%--对应的是自己的--%>
                                    <c:if test="${sessionScope.user.id==answer.user.id}">
                                        <c:if test="${answer.anonymous==true}"><a href="#" class="black-text"
                                                                                  onclick="userHomePage('${sessionScope.user.id}')">我的匿名回答</a></c:if>
                                        <c:if test="${answer.anonymous==false}"><a href="#" class="black-text"
                                                                                   onclick="userHomePage('${sessionScope.user.id}')">我的回答</a></c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.user.id!=answer.user.id}">
                                        <c:if test="${answer.anonymous==true}">匿名回答</c:if>
                                        <c:if test="${answer.anonymous==false}">
                                            <a href="#" class="black-text"
                                               onclick="userHomePage('${answer.user.id}')">${answer.user.nickname}</a>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <span class="grey-text" id="time${answer.id}"></span>
                                <script type="text/javascript">
                                    var id = "#time" + "${answer.id}";
                                    $(id).html(jsDateDiff('${answer.createTime}'))
                                </script>

                            </div>
                        </div>

                        <div class="divider"></div>
                            <%--问题标题--%>
                        <div class="row">
                                <%--进入具体回答--%>
                            <a href="#">
                                <h5 onclick="answer('${answer.id}')"
                                    class="red-text darken-1">${answer.question.title}
                                </h5>
                            </a>
                                <%--话题--%>
                            <a href="#" class="grey-text"
                               onclick="category('${answer.question.category.id}')">
                                    ${answer.question.category.category}</a>
                        </div>
                            <%--点赞以及话题的内容--%>


                        <div class="row">
                                <%--问答内容--%>
                                <%--隐藏的回答--%>

                            <input id="${answer.id}" type="hidden" value='${answer.content}'/>

                                <%--显示的回答--%>
                            <div class="col s12 waves-effect" onclick="answer('${answer.id}')"
                                 id="div${answer.id}"></div>

                            <script type="text/javascript">
                                getSlimContent('${answer.id}')
                            </script>


                        </div>

                    </div>
                </div>

            </c:forEach>
        </div>
        <div class="col l3 s12 m12">
            <%@ include file="/public/sidebar.jspf" %>
        </div>
    </div>
</div>
</body>
</html>
