<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype HTML>
<html>
<head>
    <%@include file="/public/head.jspf" %>
    <link type="text/css" rel="stylesheet" href="/materialize/css/materialize.min.css" media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/materialize/css/style.css" media="screen,projection"/>
    <script type="text/javascript" src="/materialize/js/cropbox.js"></script>
    <script type="text/javascript">
        $(function () {
            var options =
            {
                thumbBox: '.thumbBox',
                spinner: '.spinner',
                imgSrc: '/image/avatar/default.jpg'
            };
            var cropper = $('.imageBox').cropbox(options);
            $('#upload-file').on('change', function () {
                var reader = new FileReader();
                reader.onload = function (e) {
                    options.imgSrc = e.target.result;
                    cropper = $('.imageBox').cropbox(options);
                };
                reader.readAsDataURL(this.files[0]);
                this.files = [];
            });
            $('#btnCrop').on('click', function () {
                var img = cropper.getDataURL();
                $('.cropped').html('');
                $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:180px;margin-top:4px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
            });
            $('#btnZoomIn').on('click', function () {
                cropper.zoomIn();
            });
            $('#btnZoomOut').on('click', function () {
                cropper.zoomOut();
            })


        })

    </script>
</head>
<body>
<%@ include file="/public/navbar.jspf" %>
<div class="container">
    <div class="col l9 s12">
        <div class="imageBox col s6  center-align">
            <div class="thumbBox"></div>
        </div>
        <div class="cropped col s6"></div>
        <!-- <input type="file" id="file" style=" width: 200px">-->

        <div class=" col s4">
            <div class="file-field input-field">
                <div class="btn red darken-1">
                    <span>上传头像</span>
                    <input type="file" id="upload-file"/>
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text">
                </div>
            </div>
        </div>
        <a type="button" id="btnCrop" class="col s4 btn red darken-1 ">裁切</a>
        <a type="button" id="btnZoomIn" class="col s4 btn red darken-1">+</a>
        <a type="button" id="btnZoomOut" class="col s4 btn red darken-1">-</a>

    </div>
    <div class="col l3 hide-on-med-and-down">


        <%@ include file="../public/sidebar.jspf" %>
    </div>
</div>
</body>
</html>
