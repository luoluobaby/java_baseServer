
//加载外部js文件
function loadScript(url) {
    var script = document.createElement("script");
    script.type = 'text/javascript';
    script.src = url;
    document.body.appendChild(script);
}
loadScript("https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js");

//内部添加js代码
function loadScriptString(code) {
    var script = document.createElement("script");
    script.type = 'text/javascript';
    try {
        script.appendChild(document.createTextNode(code));//标准写法
    } catch (ex) {
        script.text = code; //兼容ie
    }
    document.body.appendChild(script)
}


var testValu = '[';

$("body a").each(function () {
    var a=$(this).attr("href");
    testValu+=('{"href:"'+a+'"}');
})
testValu += "]";
$("#content_content").html(testValu);


// var testValu = "{";
// var ajaxurl='http://open.taobao.com/apitools/ajax_props.do?_tb_token_=5671136811e33&act=childCid&restBool=false&ua=090%23qCQXNXXgXDDXPvi0XXXXXQkOIzaYH95AzwLlO%2BjaAGBPfrPlsxU5G8i3OoueHG7AzPliXiOIQuysrb9FibIhbL%2F8RDXgLZzpq4QXius%2BSbuIXvXzrvksFvVs3XdoFsbiKN8X%2FtIwojLiXXB%2B0ydC24QXrXXPN%2FmoBrdvXIiQBUliXXwW2tOoG4itDBjsXvXq2C98OEvXKy%2BTq4QXius%2BSbu%2FXvXuZW4R4tG0wvQXrg3TY9Cq32Ejg1FAb5QkXvXuLWQ5HfD5H4QXaOXTsEq6cjFo%2F4QXUrdtFgZc%2B4QiIZRSF07cXllbK1Ss%2B9Vrlzdt8gZc%2B4pAIZRSF07cX9wtXvXQ0ZsNLzliXXfMhTQ%2F%2BvQXaBVLorISXudQ%2BkViXikTuprTsDxbDXaXmLwtI%2FTTnjhQVrxlDHQWXvXOXXjVcrduIyXX4X4Oq4QXius%2BSbuIXvXzrDihFvE7WvdoFsbiKN8X%2FtIwojLiXXB%2B0ydCq4QXius%2BSbQ%3D';
//
// $("#cid_0 option").each(function(){
//     testValu+='{"cid":'+$(this).val()+',"name":"'+$(this).text()+'"},\r\n';
//     // testValu+=getAjaxValue(ajaxurl+"&cid="+$(this).val());
// });
// testValu += "}";
// $("#propTextarea").html(testValu);
// function getAjaxValue(url)
// {
//     htmlobj=$.ajax({url:url,async:false});
//     return htmlobj.responseText;
// }

// var testValu = "{";
// var ajaxurl='http://open.taobao.com/apitools/ajax_props.do?_tb_token_=5671136811e33&act=childCid&restBool=false&ua=090%23qCQXNXXgXDDXPvi0XXXXXQkOIzaYH95AzwLlO%2BjaAGBPfrPlsxU5G8i3OoueHG7AzPliXiOIQuysrb9FibIhbL%2F8RDXgLZzpq4QXius%2BSbuIXvXzrvksFvVs3XdoFsbiKN8X%2FtIwojLiXXB%2B0ydC24QXrXXPN%2FmoBrdvXIiQBUliXXwW2tOoG4itDBjsXvXq2C98OEvXKy%2BTq4QXius%2BSbu%2FXvXuZW4R4tG0wvQXrg3TY9Cq32Ejg1FAb5QkXvXuLWQ5HfD5H4QXaOXTsEq6cjFo%2F4QXUrdtFgZc%2B4QiIZRSF07cXllbK1Ss%2B9Vrlzdt8gZc%2B4pAIZRSF07cX9wtXvXQ0ZsNLzliXXfMhTQ%2F%2BvQXaBVLorISXudQ%2BkViXikTuprTsDxbDXaXmLwtI%2FTTnjhQVrxlDHQWXvXOXXjVcrduIyXX4X4Oq4QXius%2BSbuIXvXzrDihFvE7WvdoFsbiKN8X%2FtIwojLiXXB%2B0ydCq4QXius%2BSbQ%3D';
//
// $("#cid_0 option").each(function(){
//     testValu+=('"'+$(this).val()+'":'+getAjaxValue(ajaxurl+"&cid="+$(this).val())+',\r\n');
// });
// testValu += "}"
// $("#propTextarea").html(testValu);
// function getAjaxValue(url)
// {
//     htmlobj=$.ajax({url:url,async:false});
//     return htmlobj.responseText;
// }

// var select = document.getElementById("cid_0");
// var testValu = "";
// var ajaxurl='http://open.taobao.com/apitools/ajax_props.do?_tb_token_=5671136811e33&act=childCid&restBool=false&ua=090%23qCQXNXXgXDDXPvi0XXXXXQkOIzaYH95AzwLlO%2BjaAGBPfrPlsxU5G8i3OoueHG7AzPliXiOIQuysrb9FibIhbL%2F8RDXgLZzpq4QXius%2BSbuIXvXzrvksFvVs3XdoFsbiKN8X%2FtIwojLiXXB%2B0ydC24QXrXXPN%2FmoBrdvXIiQBUliXXwW2tOoG4itDBjsXvXq2C98OEvXKy%2BTq4QXius%2BSbu%2FXvXuZW4R4tG0wvQXrg3TY9Cq32Ejg1FAb5QkXvXuLWQ5HfD5H4QXaOXTsEq6cjFo%2F4QXUrdtFgZc%2B4QiIZRSF07cXllbK1Ss%2B9Vrlzdt8gZc%2B4pAIZRSF07cX9wtXvXQ0ZsNLzliXXfMhTQ%2F%2BvQXaBVLorISXudQ%2BkViXikTuprTsDxbDXaXmLwtI%2FTTnjhQVrxlDHQWXvXOXXjVcrduIyXX4X4Oq4QXius%2BSbuIXvXzrDihFvE7WvdoFsbiKN8X%2FtIwojLiXXB%2B0ydCq4QXius%2BSbQ%3D';
// if (null ===select){
//     alert("select is null");
// }
// else
// {
//     for (i=0;i<select.length;i++)
//     {
//         testValu+=(getAjaxValue(ajaxurl+"&cid="+select[i].value));
//     }
//     document.getElementById("propTextarea").innerText=testValu;
// }
//
// function getAjaxValue(url)
// {
//     htmlobj=$.ajax({url:url,async:false});
//     return htmlobj.responseText;
// }