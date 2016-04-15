<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--注入应用模板  -->
    <%@include file="/public/head.jspf" %>
    <title>用户设置页面</title>
    <script type="text/javascript">
        $(function () {
            //默认首页被选中
            $("#myTab").find("li").eq(4).addClass("active");
            //判断 配选中的  #uId li

            $("#uId li").click(function () {
                $(this).parent("ul").children("li").removeClass("active");
                $(this).addClass("active");
            });
        });
    </script>
    <style type="text/css">
        .panel-default > .panel-heading {
            background-image: linear-gradient(to bottom, #fff 0px, #fff 100%);
            background-repeat: repeat-x;
        }

        /* 下方标题栏 */
        #uId li.active > a {
            background-color: white;
            color: black;
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<%@include file="/WEB-INF/user/header.jspf" %>
<div id="main">
    <div class="container">
        <div class="row">dgee</div>
    </div>
</div>

</body>
</html>