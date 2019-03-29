<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" type="text/css" href="../css/weui1.0.css">
    <title>个人中心</title>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
<body>
<form id="profileForm">
    <div class="container" id="container">
        <div class="page input js_show">
            <div class="weui-cells__title">我的资料</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">头像</label></div>
                    <div class="weui-cell__bd">
                        <img src="../image/head_logo/132" class="head_img"
                             style="width:50px; height: 50px; border: 1px solid #ccc; border-radius: 50%"
                             onclick="addImgItem()">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="text" name="user_name" value="守护落落"
                               placeholder="请输入昵称">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="text" name="phone" value="" placeholder="请输入手机号">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">微信</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" name="weixin" type="text" value="" placeholder="请输入微信">
                    </div>
                </div>
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">QQ</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" name="qq" type="text" value="" placeholder="请输入QQ">
                    </div>
                </div>

            </div>
            <div class="weui-cells__title">我的圈子</div>
            <div class="weui-cells">
                <div class="weui-cell weui-cell_switch">
                    <div class="weui-cell__bd">全平台共享资料，扩大业务范围</div>
                    <div class="weui-cell__ft">
                        <input class="weui-switch" type="checkbox">
                    </div>
                </div>
                <a class="weui-cell weui-cell_access" href="javascript:;">
                    <div class="weui-cell__bd">
                        <p>cell</p>
                    </div>
                    <div class="weui-cell__ft">
                    </div>
                </a>
            </div>
            <a name="profile"></a>
            <div class="weui-cells__title">我的行业范围介绍【<a href="#" class=" weui-cell_access weui-cell_link">添加自营公司/门店，揽客、招代理</a>】</div>
            <div class="weui-cells">

                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">公司名称</label></div>
                    <div class="weui-cell__bd">
                        <input class="weui-input" name="weixin" type="text" value="" placeholder="请输入公司名称">
                    </div>
                </div>
                <div class="page-bd-15 weui-cells_checkbox">
                    <div class="weui-flex">
                        <div class="weui-flex__item">
                            <label class="weui-cell weui-check__label" for="x11">
                                <div class="weui-cell__hd">
                                    <input type="radio" class="weui-check" name="radio1" id="x11">
                                    <span class="weui-icon-checked"></span>
                                </div>
                                <div class="weui-cell__bd">
                                    <p>服务</p>
                                </div>
                            </label>
                        </div>
                        <div class="weui-flex__item">
                            <label class="weui-cell weui-check__label" for="x12">
                                <div class="weui-cell__hd">
                                    <input type="radio" name="radio1" class="weui-check" id="x12" checked="checked">
                                    <span class="weui-icon-checked"></span>
                                </div>
                                <div class="weui-cell__bd">
                                    <p>产品</p>
                                </div>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="weui-cell_select">
                    <div class=" weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">行业范围</label></div>
                        <div class="weui-cell__bd ">
                            <select class="weui-select" name="select1">
                                <option value="1">中国</option>
                                <option value="2">美国</option>
                                <option value="3">英国</option>
                            </select>
                        </div>
                    </div>
                    <div class=" weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">具体范围</label></div>
                        <div class="weui-cell__bd ">
                            <select class="weui-select" name="select1">
                                <option value="1">中国</option>
                                <option value="2">美国</option>
                                <option value="3">英国</option>
                            </select>
                        </div>
                    </div>
                    <div class=" weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label">经营方式</label></div>
                        <div class="weui-cell__bd ">
                            <select class="weui-select" name="select1">
                                <option value="1">制造/批发</option>
                                <option value="2">渠道/批发</option>
                                <option value="3">零售</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <a name="resoure"></a>
            <div class="weui-cells__title">我的资源优势【可更改】 <span class="show_result resoure_result">(你还能输入51字/限140字)</span>
            </div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <textarea class="weui-textarea" name="resoure_advantage" id="resoure"
                                  onkeyup="checkWords(this, &#39;.resoure_result&#39;, 140)" placeholder="请输入我的资源优势"
                                  rows="5">①：优鲜共享（APP）平台，提供智能花盆、种菜机等个人养花种菜设备、耗材及服务。②：中小型植物工厂设计、建设及落地服务。③：编界，热文广告植入工具。帮助销售人员高效在线精准拓客。</textarea>
                    </div>
                </div>
            </div>
            <a name="needs"></a>
            <div class="weui-cells__title">我需要的资源【可更改】 <span class="show_result needs_result">(你还能输入67字/限140字)</span>
            </div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <textarea class="weui-textarea" name="needs_information" id="needs"
                                  onkeyup="checkWords(this, &#39;.needs_result&#39;, 140)" placeholder="请输入我需要的资源"
                                  rows="5">面向全国招收兼职销售人员。无需资金投入，每天工作一小时，月入2W+。电联：13006687999（非诚勿扰）。【注：群主优先，限99人，招满即止】</textarea>
                    </div>
                </div>
            </div>
            <div class="weui-cells__title">我的二维码【点击更改】 <span class="show_result">(请上传比例为16:9或1:1的二维码图片)</span></div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd" style="text-align: center;">
                        <img id="upload_ads_img" src="./个人中心_files/6db3602763b266bede1a4e2aff586e36.jpg"
                             style="width: 80%;">
                    </div>
                </div>

                <div class="weui-btn-area" id="profileSave">
                    <input type="hidden" id="err1" value="0">
                    <input type="hidden" id="err2" value="0">
                    <input type="hidden" id="err3" value="0">
                    <button type="button" class="weui-btn weui-btn_primary" data-href="javascript:" id="showTooltips"
                            style="border:none;background: #12D5C7;">保存
                    </button>
                </div>

            </div>
        </div>
    </div>
</form>
<input type="file" id="file" name="" style="display: none;">
<input type="file" id="file1" name="" style="display: none;">
<style type="text/css">
    .weui-switch:checked, .weui-switch-cp__input:checked ~ .weui-switch-cp__box {
        border-color: #12D5C7;
        background-color: #12D5C7;
    }

    .show_result {
        color: #999;
        font-size: 11px;
        padding-left: 10px;
    }
</style>
<script type="text/javascript">
    var errNum = 0;
    var msg = '';
    var errNo = 0;

    function checkWords(obj, resultClassName, limitWords) {
        var length = limitWords;
        var content = $(obj).val();
        var content_len = content.length;
        var in_len = length - content_len;

        switch (resultClassName) {
            case '.profile_result':
                msg = '我的简介';
                errNo = 1;
                break;
            case '.resoure_result':
                msg = '我的资源优势';
                errNo = 2;
                break;
            case '.needs_result':
                msg = '我需要的资源';
                errNo = 3;
                break;
        }

        if (in_len >= 0) {
            $('#err' + errNo).val(0);
            $(resultClassName).html('(你还能输入' + in_len + '字/限' + limitWords + '字)');
        } else {
            $('#err' + errNo).val(1);
            $(obj).val(content.substr(0, limitWords));
            var errMsg = '<em style="color:red; font-size: 11px; font-style: normal;">(您已经超出' + (-in_len) + '字数/限' + limitWords + '字！)</em>';
            $(resultClassName).html(errMsg);
            return false;
        }
    }

    checkWords('#profile', '.profile_result', 70);
    checkWords('#resoure', '.resoure_result', 140);
    checkWords('#needs', '.needs_result', 140);

    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1) {
        bind_name = 'propertychange';
    }

    $('#profile').bind(bind_name, function () {
        checkWords(this, '.profile_result', 70);
    });
    $('#resoure').bind(bind_name, function () {
        checkWords(this, '.resoure_result', 140);
    });
    $('#needs').bind(bind_name, function () {
        checkWords(this, '.needs_result', 140);
    });

    $('.weui-switch-cp__input').click(function () {
        if ($(this).prop('checked')) {
            $(this).val(1);
        }
        else {
            $(this).val(0);
        }
    });

    $('#profileSave button').click(function () {

        var err1 = $('#err1').val();
        var err2 = $('#err2').val();
        var err3 = $('#err3').val();

        if (err1 > 0) {
            alert('我的简介的填写超出限制字数，请仔细检查！');
            return false;
        }

        if (err2 > 0) {
            alert('我的资源优势的填写超出限制字数，请仔细检查！');
            return false;
        }

        if (err3 > 0) {
            alert('我需要的资源的填写超出限制字数，请仔细检查！');
            return false;
        }

        var userName = $('.wexin-user').val();

        if (!userName) {
            alert('昵称不能为空！');
            return false;
        }

        $.post("/index/user/profilesave.html", $('#profileForm').serialize(), function (res) {
            if (res.errcode == 200) {
                alert('保存成功！');
                window.location.reload();
            } else {
                alert(res.msg);
            }
        });
    });

    $('.weui-vcode-btn').click(function () {
        var phone = $('.phone').val();
        var userName = $('.wexin-user').val();
        if (!phone) {
            alert('手机号不能为空！');
            return false;
        }

        $.post("/index/user/profilesave.html", {
            "save_phone": "only",
            "user_name": userName,
            "phone": phone
        }, function (res) {
            if (res.errcode == 200) {
                alert('修改成功！');
                //window.location.reload();
            } else {
                alert(res.msg);
            }
        });
    });

    function loadDiv(text) {
        var div = "<div id='_layer_'> <div id='_MaskLayer_' style='filter: alpha(opacity=30); -moz-opacity: 0.3; opacity: 0.3;background-color: #000; width: 100%; height: 100%; z-index: 1000; position: absolute;" + "left: 0; top: 0; overflow: hidden; display: none'></div><div id='_wait_' style='background:#fff; z-index: 1005; position: absolute; width:200px; margin:0 auto;-moz-animation-duration: s;-o-animation-duration: s;animation-duration: s;height:65px; padding-top: 10px; color: #999; text-align: center; margin: 0 auto; display: none'  ><p style='text-align: center'><img src='/static/test/loading.gif' /><br>" + "" + text + "</p><button onclick='LayerHide()' style='display:none;'>关闭</button></div></div>";
        return div;
    }

    function LayerShow(text) {
        var addDiv = loadDiv(text);
        var element = $(addDiv).appendTo(document.body);
        $(window).resize(Position);
        var deHeight = $(document).height();
        var deWidth = $(document).width();
        Position();
        $("#_MaskLayer_").show();
        $("#_wait_").show();
    }

    function Position() {
        $("#_MaskLayer_").width($(document).width());
        $("#_MaskLayer_").height($(document).height());
        var deHeight = $(window).height() + $(window).scrollTop();
        var deWidth = $(window).width();

        $("#_wait_").css({
            left: (deWidth - $("#_wait_").width()) / 2 + "px",
            top: (deHeight - $("#_wait_").height() - $(window).height() / 2) + "px"
        });
    }

    function LayerHide() {
        $("#_MaskLayer_").hide();
        $("#_wait_").hide();
        del();
    }

    function del() {
        var delDiv = document.getElementById("_layer_");
        delDiv.parentNode.removeChild(delDiv);
        //删除
    }

    var file = document.getElementById('file');

    function addImgItem() {
        file.click();
    }

    file.onchange = function () {
        UpladFile();
    }

    function UpladFile() {
        LayerShow('正在上传，请稍侯...');
        var FileController = "/index/user/headimg.html";// 接收上传文件的后台地址
        // FormData 对象
        var form = new FormData();
        form.append("file", file.files[0]);                           // 文件对象
        // XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
        xhr.onload = function (data) {
            var obj = JSON.parse(data.currentTarget.response);
            if (obj.errcode == 200) {
                $('.head_img').attr('src', obj.data);
                file.value = '';
                LayerHide();
            } else {
                alert(obj.msg);
                LayerHide();
            }
        };
        xhr.send(form);
    }

    var file1 = document.getElementById('file1');

    $('#upload_ads_img').click(function (e) {
        if (e && e.preventDefault) {
            e.preventDefault();
        }
        file1.click();
    });
    //    function addUploadAdsItem(){
    //
    //    }

    file1.onchange = function () {
        UpladAdsFile();
    }

    function UpladAdsFile() {
        LayerShow('正在上传，请稍侯...');
        var FileController = "/index/user/uploadadsimg.html";// 接收上传文件的后台地址
        // FormData 对象
        var form1 = new FormData();
        form1.append("file1", file1.files[0]);                           // 文件对象
        // XMLHttpRequest 对象
        var xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
        xhr.onload = function (data) {
            var obj = JSON.parse(data.currentTarget.response);
            if (obj.errcode == 200) {
                $('#upload_ads_img').attr('src', obj.data);
                file1.value = '';
                LayerHide();
            } else {
                alert(obj.msg);
                LayerHide();
            }
        };
        xhr.send(form1);
    }
</script>

</body>
</html>