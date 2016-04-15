<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype HTML>
<html>
<head>
    <%@include file="../public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>
    <script type="text/javascript">
        var pageSize = 20;//显示页面数据
        var totalPage = "";//当前话题对应的问题的总页数
        var pageIndex = 1;//默认第一页
        var currentCid = "";//当前的分类cid
        $(function () {
            var url = $(location).attr('href');
            var cid;
            if (/.*\.jsp\?cid=.*/.test(url))
                cid = $(location).attr('href').split('?')[1].split('&')[0].split('=')[1];
            if (cid != null && cid != "") {
                $.ajax({
                    url: '/category/count.action',
                    type: "post",
                    data: {cid: cid},
                    success: function (data) {
                        if (data != 0) {
                            totalPage = data;
                            getQuestionList(cid)//获取问题列表
                            current_total();
                        };
                        else {
                            $("#mainQuestion").html("<h5>该话题暂无新动态</h5>");
                            $("#pageRow").hide()
                        }
                    }
                })
            } else {
                $("#pageRow").hide();
                $("#mainQuestion").html("<h5>您可以选择您感兴趣的话题</h5>");

            }


        });
        //获取当前的话题有多少问题

        function getQuestionCountByCid(obj) {
            pageIndex = 1;

            var cid = obj.name;
            var iddd = "#" + cid;
            $(".categoryButtom").attr("class", "categoryButtom btn grey lighten-1  waves-effect");
            $(iddd).attr("class", "categoryButtom btn red darken-1  waves-effect");
            currentCid = cid;
            $.ajax({
                url: '/category/count.action',
                type: "post",
                data: {cid: cid},
                success: function (data) {
                    if (data != 0) {
                        totalPage = data;
                        getQuestionList(cid)//获取问题列表
                        current_total();
                    };
                    else {
                        $("#mainQuestion").html("<h5>该话题暂无新动态</h5>");
                        $("#pageRow").hide()
                    }
                }
            })
        }


        //获取问题列表
        function getQuestionList(cid) {
            $.ajax({
                url: '/category/category.action',
                type: "post",
                data: {
                    cid: cid, page: pageIndex
                },
                success: function (data) {
                    if (data == 'false') {
                        login();
                    }
                    else {
                        $("#mainQuestion").html("");
                        $("#mainQuestion").html(data);
                    }
                }
            })
        }
        //下一页
        function nextPage() {//点击事件
            if (pageIndex < totalPage) {//
                pageIndex++;
                getQuestionList(currentCid);
                current_total();
                scrollToTop()
            }
        }
        //上一页
        function prePage() {//点击事件
            if (pageIndex > 1) {//
                pageIndex--;
                getQuestionList(currentCid);
                current_total();
                scrollToTop()
            }
        }
        //当前页面与总页数显示
        function current_total() {
            $("#pageRow").show();
            var str = pageIndex + "/" + totalPage;
            $("#current_total").html(str);
            if (pageIndex == 1) {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center btn disabled red darken-1");
            } else {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center waves-effect btn red darken-1");
            }
            if (pageIndex == totalPage) {
                $("#nextPage").attr("class", "col l2 m2 s5 btn center disabled red darken-1");

            } else {
                $("#nextPage").attr("class", "col l2 m2 s5 center waves-effect btn red darken-1");
            }

        }
    </script>
</head>
<body>
<%@ include file="../public/navbar.jspf" %>
<!-- 内容区 -->
<!--  话题-->
<div class="container">
    <div class="row">
        <div id="index" class="col l9 s12 m12">
            <br>
            <h4>话题</h4>

            <%--话题分类--%>
            <div class="row">
                <c:forEach items="${applicationScope.categoryList}" var="category">
                    <a class="categoryButtom btn grey lighten-1 waves-effect" href="javascript:void (0)"
                       id="${category.id}"
                       onclick="getQuestionCountByCid(this)"
                       name="${category.id}"> ${category.category}</a>

                </c:forEach>
            </div>
            <%--问题列表--%>
            <div id="mainQuestion">

            </div>


            <div id="pageRow" class="row center">
                <a id="prePage" onclick="prePage()">上一页</a>

                <div class="col l2 m2 s2 text-black center-align" id="current_total"></div>
                <a id="nextPage" onclick="nextPage()">下一页</a>
            </div>
        </div>
        <div class="col l3 hide-on-med-and-down">
            <%@ include file="/public/sidebar.jspf" %>
        </div>
    </div>
</div>
</body>
</html>
