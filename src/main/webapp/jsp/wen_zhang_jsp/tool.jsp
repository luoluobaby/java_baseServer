<%@ page import="com.util.PageValues" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/9/19
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = PageValues.RESOURCES_BASE_PATH ;
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="<%=path%>css/weui1.0.css">
    <title>启动编辑器</title>
    <script type="text/javascript" src="<%=path%>js/jquery.min.js"></script>
</head>
<body>
<div class="container" id="container">

    <!--购买VIP end-->
    <div class="page input js_show">
        <form class="wx-form" action="https://bianjie.zuzitech.com/index/article/parselurl.html" method="post">
            <div class="page__bd">
                <div class="weui-cells__title">微信原文链接</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <textarea class="weui-textarea" name="wx_url" placeholder="将现有的微信文章链接粘贴到这里，可以更改联系人名称，重新发送！" rows="5"></textarea>
                        </div>
                    </div>
                </div>

                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_primary" href="javascript:" id="showTooltips" style="background: #45bdb4">启动编辑器</a>
                </div>


                <div class="weui-btn-area" style="position: relative;">
                    <a class="weui-btn weui-btn_primary" href="https://bianjie.zuzitech.com/index/origin/create.html" style="background: #45bdb4">开始原创</a>
                    <span style="color:white;background:red;font-size:11px;padding-left:3px;padding-right:3px;border-radius:5px;position:absolute;right:-5px;top:-10px;">推荐!</span>
                </div>
            </div>
        </form>
    </div>
</div>

<div style="text-align: center; margin-top: 65px;"><a href="/index/article/introduce.html" style="font-size:16px; color: #2e75f6; text-decoration: underline;">查看操作说明</a></div>
<div style="text-align: center; margin-top: 15px;"><a href="/index/guide/downloadapp.html" style="font-size:16px; color: #2e75f6; text-decoration: underline;">下载编界APP</a></div>
<script type="text/javascript">
    $('#showTooltips').click(function (){
        var url = $('.weui-textarea').val();
        url = trimStr(url);
        if(!url.match(/https?:\/\//))
        {
            alert('请输入微信文章连接！');
            return false;
        }else{
            $('.wx-form').submit();
        }
    });
    function trimStr(str){ str = str.replace(/[\r\n]/g,""); return str.replace(/(^\s*)|(\s*$)/g,"");}
</script>
</body>
</html>