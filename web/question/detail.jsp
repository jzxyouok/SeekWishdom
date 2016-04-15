<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype HTML>
<html>
<head>
    <%@include file="/public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>

    <script type="text/javascript">

        var ipage = 1;//默认第一页
        var pages;//总页数
        var countAnswer;//总的回答数量
        var updateQcontent;//更新内容
        var currentPage = '${requestScope.currentPage}';
        var pageMark = '${requestScope.pageMark}';//当前aid所在页表
        var qInputId = '${requestScope.question.id}';//问题内容的隐藏框
        var um;
        $(function () {

            um = UM.getEditor('ueditor', {
                initialContent: '<span style="color:#989898">在此输入你的回答</span>',
                initialFrameWidth: null,
                autoClearinitialContent: true,
                initialFrameHeight: 300
            });
            $.ajax({
                url: '/answer/count.action',
                type: "post",
                data: {qid: '${requestScope.question.id}'},
                success: function (data) {
                    if (data != 0) {
                        pages = data;
                        if (pageMark != '') {
                            ipage = currentPage;
                        }
                        getAnswerList(ipage);
                        current_total();
                    }
                }
            });

            $("#update").hide();
        });
        //显示要修改的内容
        function updateShow() {
            $("#mainContent").hide();
            $("#update").show();
            updateQcontent = UM.getEditor('change_content', {
                initialFrameWidth: null,
                autoClearinitialContent: true,
                initialFrameHeight: 600
            });
            var content = $("#" + qInputId).val();
            updateQcontent.setContent(content)
        }

        //取消修改
        function updatehide() {
            $("#mainContent").show();
            $("#update").hide();
        }
        //修改并向后台传递 问题内容的修改
        function update() {
            var text = updateQcontent.getContent();
            var isAnonymous = $("#filled-in").is(':checked');


            $.ajax({
                url: '/question/update.action',
                type: "post",
                data: {
                    qid: '${requestScope.question.id}', content: text, anonymous: isAnonymous
                },
                success: function (data) {
                    if (data == 'true') {
                        $("#" + qInputId).val(text);
                        getSlimContent(qInputId);
                        $("#mainContent").show();
                        $("#update").hide();
                    }
                    else {
                        //其他操作
                    }
                }
            })

        }

        //获取回答列表
        function getAnswerList(page) {
            $.ajax({
                url: '/answer/answerList.action',
                type: "post",
                data: {
                    qid: '${requestScope.question.id}', page: page
                },
                success: function (data) {
                    $("#answer").html("");
                    $("#answer").html(data);
                }
            })
        }
        //添加回答
        function addAnswer() {
            if (answerCheck() == false) {
                return false;
            }
            var options = {
                url: "${root}/answer/save.action",
                type: 'post',
                data: null,
                success: function (data) {
                    var text = data;
                    if (data != "false") {
                        answer(data);
                    }
                }
            };
            var form = $("#form");
            form.ajaxSubmit(options);
        }
        //验证答案是否为空
        function answerCheck() {
            var content = $("#ueditor").val();
            if (content == "") {
                alert('没有内容');
                um.focus();//光标返回编辑器中
                return false;
            }
        }

        //下一页
        function nextPage() {//点击事件
            if (ipage < pages) {//
                ipage++;
                getAnswerList(ipage);
                current_total();
                scrollToTop()
            }
        }
        //上一页
        function prePage() {//点击事件
            if (ipage > 1) {//
                ipage--;
                getAnswerList(ipage);
                current_total();
                scrollToTop()
            }
        }

        //当前页面与总页数显示
        function current_total() {
            var str = ipage + "/" + pages;
            $("#current_total").html(str);
            if (ipage == 1) {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center btn disabled red darken-1");
            } else {
                $("#prePage").attr("class", "col l2 offset-l3 col m2 offset-m3 s5 center waves-effect btn red darken-1");
            }
            if (ipage == pages) {
                $("#nextPage").attr("class", "col l2 m2 s5 btn center disabled red darken-1");

            } else {
                $("#nextPage").attr("class", "col l2 m2 s5 center waves-effect btn red darken-1");
            }
        }
    </script>
</head>
<body>
<%@ include file="/public/navbar.jspf" %>

<div class="container">
    <div class="row">
        <div class="col l9 m12 s12">
            <br>

            <div class="row">
                <div class="card col s12">
                    <div class="card-content">
                        <%--个人头像及名称信息--%>
                        <%--个人头像及名称信息--%>
                        <div class="row valign-wrapper">
                            <div class="col l1 m1 s2">
                                <%--头像--%>
                                <c:if test="${requestScope.question.anonymous==false}">
                                    <img src="${requestScope.question.user.avatar}" class="responsive-img"
                                         onclick="userHomePage('${requestScope.question.user.id}')"></c:if>
                                <c:if test="${requestScope.question.anonymous==true}">
                                    <img src="/image/avatar/anonymous.jpg" class="responsive-img"></c:if>
                            </div>
                            <div class="col l11 m11 s10">

                                <%--当前用户未登录--%>
                                <c:if test="${sessionScope.user==null}">
                                    <c:if test="${requestScope.question.anonymous==true}">
                                        匿名回答
                                    </c:if>
                                    <c:if test="${requestScope.question.anonymous==false}">
                                        <a href="#" class="black-text"
                                           onclick="userHomePage('${requestScope.question.user.id}')">${requestScope.question.user.nickname}</a>
                                    </c:if>
                                </c:if>
                                <%--当前用户登录--%>
                                <c:if test="${sessionScope.user!=null}">
                                    <%--对应的是自己的--%>
                                    <c:if test="${sessionScope.user.id==requestScope.question.user.id}">
                                        <c:if test="${requestScope.question.anonymous==true}">
                                            <a href="#" class="black-text"
                                               onclick="userHomePage('${sessionScope.user.id}')">我的匿名提问</a>
                                        </c:if>
                                        <c:if test="${requestScope.question.anonymous==false}">
                                            <a href="#" class="black-text"
                                               onclick="userHomePage('${sessionScope.user.id}')"> 我的提问</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${sessionScope.user.id!=requestScope.question.user.id}">
                                        <c:if test="${requestScope.question.anonymous==true}">
                                            匿名提问
                                        </c:if>
                                        <c:if test="${requestScope.question.anonymous==false}">
                                            <a href="#" class="black-text"
                                               onclick="userHomePage('${requestScope.question.user.id}')">${requestScope.question.user.nickname}</a>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <span class="grey-text" id="time${requestScope.question.id}"></span>
                                <c:if test="${requestScope.isCurrentUser==1}">
                                    <a href="javascript:void(0)" class="text-black right" onclick="updateShow()">编辑</a>
                                </c:if>
                                <script type="text/javascript">
                                    var id = "#time" + "${requestScope.question.id}";
                                    $(id).html(jsDateDiff('${requestScope.question.createTime}'))
                                </script>
                            </div>
                        </div>

                        <%--问题标题--%>
                        <div class="row">
                            <%--进入具体回答--%>
                            <a href="#">
                                <h5 onclick="question('${requestScope.question.id}')"
                                    class="red-text darken-1">${requestScope.question.title}
                                </h5>
                            </a>

                            <%--话题--%>
                            <a href="#" class="grey-text"
                               onclick="category('${requestScope.question.category.id}')">
                                ${requestScope.question.category.category}</a>
                        </div>

                        <%--问题内容--%>
                        <div id="mainContent">
                            <div class="col s12">
                                <%--隐藏的问题--%>
                                <input id="${requestScope.question.id}" type="hidden"
                                       value='${requestScope.question.content}'/>
                                <%--显示的问题--%>
                                <div class="row" id="div${requestScope.question.id}"></div>
                                <script type="text/javascript">
                                    getSlimContent('${requestScope.question.id}')
                                </script>

                            </div>
                            <div class="divider"></div>

                            <%--展开评论修改等功能底部栏--%>

                            <div id="spreadButtomBar${requestScope.question.id}" class="row" style="display: none">

                                <%-- <a href="javascript:void (0)"
                                    class="black-text col offset-s1">${requestScope.commentNumList[status.index]}条评论</a>--%>
                                <a href="javascript:void (0)" onclick="getSlimContent('${requestScope.question.id}')"
                                   class="waves-effect btn red darken-1 col l2 m2 s4">收起</a>
                                <a href="javascript:void (0)"
                                   class="black-text col offset-s1 right">${requestScope.qCommentNum}条评论</a>
                            </div>

                            <div id="slimButtomBar${requestScope.question.id}" class="row">
                                <a href="javascript:void (0)" onclick="spreadContent('${requestScope.question.id}')"
                                   class="waves-effect btn grey lighten-1 col l2 m2 s4">显示全部</a>
                                <a href="javascript:void (0)"
                                   class="black-text col offset-s1 right">${requestScope.qCommentNum}条评论</a>
                            </div>
                        </div>

                        <%--修改问题--%>
                        <div id="update">

                            <textArea id="change_content" class="col s12 z-depth-1" name="content"></textArea>

                            <div class="right">
                                <input type="checkbox" value="true" name="anonymous" class="filled-in"
                                       id="filled-in"/>
                                <label for="filled-in">匿名</label>


                                <a type="button" onclick="update()"
                                   class="waves-effect btn red darken-1">提交修改</a>
                                <a type="button" onclick="updatehide()"
                                   class="waves-effect btn red darken-1">取消</a>

                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div id="answer"></div>
            <%--上一页，下一页的显示--%>
            <c:if test="${requestScope.answerNum>0}">
                <div class="row">
                    <a id="prePage" onclick="prePage()">上一页</a>

                    <div class="col l2 m2 s2 text-black center-align" id="current_total"></div>
                    <a id="nextPage" onclick="nextPage()">下一页</a>
                </div>
            </c:if>

            <br>
            <br>

            <form id="form">
                <input type="hidden" name="question.id" value="${requestScope.question.id}">


                <textArea id="ueditor" class="col s12 z-depth-1" name="content"></textArea>


                <div class="col l3 offset-l5 m3 offset-m5 s6">
                    <p>
                        <input type="checkbox" value="true" name="anonymous" class="filled-in" id="filled-in-box"/>
                        <label for="filled-in-box">匿名</label>
                    </p>
                </div>
                <div class="col l4 m4 s6">
                    <a type="button" onclick="addAnswer()"
                       class="col s12 waves-effect waves-light btn-large red darken-1">发布回答</a>
                </div>
            </form>

        </div>
        <div class="col l3 hide-on-med-and-down">
            <%@ include file="../public/sidebar.jspf" %>
        </div>
    </div>
</div>

</div>
</body>
</html>
