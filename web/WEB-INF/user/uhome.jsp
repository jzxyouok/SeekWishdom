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
            $("#myTab").find("li").eq(4).addClass("active");
            //判断 配选中的  #uId li
            /*标记选中*/
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

        /*清除 a标签选中时候的虚线框*/
        a:focus {
            outline: none;
            /*  -moz -outline: none;*/
            -moz-outline: none;
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<%@include file="/WEB-INF/user/header.jspf" %>
<div id="main">

    <div class="container">
        <div class="row">
            <!--主页  -->
            <div class="col-sm-7 blog-main">

                <div class="blog-post">
                    <!--个人信息  -->
                    <div class="panel panel-info">
                        <div class="panel-heading">我的资料</div>
                        <div class="panel-body">
                            <div class="media">
                                <div class="media-left media-middle">
                                    <img class="media-object" src="${seekWishdom}/image/image.png" alt="头像"/>头像
                                </div>
                                <div class="media-body">
                                    <div class="list-group">
                                        <span class="list-group-item">昵称:${sessionScope.user.nickname}</span> <span
                                            class="list-group-item">邮箱:${sessionScope.user.email}</span>
                                    </div>
                                    <a class="glyphicon glyphicon-pencil" href="#" role="button" style="float:right">编辑我的资料</a>

                                </div>
                            </div>

                        </div>
                        <div class="panel-footer" style="padding:2px">
                            <ul class="nav nav-pills" id="uId">
                                <li role="presentation"><a href="#" class="glyphicon glyphicon-home"></a></li>
                                <li role="presentation" class="active"><a href="#">Profile</a></li>
                                <li role="presentation"><a href="#">Messages</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
                <!-- /.blog-post -->
                <!-- 相关回答 -->
                <div class="blog-post">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            回答 <a class="glyphicon glyphicon-chevron-right" href="#" role="button"
                                  style="float:right;color: silver;"></a>
                        </div>
                        <div class="panel-body">其他内容</div>
                    </div>
                </div>
                <!-- 相关 -->
                <div class="blog-post">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            提问 <a class="glyphicon glyphicon-chevron-right" href="#" role="button"
                                  style="float:right;color: silver;"></a>
                        </div>
                        <div class="panel-body">其他内容</div>
                    </div>
                </div>
                <!-- 相关回答 -->
                <div class="blog-post">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            最新动态 <a class="glyphicon glyphicon-chevron-right" href="#" role="button"
                                    style="float:right;color: silver;"></a>
                        </div>
                        <div class="panel-body">其他内容</div>
                    </div>
                </div>
            </div>
            <!--
        -post"> main -->
            <!--  右边栏-->
            <div class="col-sm-3 col-sm-offset-1 blog-sidebar" style="margin-left:0px;">
                <div class="sidebar-module sidebar-module-inset">
                    <h4>About</h4>

                    <p>
                        Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet
                        fermentum. Aenean lacinia bibendum nulla sed consectetur.
                    </p>
                </div>
                <div class="sidebar-module">
                    <h4>Archives</h4>
                    <ol class="list-unstyled">
                        <li><a href="#">March 2014</a></li>
                        <li><a href="#">February 2014</a></li>
                        <li><a href="#">January 2014</a></li>
                        <li><a href="#">December 2013</a></li>
                        <li><a href="#">November 2013</a></li>
                        <li><a href="#">October 2013</a></li>
                        <li><a href="#">September 2013</a></li>
                        <li><a href="#">August 2013</a></li>
                        <li><a href="#">July 2013</a></li>
                        <li><a href="#">June 2013</a></li>
                        <li><a href="#">May 2013</a></li>
                        <li><a href="#">April 2013</a></li>
                    </ol>
                </div>
                <div class="sidebar-module">
                    <h4>Elsewhere</h4>
                    <ol class="list-unstyled">
                        <li><a href="#">GitHub</a></li>
                        <li><a href="#">Twitter</a></li>
                        <li><a href="#">Facebook</a></li>
                    </ol>
                </div>
            </div>
            <!-- /.blog-sidebar -->

        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->

</div>

</body>
</html>