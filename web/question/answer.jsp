<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
    var aInputId = "";//定义的隐藏input的id
    var divId = "";
    var updateDiv = "";
    var ueditoDivID = "";
    var footDiv = "";
    var plane = "";
    var checkInput = "";
    var updateContent;//回答的编辑框
    $(function () {
        initImgClass()
    });


    //更新回答
    function updateAnswer(hiddenInputId) {
        aInputId = hiddenInputId;
        //先隐藏内容显示 拼接id 编辑框显示
        checkInput = "#" + aInputId;
        updateDiv = "#update" + aInputId;//需要更新的编辑框
        ueditoDivID = "change" + aInputId;
        plane = "#answerDiv" + hiddenInputId;
        $(updateDiv).show();//编辑框显示
        $(plane).hide();
        //添加文本到um。editor
        updateContent = UM.getEditor(ueditoDivID, {
            initialFrameWidth: null,
            autoClearinitialContent: true,
            initialFrameHeight: 350
        });
        var text = $("#" + hiddenInputId).val();
        updateContent.setContent(text)
    }

    //更新回答相关处理
    function sumbitAnswerChange() {
        //获取更新后的内容
        //

        var isAnonymous = $(checkInput).is(':checked');
        var content = updateContent.getContent();
        $.ajax({
            url: '/answer/update.action',
            type: "post",
            data: {
                aid: aInputId, content: content, anonymous: isAnonymous
            },
            success: function (data) {
                if (data == 'true') {
                    updateAnswershow();
                } else {
                    //其他操作
                }
            }
        })
    }
    //取消修改
    function cancelAnswerChange() {
        $(updateDiv).hide();//编辑框显示
        $(plane).show();
    }
    /**
     * 更新显示内容
     */
    function updateAnswershow() {
        //内容过滤处理
        var content = updateContent.getContent();
        var inputID = "#" + aInputId;
        $(inputID).val(content)//input隐藏框获取相应内容
        var data = getSlimContent(aInputId)//处理并显示过滤后台的行*/
        $(divId).html(data);//内容显示
        $(updateDiv).hide();//编辑框隐藏
        $(plane).show();
    };;

</script>
<c:forEach items="${requestScope.answerList}" var="answer" varStatus="status">


    <div class="card col s12" id="class${answer.id}">
        <div class="card-content">
                <%--个人头像及名称信息--%>
            <div class="row valign-wrapper">
                <div class="col l1 m1 s2">
                        <%--头像--%>
                    <c:if test="${answer.anonymous==false}">
                        <img src="${answer.user.avatar}" class="responsive-img"
                             onclick="userHomePage('${answer.user.id}')"></c:if>
                    <c:if test="${answer.anonymous==true}">
                        <img src="/image/avatar/anonymous.jpg" class="responsive-img"></c:if>
                </div>
                <div class="col l11 m11 s10 valign">

                        <%--当前用户未登录--%>
                    <c:if test="${sessionScope.user==null}"><c:if
                            test="${answer.anonymous==true}">匿名回答</c:if>
                        <c:if test="${answer.anonymous==false}"><a href="#" class="black-text"
                                                                   onclick="userHomePage('${answer.user.id}')">${answer.user.nickname}</a></c:if>
                    </c:if>
                        <%--当前用户登录--%>
                    <c:if test="${sessionScope.user!=null}">
                        <%--对应的是自己的--%>
                        <c:if test="${sessionScope.user.id==answer.user.id}">
                            <c:if test="${answer.anonymous==true}"><a href="#" class="black-text"
                                                                      onclick="userHomePage('${sessionScope.user.id}')">我的匿名回答</a></c:if>
                            <c:if test="${answer.anonymous==false}"><a href="#" class="black-text"
                                                                       onclick="userHomePage('${sessionScope.user.id}')">我的回答</a></c:if>
                        </c:if>
                        <c:if test="${sessionScope.user.id!=answer.user.id}">
                            <c:if test="${answer.anonymous==true}">匿名回答</c:if>
                            <c:if test="${answer.anonymous==false}">
                                <a href="#" class="black-text"
                                   onclick="userHomePage('${answer.user.id}')">${answer.user.nickname}</a>
                            </c:if>
                        </c:if>
                    </c:if>

                    <span class="grey-text" id="time${answer.id}"></span>
                    <c:if test="${sessionScope.user.id==answer.user.id}">
                        <a href="javascript:void(0)" class="text-black right"
                           onclick="updateAnswer('${answer.id}')">编辑</a>
                    </c:if>
                    <script type="text/javascript">
                        var id = "#time" + "${answer.id}";
                        $(id).html(jsDateDiff('${answer.createTime}'))
                    </script>

                </div>
            </div>
            <div class="divider"></div>

            <div id="answerDiv${answer.id}">
                <div class="row">
                        <%--点赞功能--%>
                    <div class="col l1 m2 s2 center-align">
                        <br>
                        <c:if test="${requestScope.isAgreeList[status.index]>0}">
                            <a class="btn-floating waves-effect waves-light red darken-1"
                               onclick="addAgree(this)"
                               name="${answer.id}">${requestScope.agreeNumList[status.index]}</a>
                        </c:if>
                        <c:if test="${requestScope.isAgreeList[status.index]==0}">
                            <a class="btn-floating waves-effect waves-light grey"
                               onclick="addAgree(this)"
                               name="${answer.id}">${requestScope.agreeNumList[status.index]}</a>
                        </c:if>
                    </div>
                        <%--问答内容--%>
                    <div class="col l11 m10 s10">
                            <%--隐藏的回答--%>
                        <input id="${answer.id}" type="hidden" value='<div class="col s12">${answer.content}</div>'/>

                            <%--显示的回答--%>
                        <div class="row " id="div${answer.id}"></div>

                        <script type="text/javascript">
                            getSlimContent('${answer.id}')
                        </script>

                    </div>
                </div>

                <div class="divider"></div>

                    <%--展开评论修改等功能底部栏--%>

                <div id="spreadButtomBar${answer.id}" class="row" style="display: none">

                        <%-- <a href="javascript:void (0)"
                            class="black-text col offset-s1">${requestScope.commentNumList[status.index]}条评论</a>--%>
                    <a href="javascript:void (0)" onclick="getSlimContent('${answer.id}')"
                       class="waves-effect btn red darken-1 col l2 m2 s4">收起</a>
                    <a href="javascript:void (0)"
                       class="black-text col offset-s1 right">${requestScope.commentNumList[status.index]}条评论</a>
                </div>

                <div id="slimButtomBar${answer.id}" class="row">
                    <a href="javascript:void (0)" onclick="spreadContent('${answer.id}')"
                       class="waves-effect btn grey lighten-1 col l2 m2 s4">显示全部</a>
                    <a href="javascript:void (0)"
                       class="black-text col offset-s1 right">${requestScope.commentNumList[status.index]}条评论</a>
                </div>

            </div>
                <%--更新的编辑框--%>
            <div id="update${answer.id}" hidden="hidden">
                <textArea id="change${answer.id}" class="col s12 z-depth-1" name="content"></textArea>
                <br>

                <div class="right">

                    <input type="checkbox" value="true" name="anonymous" class="filled-in"
                           id="filled-in${answer.id}"/>
                    <label for="filled-in${answer.id}">匿名</label>
                    &nbsp;&nbsp;&nbsp;


                    <a type="button" onclick="sumbitAnswerChange()"
                       class="waves-effect btn red darken-1">提交修改</a>
                    <a type="button" onclick="cancelAnswerChange()"
                       class="waves-effect btn red darken-1">取消</a>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
