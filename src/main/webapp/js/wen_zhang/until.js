// var getSelectedNode = [];
// var changed = document.getElementById('change_html');
// var selected = $('.selected_item');
// var file = document.getElementById('file');
// var textarea = document.getElementById('add_text_textarea');
// var video_textarea = document.getElementById('add_video_textarea');
// var link_textarea = document.getElementById('add_link_textarea');
//
// $('#change_html').show();

function getInsertNode() {
    //var selection = window.getSelection ? window.getSelection() : document.selection;
    var selection = window.getSelection ? window.getSelection() : document.selection;
    var range = selection.createRange ? selection.createRange() : selection.getRangeAt(0);
    return range;
}

// file.onchange = function(){
//     UpladFile();
// };
//
// changed.onclick = function () {
//     event.stopPropagation();
// };

function checkUrl(urlString){
    if(urlString != ""){
        var reg=/(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?/;
        if(!reg.test(urlString)){
            return false;
        }
        return true;
    }
    return false;
}

function getClass(tagname, className) {
    //tagname指元素，className指class的值
    //判断浏览器是否支持getElementsByClassName，如果支持就直接的用
    if (document.getElementsByClassName) {
        return getElementsByClassName(className);
    }
    else {    //当浏览器不支持getElementsByClassName的时候用下面的方法
        var tagname = document.getElementsByTagName_r(tagname);  //获取指定元素
        var tagnameAll = [];     //这个数组用于存储所有符合条件的元素
        for (var i = 0; i < tagname.length; i++) {     //遍历获得的元素
            if (tagname[i].className == className) {     //如果获得的元素中的class的值等于指定的类名，就赋值给tagnameAll
                tagnameAll[tagnameAll.length] = tagname[i];
            }
        }
        return tagnameAll;
    }
}

$("document").ready(function () {
    $("#js_content a").click(function(event){
        event.preventDefault();
    });

    $("#js_content").bind("click", function (event) {
        $("#js_content").find("*").removeClass("selected_item");
        var clickedNode = event.target;
        var NodeType = event.target.nodeName;

        if(clickedNode.id != 'js_content')
        {
            var $$myNode = $(clickedNode);
            var _top = $$myNode.offsetTop;
            //$("#editText").css({"top":_top+"px"});
            //$("#edit_txt").val($$myNode.text());
            $('#change_html').show();
            if( $$myNode.hasClass('temp_notice') )
            {
                $$myNode.parent().addClass('selected_item');
                return;
            }
            $$myNode.addClass("selected_item");
        }

    })
});

function UpladFile() {
    LayerShow('正在上传，请稍侯...');
    var FileController = "/index/article/uploadimg";                    // 接收上传文件的后台地址
    // FormData 对象
    var form = new FormData();
    form.append("file", file.files[0]);                           // 文件对象
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (data) {
        // alert("上传完成!");
        var obj = JSON.parse(data.currentTarget.response);
        if(obj.errcode==200){
            var img = document.createElement("img");
            var x = getInsertNode();
            img.setAttribute('src',obj.data);
            img.setAttribute('class','insert_img');
            img.setAttribute('style','margin-top:15px; margin-bottom:15px;');
            //insertAfter(img, x);
            insertImage(obj.data);
            file.value = '';
            LayerHide();
        }else{
            alert(obj.msg);
            LayerHide();
            //console.log(obj);
        }
    };
    xhr.send(form);
}

function cancelItem(){
    $("#js_content").find(".insert_content").last().remove();
    $("#js_content").find(".insert_img").last().remove();
    $(".selected_item").last().css('display', 'block');
    changed.setAttribute('style', 'display:none');
    $('#make_sure').css('display', 'block');
    $("#js_content").find("*").removeClass("selected_item");
}

function addImgItem(){
    file.click();
}

function addTextSure(){
    var x = getInsertNode();
    var newNode = document.createElement("div");
    var nickname = '<{$user.user_name}>';
    var myDate = new Date();
    //var datetime = myDate.getFullYear() + '-' + myDate.getMonth() + '-' + myDate.getDay();
    var datetime = '';
    textarea.setAttribute('style', 'display:none');
    newNode.setAttribute('class', 'insert_content');
    newNode.setAttribute('style','background: rgb(254,255,239);width:100%;margin: 10px auto;border-top: 1px solid #ccc;border-bottom: 1px solid #ccc;text-align: center; font-weight: 500;');
    newNode.innerHTML = '<h1 class="new_comment" style="margin: 10px">'+textarea.getElementsByTagName('textarea')[0].value+'</h1>';
    //newNode.innerHTML += '<p class="new_comment" style="text-align:right;color: #666; padding-right:8px;">--'+nickname+'@编界 '+datetime+'</p>';
    insertAfter(newNode, x);
}

var video_index = 0;
function addVideoSure(){
    var videoUrl = video_textarea.getElementsByTagName('textarea')[0].value;
    if(videoUrl == ''){
        alert('请输入视频链接地址!');
        return false;
    }
    if(!checkUrl(videoUrl)){
        alert('不是正确的视频地址，请注意检查一下!');
        return false;
    }

    $.post("/index/Media/AnalysisVideo",{ "video_url":videoUrl }, function(res){
        if(res.errcode == 0){
            //var x = getInsertNode();
            if(getSelectedNode.length){
                x = getSelectedNode[getSelectedNode.length-1];
            }
            var newNode = document.createElement("div");
            video_textarea.setAttribute('style', 'display:none');
            newNode.setAttribute('class', 'insert_content');
            newNode.innerHTML += '<div class="temp_notice_div" id="container'+video_index+'"><div class="temp_notice'+video_index+'" style="overflow: hidden"> <p class="new_comment" style="text-align:right;color: #666; padding-right:8px;">'+res.videoSource+'</p></div></div>';
            //insertAfter(newNode, x);
            var parentNode = getSelectedNode[getSelectedNode.length-1];
            if(typeof parentNode === 'undefined'){
                $('#bianjie_content_22reljxts').find('p:last-child').append(newNode).append("<p><br/></p>");
            }else{
                $(parentNode).append(newNode).append("<p><br/></p>");
            }
        }
        else{
            alert(res.msg);
        }
    },'json');
    video_index++;
}

function addLinkSure(){
    //var x = getInsertNode();
    if(getSelectedNode.length){
        x = getSelectedNode[getSelectedNode.length-1];
    }

    var newNode = document.createElement("p");

    var linkTxt = link_textarea.getElementsByTagName('textarea')[0].value;
    var linkUrl = link_textarea.getElementsByTagName('textarea')[1].value;

    if(linkTxt == ''){
        alert('请输入链接文字!');
        return false;
    }

    if(linkUrl == ''){
        alert('请输入链接地址!');
        return false;
    }

    if(!checkUrl(linkUrl)){
        alert('不是正确的链接地址，请注意检查一下!');
        return false;
    }

    link_textarea.setAttribute('style', 'display:none');
    //newNode.setAttribute('class', 'insert_content');
    //newNode.setAttribute('style','text-decoration:underline;text-align: center; font-weight: 500;');
    newNode.innerHTML = '<a href="'+linkUrl+'" class="new_comment" style="margin: 10px">'+linkTxt+'</a>';
    var parentNode = getSelectedNode[getSelectedNode.length-1];
    if(typeof parentNode != undefined){
        $(parentNode).append(newNode).append("<p><br/></p>");
    }else{
        $('#bianjie_content_22reljxts p').last().append(newNode).append("<p><br/></p>");
    }
    //insertAfter(newNode, x);
}

function cancelTextEdit(){
    $('.add_textarea').attr('style','display:none');
    textarea.setAttribute('style', 'display:none');
}

function addTextItem(){
    textarea.getElementsByTagName('textarea')[0].value = '';
    textarea.setAttribute('style', 'display:block');
}

function addLinkItem(){
    link_textarea.getElementsByTagName('textarea')[0].value = '';
    link_textarea.getElementsByTagName('textarea')[1].value = '';
    link_textarea.setAttribute('style', 'display:block;height: 150px;');
}

function addVideoItem(){
    video_textarea.getElementsByTagName('textarea')[0].value = '';
    video_textarea.setAttribute('style', 'display:block');
}

function delectSelectItem() {
    $(".selected_item").last().addClass('has_deleted').css('display', 'none');
}

function changeData(data, tag){
    if( tag != 'video' ){
        return ;
    }

    for(var i = 0; i < data.length; i++)
    {
        if( data[i].hasAttribute("data-src") ){
            datasrc = data[i].getAttribute("data-src");
        }else{
            datasrc = data[i].getAttribute("src");
        }

        var height = data[i].getAttribute("height");
        data[i].removeAttribute("width");
        data[i].removeAttribute("height");
        data[i].setAttribute("style", "width: 100%; height:"+height-100+"px;");
        datasrc = datasrc.replace('preview.html', 'player.html');
        datasrc = datasrc.replace('width','ws');
        datasrc = datasrc.replace('height', 'hs');
        data[i].setAttribute("src", datasrc);

        $(data[i]).before('<div class="temp_notice_div" id="container'+i+'"></div>');
        //$(data[i]).after('<p class="temp_notice temp_notice'+i+'">▲编界提示:触摸这里可以选中区域</p>');
        $(data[i]).appendTo('#container'+i);
        //$('.temp_notice'+i).appendTo('#container'+i);
    }
}

var videos = document.getElementsByClassName('video_iframe');

changeData(videos, 'video');

$("#js_content").find("img").each(function () {

    var _this = $(this);
    var src = _this.attr('data-src');
    var domain = "<{$domain}>";

    if( _this.attr('src') == null || _this.attr('src') == undefined )
    {
        if( src )
        {
            $.post("<{:url('article/requestimg')}>",{ "img_url":src }, function(res){
                _this.attr('src', res);
            });
        }
    }

});

function loadDiv(text) {
    var div = "<div id='_layer_'> <div id='_MaskLayer_' style='filter: alpha(opacity=30); -moz-opacity: 0.3; opacity: 0.3;background-color: #000; width: 100%; height: 100%; z-index: 1000; position: absolute;" +                 "left: 0; top: 0; overflow: hidden; display: none'></div><div id='_wait_' style='background:#fff; z-index: 1005; position: absolute; width:200px; margin:0 auto;-moz-animation-duration: s;-o-animation-duration: s;animation-duration: s;height:115px; padding-top: 10px; color: #999; text-align: center; margin: 0 auto; display: none'  ><p style='text-align: center'><img src='/static/test/loading.gif' /><br>" + "" + text + "</p><button onclick='LayerHide()' style='display:none;'>关闭</button></div></div>";
    return div;
}

function LayerShow(text) {
    var addDiv= loadDiv(text);
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

    $("#_wait_").css({ left: (deWidth - $("#_wait_").width()) / 2 + "px", top: (deHeight  - $("#_wait_").height() - $(window).height()/2) + "px" });
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

//保存
function saveOrigin() {
    var isEdit = '<{$Request.param.id}>';
    //移除废弃标签
    removeWastTags();

    $("#bianjie_content_22reljxts").find("*").removeClass("selected_item");

    var title = $('.title_22reljxts').text();
    var author = $('.author_22reljxts').text();
    var pubdate = $('.pubdate_22reljxts').text();
    $('#bianjie_content_22reljxts').find('.rich_media_content').attr('id','').removeClass('rich_media_content');
    var content = $('#bianjie_content_22reljxts').html();
    content = '<div class="rich_media_content" id="js_content">'+content+'</div>';
    var cover = $("#bianjie_content_22reljxts").find('img').eq(0).attr('src');
    $('.save_form_22reljxts input[name="title"]').val(title);
    $('.save_form_22reljxts input[name="author"]').val(author);
    $('.save_form_22reljxts input[name="pubdate"]').val(pubdate);
    $('.save_form_22reljxts input[name="cover"]').val(cover);
    $('.save_form_22reljxts textarea[name="content"]').val(content);

    if( !title )
    {
        alert('请输入文章标题！');
        return false;
    }

    if(!content || content == '<p class="inputForm" placeholder="请输入文章内容"></p>'){
        alert('请输入文章内容！');
        return false;
    }

    LayerShow('正在保存，请稍侯...');
    $.post("<{:url('article/save')}>", $('.save_form_22reljxts').serialize(), function(res){
        alert(res.msg);
        LayerHide();
        if(res.errcode == 200){
            window.location.href = res.data;
        }
    });
}

function removeWastTags()
{
    $("#bianjie_content_22reljxts").find(".has_deleted").each(function () {
        if($(this).css('display') == 'none')
        {
            $(this).remove();
        }
    });

    $("#bianjie_content_22reljxts").find(".temp_notice_div").each(function () {
        if( $(this).css('display') == 'none' )
        {
            $(this).remove();
        }else{
            $(this).children("p").remove();
            $(this).after($(this).find("iframe"));
            $(this).remove();
        }
    });
}

$('mpvoice').each(function () {
    $(this).attr('src', 'https://mp.weixin.qq.com' + $(this).attr('src').replace('https://mp.weixin.qq.com',''));
});

function insertImage(imgSrc){
    var div  = document.createElement("p");
    div.innerHTML =  "<img src='"+imgSrc+"' style='margin-top:15px; margin-bottom:15px;max-width: 100%;' class='insert_img'/>";
    var ranged = getInsertNode();
    if(typeof ranged == 'undefined'){
        $('#bianjie_content_22reljxts').find('p:last-child').append(div).append('<p><br/></p>');
    }else{
        ranged.insertNode(div);
    }
    //selection.modify("move", "right", "word");
}

$("#bianjie_content_22reljxts").find("*").click(function (event) {
    var target = this;
    event.preventDefault();
    var clickedNode = event.target;
    var NodeType = event.target.nodeName;

    if(clickedNode.id != 'bianjie_content_22reljxts')
    {
        var $$myNode = $(clickedNode);
        getSelectedNode.push($$myNode);
        //console.log(getSelectedNode);
    }
});

//滚动条在Y轴上的滚动距离
function getScrollTop(){
    return document.body.scrollTop;
}

//文档的总高度
function getScrollHeight(){
    return document.body.clientHeight;
}

//浏览器视口的高度
function getWindowHeight(){
    var windowHeight = 0;
    if(document.compatMode == "CSS1Compat")
    {
        windowHeight = document.documentElement.clientHeight;
    }
    else
    {
        windowHeight = document.body.clientHeight;
    }
    return windowHeight;
}

//滑动监听
// window.onscroll = function(){
//     //滑到底部时footer定于最下方,假定<footer>的height为30
//     if((getScrollHeight() - getScrollTop() - getWindowHeight()) > 30)
//         $('#change_html').css('position','fixed');
//     else
//         $('#change_html').css('position','relative');
// }