<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var aInputId = "";//定义的隐藏input的id
    var divId = "";
    var updateDiv = "";
    var ueditoDivID = "";
    var footDiv = "";
    var plane = "";
    var updateContent;//回答的编辑框
    $(function () {
        initImgClass
    });


    //更新回答
    function updateAnswer(hiddenInputId) {
        aInputId = hiddenInputId;
        //先隐藏内容显示 拼接id 编辑框显示
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
        var content = updateContent.getContent();
        $.ajax({
            url: '/answer/update.action',
            type: "post",
            data: {
                aid: aInputId, content: content
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

    <div class="row" id="class${answer.id}">
            <%--个人头像及名称信息--%>
        <span class="col s12 grey-text" id="time${answer.id}"></span>
        <script type="text/javascript">
            var id = "#time" + "${answer.id}";
            $(id).html(jsDateDiff('${answer.createTime}'))
        </script>
        <div class="row col s12"><a href="#"><h5 onclick="question('${answer.question.id}')"
                                                 class="red-text darken-1"> ${answer.question.title}</h5></a>

            <input id="${answer.id}" type="hidden" value='${answer.content}'/>

                <%--显示的回答--%>
            <a href="#" class="black-text" onclick="answer('${answer.id}')"
               id="div${answer.id}"></a>

            <script type="text/javascript">
                getSlimContent('${answer.id}')
            </script>
        </div>
    </div>


    <div class="divider"></div>

</c:forEach>

