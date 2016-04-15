<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--注入应用模板  -->
    <%@include file="/public/head.jspf" %>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"></script>
    <LINK rel=stylesheet href="/ueditor/themes/default/css/ueditor.css">

    <script type="text/javascript">
        $(function () {
            //默认首页被选中
            $("#myTab").find("li").eq(3).addClass("active");
            //ueditor声明
            var ue = UE.getEditor('container');
            $("#getContent").click(function () {
                var text = ue.getContent();
                alert(text)
            })

        })
    </script>
    <style type="text/css">
        #container {
            margin-left: 8%;
        }
    </style>
</head>
<body>
<!-- 导航条 -->
<%@include file="/WEB-INF/user/header.jspf" %>
<div id="main">
    <!-- 内容区 -->
    <!--  话题-->
    <div id="content" style="margin-left:20%; margin-right:30%;">
        <!--编辑提问  -->
        <form class="form-horizontal" action="question_saveOrUpdate.action" method="post">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">问题标题</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="请填写你的问题" name="title">
                </div>
            </div>
            <div class="form-group">
                <label for="categoryName" class="col-sm-2 control-label">所属分类</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="categoryName" placeholder="请填写分类"
                           name="category.category">
                </div>
            </div>
            <!-- 能够获取原始数据-->
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">问题内容</label>
            </div>
				<textArea id="container" style="height:550px" name="content" placeholder="">这里写你的初始化问题内容 
				</textArea>
            <input type="submit" value="提交" style="float: right;width:80px;margin-top:10px" class="btn btn-danger">
        </form>
    </div>


</div>

</body>
</html>