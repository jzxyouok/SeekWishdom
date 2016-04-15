<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype HTML>
<html>
<head>
    <%@ include file="/public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>

    <script type="text/javascript">
        $(function () {
            //ueditor声明
            if (!checkLogin()) {
                login()
            }
            $(document).ready(function () {
                $('select').material_select();
            });


            var um = UM.getEditor('ueditor', {
                initialContent: '<span style="color:#989898">在此输入问题说明（可选）</span>',
                initialFrameWidth: null,
                autoClearinitialContent: true,
                initialFrameHeight: 300

            });


        });
        function save() {
            var titleResult = titleCheck();
            if (titleResult == false) {
                return false;
            }
            var categoryResult = categoryCheck();
            if (categoryResult == false) {
                return false;
            }
            var options = {
                url: ' ${root}/question/save.action',
                type: 'post',
                data: null,
                success: function (data) {
                    if (data != "") {
                        window.location.href = '/question/question.action?qid=' + data;
                    }
                }
            };
            var form = $("#form");
            form.ajaxSubmit(options);
        }
        //验证标题
        function titleCheck() {
            var text = $("#title").val();
            // var str = /^[\w\d@\.\-_]{1,200}$/i;//标题长度的验证

            if (text.length <= 0) {
                alert("标题不能为空");
                return false;
            }
            else if (text.length > 200) {
                alert("标题过长，请修改");
                return false
            }

        }
        //验证分类是否选择
        function categoryCheck() {
            var text = $("select option:selected").val();
            if (text == "") {
                alert("分类必填");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%@ include file="../public/navbar.jspf" %>
<div class="container">
    <div class="row">
        <div class="col l9 m12 s12">
            <br>
            <h4>新的提问</h4>

            <form id="form">
                <div class="input-field">
                    <input id="title" name="title" type="text">
                    <label for="title">问题：</label>
                </div>
				<textArea id="ueditor" class="col s12 z-depth-1" name="content">
				</textArea>

                <div class="input-field">

                    <select name="cid" id="category_select">
                        <option value="" disabled selected>请选择问题分类</option>

                        <c:forEach items="${applicationScope.categoryList}" var="category">

                            <option value="${category.id}">${category.category}</option>

                        </c:forEach>
                    </select>
                </div>

                <div class="col l3 offset-l5 m3 offset-m5 s6">
                    <p>
                        <input type="checkbox" value="true" name="anonymous" class="filled-in" id="filled-in-box"/>
                        <label for="filled-in-box">匿名</label>
                    </p>
                </div>
                <div class="col l4 m4 s6">
                    <a type="button" onclick="save()"
                       class="col s12 waves-effect waves-light btn-large red darken-1">提交</a>
                </div>

            </form>
        </div>
        <div class="col l3 hide-on-med-and-down">
            <%@ include file="../public/sidebar.jspf" %>
        </div>
    </div>
</div>

</body>
</html>
