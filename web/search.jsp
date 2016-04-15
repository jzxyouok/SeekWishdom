<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype HTML>
<html>
<head>
    <%@include file="public/head.jspf" %>

    <script type="text/javascript">
        $(function () {
            var title = $(location).attr('href').split('?')[1].split('&')[0].split('=')[1];
            $.ajax({
                url: "${root}/question/search.action",
                data: {
                    title: title
                },
                type: "get",
                success: function (data) {
                    $("#searchList").html("");
                    $("#searchList").html(data);
                }
            })

        })
    </script>
</head>
<body>
<%@ include file="/public/navbar.jspf" %>
<div class="container">
    <div class="row">

        <div id="index" class="col l9 s12 m12">
            <br>
            <h5>搜索结果</h5>
            <div class="card">
                <div class="card-content">
                    <div id="searchList"></div>
                </div>
            </div>

        </div>

        <div class="col l3 s12 m12">
            <%@ include file="/public/sidebar.jspf" %>
        </div>
    </div>
</div>
</body>
</html>

