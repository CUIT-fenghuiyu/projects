// 获取当前 url 中某个参数的方法
function getURLParam(key){
    var params = location.search;
    if(params.indexOf("?")>=0){
        params = params.substring(1);
        var paramArr = params.split('&');
        for(var i=0;i<paramArr.length;i++){
            var namevalues = paramArr[i].split("=");
            if(namevalues[0]==key){
                return namevalues[1];
            }
        }
    }else{
        return "";
    }
}

//退出登录
function onExit(){
    if(confirm("是否确实退出登录?")){
        // ajax 向后端发送请求进行退出操作
        jQuery.ajax({
            url:"/user/logout",
            type:"POST",
            data:{},
            success:function(result){
                location.href="/login.html";
            }
        });
    }
}