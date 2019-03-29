<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/9/7
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="../css/weui.css">
    <link rel="stylesheet" href="../css/weui2.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/center.css">
</head>
<body>
<div class="weui_tab " style="height:44px;" id="tab1"><!--tab-fixed添加顶部-->
    <div class="weui_navbar" style="height:44px;">
        <div class="weui_navbar_item">
            已发货
        </div>
        <div class="weui_navbar_item">
            未发货
        </div>
    </div>
</div>
<div class="container">


</div>
<div class="btn_Toggle show" id="btn_Toggle">
    <ul class="icon_lists clear">
        <li><span class="icon icon-108"></span></li>
    </ul>
</div>
<script src="../js/zepto.min.js"></script>
<script>
    $(function(){
        $('#tab1').tab({defaultIndex:0,activeClass:"tab-green"});
         $("#btn_Toggle").click(function () {
             $("#btn_Toggle span").toggleClass("icon-109");
         });
  });

</script>
<%@include file= "bottom_nav.jsp"%>
</body>
</html>
