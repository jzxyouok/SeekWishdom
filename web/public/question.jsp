<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <br>

    <div class="card">

        <div class="card-content">
            <div class="card-title">
                <h5>暂无回答的问题</h5>
                <c:if test="${sessionScope.sidebarList.size()==0}">
                    <h5>暂无无回答的问题</h5>
                </c:if>
                <br>
            </div>

            <c:forEach items="${sessionScope.sidebarList}" var="question" varStatus="status">
                <span class="grey-text" id="time${question.id}"></span>
                <script type="text/javascript">
                    var id = "#time" + "${question.id}";
                    $(id).html(jsDateDiff('${question.createTime}'))
                </script>

                <%-- <div class="col s1">
                     <p> ${requestScope.agCountList[status.index]}</p>
                 </div>--%>

                <a name="${question.id}" href="#" onclick="question('${question.id}')"><h5
                        class="red-text darken-1 truncate">${question.title}</h5></a>

                <div class="divider"></div>

            </c:forEach>

        </div>
    </div>


</div>

