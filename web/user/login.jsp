<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype HTML>
<html>
<head>
    <%@ include file="../public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>
    <script type="text/javascript">


        function login() {
            //邮箱验证
            var email = $("#email");
            var password = $("#password");
            var back = emailCheck(email);
            if (back == false) {
                return false;
            }
            var passwordCheck = passwordValidate(password);
            if (passwordCheck == false) {
                return false;
            }
            //密码验证
            var options = {
                url: '${root}/user/login.action',
                type: 'post',
                data: null,
                success: function (data) {
                    if (data == "true") {
                        window.location.href = '/index.jsp';
                    } else {
                        $("#error").html("用户名或密码不正确")
                    }
                }
            };
            var form = $("#form");
            form.ajaxSubmit(options);

        }
    </script>
</head>
<body>
<%@ include file="../public/navbar.jspf" %>
<form id="form">
    <br>
    <br>
    <br>

    <div class="container row valign-wrapper">

        <div class="col s12 l3 push-l9">
            <div class="row">
                <h4 class="center-align">用户登录</h4>
            </div>
            <br>

            <div id="error" class="red-text darken-1">&nbsp;</div>
            <div class="input-field">
                <input id="email" name="email" type="email" required="required" class="validate">
                <label for="email">邮箱：</label>
            </div>
            <div class="input-field">
                <input id="password" name="password" type="password" required="required" class="validate">
                <label for="password">密码：</label>
            </div>

            <div class="col s6">
                <a onclick="login()" class="col s12 waves-effect waves-light btn-large red darken-1">登录</a>
            </div>
            <div class="col s6">
                <a href="${root}/user/register.jsp"
                   class="col s12 waves-effect waves-light btn-large red darken-1">注册</a>
            </div>
        </div>
        <div class="col l9 pull-l3 hide-on-med-and-down valign">
            <img src="../image/login.jpg" class="responsive-img z-depth-1">
        </div>
    </div>
</form>
</body>
</html>
