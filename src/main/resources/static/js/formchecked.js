var not_null = "请填写此字段";
var email_no = "邮箱格式错误";
var email_exists = "邮箱已被注册";
var email_ex = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
var bool = false;


//点击注册
$(".analog-left").click(function () {
    var email = $("input[name='email']").val();
    var password = $("input[name='password']").val();
    check_form(email,password);
    if(bool){
        check_email(email,password);
    }
});

//点击登录
$(".analog-right").click(function () {
    var email = $("input[name='email']").val();
    var password = $("input[name='password']").val();
    check_form(email,password);
    if(bool){
        login(email,password);
    }
});


//验证表单
function check_form(email,password) {
    if(email==""){
        $(".error_message_email").html(not_null).css("color","#97b53f");
        $(".lowin-group_email").css("margin-bottom"," 5px");
    }else if(password==""){
        $(".error_message_password").html(not_null).css("color","#97b53f");
        $(".lowin-group_password").css("margin-bottom"," 5px");
    }else {
        bool = true;

    }
}
//验证的ajax
function check_email(email,password) {
    $.ajax({
        url:"http://localhost:8888/user/check",
        type:"GET",
        data:"email="+$("input[name='email']").val(),
        success:function (result) {
            if(result){
                //注册
                regs(email,password);
            }else{
                $(".error_message_email").html(email_exists).css("color","#97b53f");
                $(".lowin-group_email").css("margin-bottom"," 5px");
            }
        }
    });
}

//注册的ajax
function regs(email,password) {
    $.ajax({
        url:"http://localhost:8888/user/registry",
        type:"POST",
        data:"email="+email+"&&"+"password="+password,
        success:function (result) {
            if(result){
                $(".error_message_email").html("");
            }
        }

    });
}
//登录的ajax
function login(email,password) {
    $.ajax({
        url:"http://localhost:8888/user/login",
        type:"POST",
        data:"email="+email+"&&"+"password="+password,
        success:function (result) {

         if(result.code==="10005"){
             $(".error_message_email").html(result.error).css("color","#97b53f");
             $(".lowin-group_email").css("margin-bottom"," 5px");
         }else if(result.code==="10006"){
                $(".error_message_password").html(result.error).css("color","#97b53f");
                $(".error_message_password").css("margin-bottom"," 5px");
            }else{
             //设置token的缓存
             document.cookie = "Nice "+result.token;
             window.location.href = "http://localhost:8888";
         }

        }
    });
}


//邮箱验证
$("input[name='email']").blur(function () {
    var email = $("input[name='email']").val();
    if(email==""){
        $(".error_message_email").html(not_null).css("color","#97b53f");
        $(".lowin-group_email").css("margin-bottom"," 5px");
    }else if(!email_ex.test(email)){
        $(".error_message_email").html(email_no).css("color","#97b53f");
        $(".lowin-group_email").css("margin-bottom"," 5px");
    }else{
        $(".error_message_email").html("");
    }
});

//密码验证
$("input[name='password']").blur(function () {
    var password = $("input[name='password']").val();
    if(password==""){
        $(".error_message_password").html(not_null).css("color","#97b53f");
        $(".lowin-group_password").css("margin-bottom"," 5px");
    }else{
        $(".error_message_password").html("");
    }
});