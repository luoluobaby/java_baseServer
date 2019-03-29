/**
 * 消息警告框
 * @param {提示对话框上显示的内容} message
 * @param {提示对话框上显示的标题} title
 * @param {提示对话框上按钮显示的内容} buttonCapture
 */
var showNoticeBox = function(message,title,buttonCapture) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("popup-backdrop")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("noticePopup");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title,
	buttonCapture == null ? buttonCapture="确定" : buttonCapture;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var noticePopup = document.createElement("div");
	noticePopup.setAttribute("id","noticePopup");
	noticePopup.setAttribute("class","own-popup");
	noticePopup.setAttribute("style","display: none;")
	
	var appendDom = "<div id='popup-in' class='animated pulse'><div class='popup-inner'><div class='popup-title'>"+title+"</div><div class='popup-text'>"+message+"</div></div><div class='popup-buttons' id='notice' onclick=\"noticeHide()\"><span class='popup-button popup-button-bold' ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>"+buttonCapture+"</span></div></div>";
	noticePopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(noticePopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("noticePopup").style.display = "flex";
}

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

/**
 * 消息确认框
 * @param {确认对话框上显示的内容} message
 * @param {确认对话框上显示的标题} title
 */
var confirms = function(message,title) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("confirmPopup")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("popup-backdrop");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var confirmPopup = document.createElement("div");
	confirmPopup.setAttribute("id","confirmPopup");
	confirmPopup.setAttribute("class","own-popup");
	confirmPopup.setAttribute("style","display: none;")
	
	var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div></div><div class="popup-buttons"><span class="popup-button" id="confirmDelete" onclick=isConfirm_confirm(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>是</span><span class="popup-button popup-button-bold" style="border-left: 1px #CCC solid;" id="confirmNoDelete" onclick=isConfirm_confirm(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>否</span></div></div>';
	confirmPopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(confirmPopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("confirmPopup").style.display = "flex";
}

var showTextBox = function(message,title,tip,defaultTxt) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("dialoguePopup")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("popup-backdrop");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title,
	tip == null ? tip="" : tip;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var promptPopup = document.createElement("div");
	promptPopup.setAttribute("id","dialoguePopup");
	promptPopup.setAttribute("class","own-popup");
	promptPopup.setAttribute("style","display: none;")
	if(defaultTxt == ''||defaultTxt==null){
        var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip+'></div></div><div class="popup-buttons"><span class="popup-button" id="insertTitleConfirm" onclick=isConfirm_prompt111(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTitleNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
    }else{
        var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip+' value="'+defaultTxt+'"></div></div><div class="popup-buttons"><span class="popup-button" id="insertTitleConfirm" onclick=isConfirm_prompt111(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTitleNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
    }
	//var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip+'></div></div><div class="popup-buttons"><span class="popup-button" id="insertTitleConfirm" onclick=isConfirm_prompt111(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTitleNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
	promptPopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(promptPopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("dialoguePopup").style.display = "flex";
}

var showVideoBox = function(message,title,tip) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("dialoguePopup")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("popup-backdrop");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title,
	tip == null ? tip="" : tip;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var promptPopup = document.createElement("div");
	promptPopup.setAttribute("id","dialoguePopup");
	promptPopup.setAttribute("class","own-popup");
	promptPopup.setAttribute("style","display: none;")
	
	var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">视频链接不正确</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip+'></div></div><div class="popup-buttons"><span class="popup-button" id="insertVideoConfirm" onclick=isConfirm_prompt111(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertVideoNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
	promptPopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(promptPopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("dialoguePopup").style.display = "flex";
}


/**
 * 提示框
 * @param {输入对话框上显示的内容} message
 * @param {输入对话框上显示的标题} title
 * @param {输入对话框上编辑框显示的提示文字} tip
 */
var showTextareaBox = function(message,title,tip,defaulTxt) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("dialoguePopup")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("popup-backdrop");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title,
	tip == null ? tip="" : tip;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var promptPopup = document.createElement("div");
	promptPopup.setAttribute("id","dialoguePopup");
	promptPopup.setAttribute("class","own-popup");
	promptPopup.setAttribute("style","display: none;")
	if("" !=defaulTxt && defaulTxt != null){
        var appendDom = '<div id="popup-in" class="animated pulse" style="width:98%;"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><textarea name="promptObtain" style="width:100%;border-color: #ededed;" rows="8" autofocus="" placeholder='+tip+'>'+defaulTxt+'</textarea></div></div><div class="popup-buttons"><span class="popup-button" id="insertTextConfirm" onclick=isConfirm_prompt222(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTextNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
    }else{
        var appendDom = '<div id="popup-in" class="animated pulse" style="width:98%;"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><textarea name="promptObtain" style="width:100%;border-color: #ededed;" rows="8" autofocus="" placeholder='+tip+'></textarea></div></div><div class="popup-buttons"><span class="popup-button" id="insertTextConfirm" onclick=isConfirm_prompt222(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTextNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
    }
	//var appendDom = '<div id="popup-in" class="animated pulse" style="width:98%;"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">内容不能为空</div><div class="popup-input"><textarea name="promptObtain" style="width:100%;border-color: #ededed;" rows="8" autofocus="" placeholder='+tip+'></textarea></div></div><div class="popup-buttons"><span class="popup-button" id="insertTextConfirm" onclick=isConfirm_prompt222(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertTextNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
	promptPopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(promptPopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("dialoguePopup").style.display = "flex";
}

var showLinkBox = function(message,title,tip) {
	//判断页面是否存在弹窗层dom,有的话移除重新加载
	var isDom = document.getElementById("dialoguePopup")
	if(isDom) {
		document.body.removeChild(isDom);
	}
	//判断页面是否存在弹窗遮罩层,有的话移除重新加载
	var isDom_back = document.getElementById("popup-backdrop");
	if(isDom_back) {
		document.body.removeChild(isDom_back);
	}
	
	message == null ? message="nulll" : message,
	title == null ? title="" : title,
	tip == null ? tip="" : tip;
	//遮罩层
	var popupBackdrop = document.createElement("div");
	popupBackdrop.setAttribute("class","popup-backdrop fadeIn");
	popupBackdrop.setAttribute("id","popup-backdrop");
	popupBackdrop.setAttribute("style","display: none;")
	//弹窗层
	var promptPopup = document.createElement("div");
	promptPopup.setAttribute("id","dialoguePopup");
	promptPopup.setAttribute("class","own-popup");
	promptPopup.setAttribute("style","display: none;")
	
	var appendDom = '<div id="popup-in" class="animated pulse"><div class="popup-inner"><div class="popup-title">'+title+'</div><div class="popup-text">'+message+'</div><div id="popup-err1" style="display:none;">链接文字不能为空</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip[0]+'></div><div id="popup-err2" style="display:none;">链接地址不正确</div><div class="popup-input"><input type="text" name="promptObtain" autofocus="" placeholder='+tip[1]+'></div></div><div class="popup-buttons"><span class="popup-button" id="insertLinkConfirm" onclick=isConfirm_prompt333(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>确定</span><span class="popup-button popup-button-bold" id="insertLinkNoConfirm" style="border-left: 1px #CCC solid;" onclick=isConfirm_prompt(); ontouchstart=btn_touchstart(this); ontouchend=btn_ontouchend(this);>取消</span></div></div>';
	promptPopup.innerHTML = appendDom;
	
	document.body.appendChild(popupBackdrop);
	document.body.appendChild(promptPopup);
	
	document.getElementById("popup-backdrop").style.display = "block";
	document.getElementById("dialoguePopup").style.display = "flex";
}


/**
 * 关闭 消息警告框
 */
function noticeHide() {
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("noticePopup").style.display = "none";
}

/**
 * 关闭 消息确认框
 */
function isConfirm_confirm(text) {
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("confirmPopup").style.display = "none";
}

function isConfirm_prompt(text) {
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("dialoguePopup").style.display = "none";
}

function isConfirm_prompt111(){
	var promptObtain = document.getElementsByName("promptObtain")[0].value;
	if(""== promptObtain){
		document.getElementById('popup-err1').style.display = 'block';
		return false;
	}
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("dialoguePopup").style.display = "none";
}

function isConfirm_prompt222(){
	var promptObtain = document.getElementsByName("promptObtain")[0].value;
	if(""== promptObtain){
		document.getElementById('popup-err1').style.display = 'block';
		return false;
	}
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("dialoguePopup").style.display = "none";
}

function isConfirm_prompt333(){
	var promptObtain = document.getElementsByName("promptObtain")[0].value;
	var promptObtain1 = document.getElementsByName("promptObtain")[1].value;
	if(""== promptObtain){
		document.getElementById('popup-err1').style.display = 'block';
		return false;
	}
	if(!checkUrl(promptObtain1)){
		 document.getElementById('popup-err1').style.display = 'none';
					document.getElementById('popup-err2').style.display = 'block';
		return false;
	}
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("dialoguePopup").style.display = "none";
}

function isConfirm_prompt444(){
	var promptObtain = document.getElementsByName("promptObtain")[0].value;
	if(""== promptObtain){
		document.getElementById('popup-err1').style.display = 'block';
		return false;
	}
	document.getElementById("popup-backdrop").style.display = "none";
	document.getElementById("dialoguePopup").style.display = "none";
}


function btn_touchstart(thises) {
	thises.style.backgroundColor = "rgba(234,234,235,0.95)"
}

function btn_ontouchend(thises) {
	thises.style.backgroundColor = "rgba(255,255,255,.95)"
}
