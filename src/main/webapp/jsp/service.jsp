<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/9/8
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="../css/weui.css"/>
    <link rel="stylesheet" href="../css/weui2.css"/>
    <script src="../js/zepto.min.js"></script>
</head>
<body class="page-bg">
<div class="tcenter" style="overflow:hidden; ">
    <img class=" img-radius" style="margin:10px auto 0;width:50px;height:50px;"
         src="../image/head_logo/1/logo.jfif">
</div>
<div class="page-hd">
    <h1 class="page-hd-title">
        中骏生活服务
    </h1>
    <p class="page-hd-desc">
        该服务由中骏住户提供，同时服务于中骏的住户，实现资源共享。
    </p>
</div>
<div class="searchbar_wrap">

</div>
<div style="margin-top: 30px"></div>
<div class="weui_panel weui_panel_access">
    <a href="cell4_.html" class="weui_media_box weui_media_appmsg">
        <div class="weui_media_hd weui-updown"><img class="weui_media_appmsg_thumb lazyload"
                                                    src="http://d8.yihaodianimg.com/N05/M03/D4/1E/CgQI0lYEBwWAQIvJAAFyostaOYY12501_80x80.jpg"
                                                    alt="" style="animation: fadeIn 1s ease 0.2s 1 both;"></div>
        <div class="weui_media_bd"><h4 class="weui_media_title">1 Apple 苹果 iPhone 6s Plus (A1699) 16G 玫瑰金色 移动联通电信4G
            全网通手机</h4>
            <p class="weui_media_desc">2015-12-01</p></div>
    </a>
    <a href="cell4_.html" class="weui_media_box weui_media_appmsg">
        <div class="weui_media_hd weui-updown"><img class="weui_media_appmsg_thumb lazyload"
                                                    src="http://d8.yihaodianimg.com/N05/M03/D4/1E/CgQI0lYEBwWAQIvJAAFyostaOYY12501_80x80.jpg"
                                                    alt="" style="animation: fadeIn 1s ease 0.2s 1 both;"></div>
        <div class="weui_media_bd"><h4 class="weui_media_title">1 Apple 苹果 iPhone 6s Plus (A1699) 16G 玫瑰金色 移动联通电信4G
            全网通手机</h4>
            <p class="weui_media_desc">2015-12-01</p></div>
    </a>
</div>
<script>
    $(function () {

        $('.searchbar_wrap').searchBar({
            cancelText: "取消",
            searchText: '关键字',
            onfocus: function (value) {

            },
            onblur: function (value) {

            },
            oninput: function (value) {

            },
            onsubmit: function (value) {
            },
            oncancel: function () {

            },

            onclear: function () {

            }
        });
    });
</script>
</body>
</html>
