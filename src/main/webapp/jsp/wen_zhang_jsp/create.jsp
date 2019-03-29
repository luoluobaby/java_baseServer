<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/9/19
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.util.PageValues" %>
<%
    String path = PageValues.RESOURCES_BASE_PATH;
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>正在原创编辑...</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link href="<%=path%>css/wen_zhang/popup.min.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="<%=path%>js/jquery.min.js"></script>
    <script src="<%=path%>js/wen_zhang/popup.js" type="text/javascript"></script>
    <script src="<%=path%>js/wen_zhang/until.js" type="text/javascript"></script>
    <script src="<%=path%>js/wen_zhang/layer.js" type="text/javascript"></script>
    <link href="<%=path%>css/wen_zhang/layer.css" type="text/css" rel="styleSheet" id="layermcss">
    <style type="text/css">
        button.show-btn {
            font-size: 18px;
            font-weight: bold;
            color: white;
            background-color: #999;
            border: none;
            display: block;
            margin: 0 auto;
            width: 25px;
            height: 25px;
            border-radius: 50%;
            padding-left: 5px;
            padding-right: 5px;
            line-height: 25px;
        }

        .review-title input, #origin-box-wrap .title input {
            border: none;
            font-size: 15px;
            color: #000;
            padding: 5px;
            width: 100%;
            margin-bottom: 8px;
        }

        .review-title input::-webkit-input-placeholder, #origin-box-wrap .title input::-webkit-input-placeholder {
            color: #000;
        }

        .item-list-box {
            margin-top: 5px;
            display: flex;
            height: 155px;
            overflow: hidden; /*border:1px solid #ededed;*/
        }

        .item-list-box .show-icon1 {
        }

        .item-list-box .item-list-left {
            flex: 4; /*border-right: 1px solid #ededed;*/
        }

        .item-list-box .show-item-content img { /* width:60px; height:60px;*/
            width: 98%;
            height: 100%;
        }

        .item-list-box .item-list-right {
            flex: 4;
            word-wrap: break-word;
            word-break: break-all;
            padding-left: 5px;
            border-left: 1px dashed #ededed;
            height: 150px;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .create-item-list {
            display: flex;
            background: #fff;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        .create-item-list .btn-area {
            flex: 0.35;
            margin-top: 50px;
        }

        .item-list-box {
            flex: 4
        }

        .btn-two-buttons {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .inputForm:empty::before {
            color: lightgrey;
            content: attr(placeholder);
        }

        .item-list-left .toolbar {
            flex: 4;
        }

        .item-list-left .show-item-content {
            height: 122px;
            text-align: center;
        }

        .toolbar {
            height: 25px;
            margin: 2px;
        }

        .toolbar-item {
            display: flex;
            background: #ededed;
            padding: 2px;
            width: 60%;
            margin: 0px auto;
            border-radius: 3px;
            width: 100%;
        }

        .toolbar-item a {
            flex: 1;
            width: 32%;
        }

        .toolbar-item img {
            width: 25px;
            height: 25px;
            text-align: center;
            margin: 0 auto;
            display: block;
        }

        .create-item-list-warp .btn-area {
            margin: 0 auto;
        }

        .delete-btn {
            position: absolute;
            right: -5px;
            top: -10px;
            font-size: 12px;
        }

        #origin-box-wrap .next-step {
            background: #45bdb4;
            padding: 5px;
            color: white;
            position: absolute;
            right: 0;
            top: 0;
            border: none;
            border-radius: 5px;
        }

        #origin-content {
            text-align: center;
            padding-left: 5px;
            padding-right: 5px;
        }

        #origin-content img {
            border: none;
            max-width: 100%;
            height: auto;
        }

        #origin-content p {
            text-align: left;
            font-size: 13px;
            color: #000;
        }

        .review-box-wrap .next-step {
            background: #45bdb4;
            padding: 5px;
            color: white;
            position: absolute;
            right: 0;
            top: 0;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>

<body style="background: #ededed;">
<div id="origin-box-wrap">

    <div style="background: #fff; padding:5px; position: fixed;width: 96%;top:5px;z-index:2">
        <div class="title" style="position: relative;">
            <input type="text" name="title" value="" placeholder="请点击这里输入标题" onclick="insertTitle(this)">
            <button class="next-step" onclick="nextStep(this)">提交预览</button>
        </div>

        <div class="authorinfo_22reljxts" style="text-align:left;">
            <span class="pubdate_22reljxts" style="color:#999; font-size:13px; padding-left:8px;">2018-09-19</span>&nbsp;&nbsp;<span
                class="author_22reljxts" style="color:#2D569F;font-size:13px;">守护落落</span>
        </div>
    </div>

    <div id="createDefault" style="margin-top: 80px;">
        <div class="btn-area">
            <button class="show-btn show-btn-0" data-button-id="0" onclick="createItemList(this)">+</button>
        </div>
    </div>

    <input type="file" id="uploadImgFile" name="" style="display: none;">

</div>

<div id="show-review-box" style="display:none;">

    <div class="review-box-wrap" style="  margin-top:10px; margin-left:8px; margin-right:8px;">
        <div class="review-title" style="position:relative;">
            <a href="javascript:;" onclick="layer.closeAll();"
               style="position:absolute;left:0; color:#45bdb4; font-size:16px; text-decoration: none;">关闭</a>
            <input type="text" id="review-title" name="title"
                   style="margin-top: 30px;border: none;width: 100%;text-align: left;" placeholder="">
            <button class="next-step" onclick="saveOrigin(this)">确认提交</button>
        </div>

        <div class="authorinfo_22reljxts" style="text-align:left;">
            <span class="pubdate_22reljxts" style="color:#999;font-size:13px; padding-left:8px;">2018-09-19</span>&nbsp;&nbsp;<span
                class="author_22reljxts" style="font-size:13px;color:#2D569F;">守护落落</span>
        </div>

        <div id="origin-content"></div>

    </div>
</div>

<form style="display: none;" class="save_form_22reljxts" method="post">
    <input type="text" name="title" value="">
    <input type="text" name="author" value="守护落落">
    <input type="text" name="pubdate" value="2018-09-19">
    <input type="text" name="cover" value="">
    <input type="text" name="source" value="">
    <input type="text" name="org_title" value="">
    <input type="text" name="is_origin" value="1">
    <input type="text" name="id" value="">
    <input type="text" name="json_data" value="">
    <textarea name="content" class="save_content_22reljxts"></textarea>
</form>


<script type="text/javascript" src="<%=path%>js/wen_zhang/origin.editor.min.js"></script>
<script>
    if (!checkContent()) {
        var _df = $('#createDefault button');
        createItemList(_df);
    }
</script>
</body>
</html>
