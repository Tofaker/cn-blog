function logout(){
    $.ajax({
        url:"/api/user/logout",
        success:function (r) {
            if (r.success){//注销成功
                window.location.href = "../index.html";//跳转首页
            }else {
                alert("错误码："+ r.code + "\n错误信息：" + r.message);
            }
        },
        error:function (req,textStatus,err) {
            if (err != undefined && err != null){
                //err 有值，属于js语法报错
                alert(err)
            }else {
                alert(req.status)
            }
        }
    });
}

$(function () {
    $.ajax({
        url:"api/article/query",
        success:function (r) {//r = { success :true, data: { user:xxx,articles:xxx} }
            if (r.success){//true
                let data = r.data;//取出data数据
                if (data.user){//已登录
                    $("#index_articles").show();//显示登录的div
                    $("#index_unlogin").hide();//隐藏未登录的div
                }else {//未登录
                    $("#index_articles").hide();
                    $("#index_unlogin").show();
                }
                let content = "";
                for (let a of data.articles){//of 表示里面的元素
                    content += '<li>';
                    content +=      '<a href="javascript:void(0)">';
                    content +=      a.title;
                    content +=      '</a>';
                    content += '<li>';
                }
                $("#index_articles").html(content);
            }else {
                alert("错误码："+r.code+"\n错误消息："+r.message)
            }
        },
        error:function (err,textStatus,thr) {
            alert("状态码："+err.status)

        }
    })
})