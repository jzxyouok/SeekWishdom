<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
</script>
<%--提问的list--%>

<c:if test="${requestScope.questionList.size()==0||requestScope.questionList==null}">
    <h5>没有找到包含该关键词的相关提问，你可以点击 <a href="/question/add.jsp" class="red-text darken-1">提问</a> 新增相关问题</h5>
</c:if>
<c:forEach items="${requestScope.questionList}" var="question" varStatus="status">


    <div class="row" id="class${answer.id}">
            <%--个人头像及名称信息--%>
        <div class="row valign-wrapper">
            <div class="col l1 m1 s2">
                    <%--头像--%>
                <c:if test="${question.anonymous==false}">
                    <img src="${question.user.avatar}" class="responsive-img"
                         onclick="userHomePage('${question.user.id}')"></c:if>
                <c:if test="${question.anonymous==true}">
                    <img src="/image/avatar/anonymous.jpg" class="responsive-img"></c:if>
            </div>
            <div class="col l11 m11 s10">

                    <%--当前用户未登录--%>
                <c:if test="${sessionScope.user==null}">
                    <c:if test="${question.anonymous==true}">
                        匿名回答
                    </c:if>
                    <c:if test="${question.anonymous==false}">
                        <a href="javascript:void (0)" class="black-text"
                           onclick="userHomePage('${question.user.id}')">${question.user.nickname}</a>
                    </c:if>
                </c:if>
                    <%--当前用户登录--%>
                <c:if test="${sessionScope.user!=null}">
                    <%--对应的是自己的--%>
                    <c:if test="${sessionScope.user.id==question.user.id}">
                        <c:if test="${question.anonymous==true}">
                            <a href="javascript:void (0)" class="black-text"
                               onclick="userHomePage('${sessionScope.user.id}')">我的匿名提问</a>
                        </c:if>
                        <c:if test="${question.anonymous==false}">
                            <a href="javascript:void (0)" class="black-text"
                               onclick="userHomePage('${sessionScope.user.id}')"> 我的提问</a>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.user.id!=question.user.id}">
                        <c:if test="${question.anonymous==true}">
                            匿名提问
                        </c:if>
                        <c:if test="${question.anonymous==false}">
                            <a href="javascript:void (0)" class="black-text"
                               onclick="userHomePage('${question.user.id}')">${question.user.nickname}</a>
                        </c:if>
                    </c:if>
                </c:if>

                <script type="text/javascript">
                    var id = "#time" + "${requestScope.question.id}";
                    $(id).html(jsDateDiff('${requestScope.question.createTime}'))
                </script>
            </div>
        </div>
        <span class="col s12 grey-text" id="time${question.id}"></span>
        <script type="text/javascript">
            var id = "#time" + "${question.id}";
            $(id).html(jsDateDiff('${question.createTime}'))
        </script>
        <div class="row col s12 waves-effect" onclick="question('${question.id}')"><h5
                class="red-text darken-1"> ${question.title}</h5>
            <br>

            <div class=" right">
                    ${requestScope.answerNumList[status.index]}个回答 &nbsp; ${requestScope.commentNumList[status.index]}个评论
            </div>
        </div>
    </div>
    <div class="divider"></div>
</c:forEach>