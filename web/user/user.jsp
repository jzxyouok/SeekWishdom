<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype HTML>
<html>
<head>
    <%@include file="/public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/materialize/css/style.css" media="screen,projection"/>
    <script type="text/javascript" src="/materialize/js/cropbox.js"></script>
    <script type="text/javascript">
        /* var uid =;//获取当前的用户id//或者传入的id*/
        var globalMark = 1;//1.表示提问 2回答，3 点赞
        var qpage = 1;//默认第一页
        var apage = 1;//默认第一页
        var agpage = 1;//默认第一页

        var aCount =${requestScope.aCount};
        var qCount =${requestScope.qCount};
        var agCount = ${requestScope.agCount};
        var qPage = ${requestScope.qPages};//问题的页码
        var aPage =${requestScope.aPages};//回答的页码
        var agPage = ${requestScope.agPages};//点赞的页码
        $(function () {
            $('ul.tabs').tabs();
            //初始化页面 罗列提问
            $("#questionDiv").hide();
            $("#answerDiv").hide();
            $("#agreeDiv").hide();
            if (qPage > 0) {
                qpage = 1;
                questionList()//初始化提问显示

            };

        });
        //提问
        function questionList() {
            pages = 1;
            ipage = 1;
            $("#questionDiv").show();
            $("#answerDiv").hide();
            $("#agreeDiv").hide();
            $.ajax({
                url: '/question/questionList.action',
                type: "post",
                data: {
                    uid: '${requestScope.user.id}', page: qpage
                },
                success: function (data) {
                    $("#mainContent").html(data);
                }
            });


            current_total();

        }
        //获取回答
        function answerList() {
            $("#questionDiv").hide();
            $("#answerDiv").show();
            $("#agreeDiv").hide();

            $.ajax({
                url: '/answer/answerListu.action',
                type: "post",
                data: {
                    uid: '${requestScope.user.id}', page: apage
                },
                success: function (data) {
                    $("#mainContent").html(data);
                }
            });
            current_totala();
        }
        function agreeList() {
            $("#questionDiv").hide();
            $("#answerDiv").hide();
            $("#agreeDiv").show();

            $.ajax({
                url: '/answer/answerListag.action',
                type: "post",
                data: {
                    uid: '${requestScope.user.id}', page: agpage
                },
                success: function (data) {
                    $("#mainContent").html(data);
                }
            });
            current_totalag();
        }


        //下一页
        function nextPage() {//点击事件
            if (qpage < qPage) {//
                qpage++;
                questionList();
                current_total();
                scrollToTop()
            }
        }
        //上一页
        function prePage() {//点击事件
            if (qpage > 1) {//
                qpage--;
                questionList();
                current_total();
                scrollToTop()

            }
        }
        //回答的上一页下一页
        //下一页
        function nextPagea() {//点击事件
            if (apage < aPage) {//
                apage++;
                answerList();
                current_totala();
                scrollToTop()

            }
        }
        //上一页
        function prePagea() {//点击事件
            if (apage > 1) {//
                apage--;
                answerList();
                current_totala();
                scrollToTop()

            }
        }

        //下一页
        function nextPageag() {//点击事件
            if (agpage < agPage) {//
                agpage++;
                agreeList();
                current_totalag();
                scrollToTop()

            }
        }
        //上一页
        function prePageag() {//点击事件
            if (agpage > 1) {//
                agpage--;
                agreeList();

                current_totalag();
                scrollToTop()

            }
        }

        //当前页面与总页数显示
        function current_total() {
            var str = qpage + "/" + qPage;
            $("#current_total").html(str);
            if (qpage == 1) {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center btn disabled red darken-1");
            } else {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center waves-effect btn red darken-1");
            }
            if (qpage == qPage) {
                $("#nextPage").attr("class", "col l2 m2 s5 btn center disabled red darken-1");

            } else {
                $("#nextPage").attr("class", "col l2 m2 s5 center waves-effect btn red darken-1");
            }
        }

        //当前页面与总页数显示 回答的
        function current_totala() {
            var str = apage + "/" + aPage;
            $("#current_totala").html(str);
            if (apage == 1) {
                $("#prePagea").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center btn disabled red darken-1");
            } else {
                $("#prePagea").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center waves-effect btn red darken-1");
            }
            if (apage == aPage) {
                $("#nextPagea").attr("class", "col l2 m2 s5 btn center disabled red darken-1");

            } else {
                $("#nextPagea").attr("class", "col l2 m2 s5 center waves-effect btn waves-light red darken-1");
            }
        }


        //当前页面与总页数显示  这个可以公用
        function current_totalag() {
            var str = agpage + "/" + agPage;
            $("#current_totalag").html(str);
            if (agpage == 1) {
                $("#prePageag").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center btn disabled red darken-1");
            } else {
                $("#prePageag").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center waves-effect btn red darken-1");
            }
            if (agpage == agPage) {
                $("#nextPageag").attr("class", "col l2 m2 s5 btn center disabled red darken-1");

            } else {
                $("#nextPageag").attr("class", "col l2 m2 s5 center waves-effect btn waves-light red darken-1");
            }
        }


    </script>
</head>
<body>
<%@ include file="/public/navbar.jspf" %>
<div class="container">
    <div class="row">
        <br>

        <div class="col l9 s12">
            <div class="card col s12">
                <div class="card-content">
                    <div class="col s4">
                        <a onclick="userHomePage('${requestScope.user.id}')" href="javascript:void (0)"><img
                                class="responsive-img" src="${requestScope.user.avatar}">
                        </a>
                    </div>
                    <h4 class="col s8">
                        <c:if test="${requestScope.user.id==sessionScope.user.id}">
                        <a onclick="userHomePage('${requestScope.user.id}')" href="javascript:void (0)">
                            <h5 class="red-text darken-1">
                                我的个人空间</h5>
                        </a>

                        <div class="black-text                    ">
                            <h6>注册邮箱：${requestScope.user.email}</h6>
                        </div>
                        </c:if>
                        <c:if test="${requestScope.user.id!=sessionScope.user.id}">
                        <a onclick="userHomePage('${requestScope.user.id}')" href="javascript:void (0)">
                            <h5 class="red-text darken-1">
                                    ${requestScope.user.nickname}的个人空间
                            </h5>
                        </a>
                        </c:if>

                </div>

            </div>
            <div class="row">
                <div class="col s12 card">
                    <div class="card-content">
                        <div class="row">
                            <ul class="tabs">
                                <li class="tab col s3">
                                    <a href="javascript:void(0)" class="red-text darken-1 active"
                                       onclick="questionList()">提问(${requestScope.qCount})</a>
                                </li>
                                <li class="tab col s3">
                                    <a class="red-text darken-1" href="javascript:void(0)"
                                       onclick="answerList()">回答(${requestScope.aCount})</a>
                                </li>
                                <li class="tab col s3">
                                    <a class="red-text darken-1" href="javascript:void(0)"
                                       onclick="agreeList()">赞过的回答(${requestScope.agCount})</a>
                                </li>
                            </ul>
                        </div>
                        <div id="mainContent">

                        </div>
                    </div>
                </div>
                <%--分页--%>
                <%--上一页，下一页的显示--%>
                <%--提问的分页--%>
                <div id="questionDiv">
                    <div class="row">
                        <a id="prePage" onclick="prePage()">上一页</a>

                        <div class="col l2 m2 s2 text-black center-align" id="current_total"></div>
                        <a id="nextPage" onclick="nextPage()">下一页</a>
                    </div>
                </div>
                <%--回答的分页--%>
                <div id="answerDiv">
                    <div class="row">
                        <a id="prePagea" onclick="prePagea()">上一页</a>

                        <div class="col l2 m2 s2 text-black center-align" id="current_totala"></div>
                        <a id="nextPagea" onclick="nextPagea()">下一页</a>
                    </div>
                </div>
                <%--点赞的分页--%>
                <div id="agreeDiv">
                    <div class="row">
                        <a id="prePageag" onclick="prePageag()">上一页</a>

                        <div class="col l2 m2 s2 text-black center-align" id="current_totalag"></div>
                        <a id="nextPageag" onclick="nextPageag()">下一页</a>
                    </div>
                </div>
            </div>
            <div class="divider"></div>
        </div>
        <div class="col l3 hide-on-med-and-down">
            <%@ include file="../public/sidebar.jspf" %>
        </div>
    </div>
</div>
</body>
</html>
