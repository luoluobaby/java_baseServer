<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/9/7
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="weui-menu">
    <div class="weui-menu-inner">
        <em></em>
        <span>内测</span>
        <ul>
            <em></em>
            <li>微信</li>
            <li>QQ</li>
            <li>陌陌</li><li>陌陌陌陌陌陌</li>
        </ul>
    </div>
    <div class="weui-menu-inner">
        <em></em>
        <span>公测</span>
        <ul>
            <em></em>
            <li>淘宝</li>
            <li>支付宝</li>
            <li>天猫</li><li>陌陌陌陌陌陌</li><li>陌陌陌陌陌陌</li><li>陌陌陌陌陌陌</li><li>陌陌陌陌陌陌</li>
        </ul>
    </div>
    <div class="weui-menu-inner">
        <em></em>
        <span>上线测试</span>
        <ul>
            <em></em>
            <li>百度地图</li>
            <li>百度一下</li>
            <li>百度智能</li>
        </ul>
    </div>

</section>
<script>
$(function(){
$('.weui-menu-inner').click(function () {
var $menu = $(this).find('ul'),
height = $menu.find('li').length * 40 + 15 + 'px',
opacity = $menu.css('opacity');

$('.weui-menu-inner ul').css({
'top': '0',
'opacity': '0'
});

if(opacity == 0) {
$menu.css({
'top': '-' + height,
'opacity': 1
});
}else {
$menu.css({
'top': 0,
'opacity': 0
});
}
});

});

</script>