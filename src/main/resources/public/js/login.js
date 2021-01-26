$(function () {
    //绑定表单提交事件
    $("#login_form").submit(function () {
        //校验表单数据是否符合格式
        //发起ajax请求
        $.ajax({
            url: '../api/user/login',
            type: 'post',//请求方法
            data: $("#login_form").serialize(),//表单数据序列化为表单 k1=v1 & k2=v2
            //前后端接口的数据：成功{success：true} 失败{success：false，code：xxx，message：xxx}
            success:function (r) {//r响应体的数据
                //成功跳转到首页，
                if (r.success){
                    window.location.href = "../index.html";//跳转到xxx页面 js相对路径是以引入的html文件作为参照点
                }
                // 失败显示错误信息
                else {
                    $("#error_message").show();
                    $("#error_message").html(r.message);

                }

            },
            error:function (r, t, e) {
                alert(r.status)
            }

        })
        return false;/*禁用表单默认提交*/
    })
})