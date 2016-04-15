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
            $("#myTab").find("li").eq(0).addClass("active");
            $("#myTab li").click(function () {
                $(this).siblings('li').removeClass('active'); // 删除其他兄弟元素的样式
                $(this).addClass('active');
            });
            $('[data-toggle="popover"]').popover();
            $("#LinkDIV").html('<button type="btn btn-lg btn-primary" data-toggle="popover" id="temp3">弹出框3</button>');
            $('[data-toggle="popover"]').each(function () {
                var element = $(this);
                var id = element.attr('id');
                var txt = element.html();
                element.popover({
                    trigger: 'manual',
                    placement: 'bottom', //top, bottom, left or right
                    title: txt,
                    html: 'true',
                    content: ContentMethod(txt),

                }).on("mouseenter", function () {
                    var _this = this;
                    $(this).popover("show");
                    $(this).siblings(".popover").on("mouseleave", function () {
                        $(_this).popover('hide');
                    });
                }).on("mouseleave", function () {
                    var _this = this;
                    setTimeout(function () {
                        if (!$(".popover:hover").length) {
                            $(_this).popover("hide")
                        }
                    }, 100);
                });
            });
        });

        //组装内容
        function ContentMethod(txt) {
            return '<table class="table table-bordered"><tr><td>' + txt + '</td><td>BB</td><td>CC</td><td>DD</td></tr>' + '<tr><td>' + txt + '</td><td>BB</td><td>CC</td><td>DD</td></tr>' + '<tr><td>' + txt + '</td><td>BB</td><td>CC</td><td>DD</td></tr>'
                    + '<tr><td>AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</td><td>BB</td><td>CC</td><td>DD</td></tr></table>';
        }
    </script>

</head>
<body>
<!-- 导航条 -->
<%@include file="/WEB-INF/user/header.jspf" %>
<div id="main">
    <div id="content" style="margin-left:25%">
        <div id="hotQuestion" class="col-md-6">
            <h4>求知动态</h4>
            <hr>
            <!--  循环产生问题-->
            <div id="hotdetail">
                <!-- 	<span><font color="silver">热门回答,来自服装搭配</font><u>关注话题</u></span>
                <h4>
                    <font color="blue">标题</font>
                </h4>

                 展示较好的回答
                展示话题，评论内容
                第一个的
                <div>
                    <span></span>

                </div> -->


                <div class="media">

                    <div class="media-left">点赞人数</div>
                    <div class="media-body">
							<span><font color="silver">热门回答,来自服装搭配</font><u>关注话题</u>
								<button type="button" class="close" style="float:right" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button></span>
                        <h4>
                            <font color="blue">标题</font>
                        </h4>
                        <div>
                            <span></span>
                            <div id="LinkDIV" style="float:left;width:200px"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="otherArea" class="col-md-2">其他类容 其他内容</div>


        </div>
    </div>
</body>
</html>