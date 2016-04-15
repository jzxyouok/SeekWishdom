<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="questionList">
    <%--问题列表--%>
    <div class="row">
        <c:forEach items="${requestScope.rows}" var="question" varStatus="status">
            <div class="card col s12">
                <div class="card-content">
                        <%-- 提问的内容--%>

                    <h5 class="red-text darken-3">${question.title}</h5>
                        <%--  点赞数量--%>
                        <%--  回答的循环--%>
                    <div class="divider"></div>
                    <c:forEach items="${requestScope.bigAnswerList[status.index]}" var="answer" varStatus="aStatus">


                        <%--111111111111111111111111111111111111111111111111111111111--%>
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


                        <div id="answerDiv${answer.id}">
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

                            <div class="divider"></div>

                        </div>

                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>


</div>
