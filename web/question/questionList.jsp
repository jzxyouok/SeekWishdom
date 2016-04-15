<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
</script>
<%--提问的list--%>
<c:forEach items="${requestScope.questionList}" var="question" varStatus="status">


    <div class="row" id="class${answer.id}">
            <%--个人头像及名称信息--%>
        <span class="col s12 grey-text" id="time${question.id}"></span>
        <script type="text/javascript">
            var id = "#time" + "${question.id}";
            $(id).html(jsDateDiff('${question.createTime}'))
        </script>
        <div class="row col s12 waves-effect" onclick="question('${question.id}')"><h5
                class="red-text darken-1"> ${question.title}</h5>
            <input id="${question.id}" type="hidden" value='${question.content}'/>

                <%--显示的回答--%>
            <a href="#" class="black-text" onclick="answer('${question.id}')"
               id="div${question.id}"></a>

            <script type="text/javascript">
                getSlimContent('${question.id}')
            </script>
        </div>
        <div class="right">
                ${requestScope.answerNumList[status.index]}个回答 &nbsp; ${requestScope.commentNumList[status.index]}个评论
        </div>
    </div>
    <div class="divider"></div>
</c:forEach>