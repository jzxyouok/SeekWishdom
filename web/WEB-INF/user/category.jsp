<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--注入应用模板  -->
    <%@include file="/public/head.jspf" %>
    <title>所有用户可访问的页面</title>
    <script type="text/javascript">
        $(function () {
            //默认首页被选中
            $("#myTab").find("li").eq(1).addClass("active");
            var isFirst = 0;
            var temp;
            /* 获取所有分类 */
            $.post('category_allCategory.action', function (text, status) {
                $.each(text, function (name, value) {
                    var button = "<a class='btn btn-default' role='button' href='#' id='" + value.id + "'>" + value.category + "</a>";
                    /* 	     var herfText="question_queryByCid.action?id="+value.id;
                     var button = "<a class='btn btn-default' role='button' href='"+herfText+"'>" + value.category + "</a>" */
                    $("#mainCategory").append(button);
                    if (isFirst == 0) {
                        getData(value.id);
                        isFirst = 1;
                        temp = value.id;
                    }
                });
            });
            function getData(data) {
                var currentCategory = "";
                currentCategory.id = data;
                $.ajax({
                    url: "question_queryByCid.action",
                    type: "post",
                    data: {
                        cid: data
                    },
                    success: function (data) {
                    }
                });
                /* 	$.post('question_queryByCid.action', {
                ${category.id} : temp
                 }, function(text, status) {
                 $.each(text, function(name, value) {

                 });
                 }); */

            }

        })
    </script>
</head>
<body>
<!-- 导航条 -->
<%@include file="/WEB-INF/user/header.jspf" %>
<div id="main">
    <!-- 内容区 -->
    <!--  话题-->
    <div id="content" style="margin-left:20%; margin-right:30%;">
        <h3>话题</h3>
        <hr>
        <div id="mainCategory"></div>
        <div id="mainQuestion"></div>
    </div>

</div>

</body>
</html>