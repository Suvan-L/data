/*加载登录页面body的时候调用*/
function bodywelcome(){
    
}

/*通用函数，获取ID*/
function $(id){
    obj =document.getElementById(id);
    return obj;
}

/*表单提交按钮-----用户a标签以post方式提交form表单*/
function formSubmit(){ 
    if(checkall()){
         $('userlogin').submit();
    }else{
        alert('登录失败!');
    }
}


var username_valid=false;
/*
    用户验证，防止输入为空
*/
function checkusername(){
    var username=$('username').value;

    if(username=="" || username==null){
        $('checkusername').innerHTML="用户名不能为空";
        return;
    }else{
       $('checkusername').innerHTML="";
        username_valid=true;
    }
}


var userpassword_valid=false;
/*
    密码验证，防止输入为空
*/
function checkuserpassword(){
    var userpassword=$('userpassword').value;

    if(userpassword=="" || userpassword==null){
        $('checkuserpassword').innerHTML="密码不能为空";
        return;
    }
    else{
        $('checkuserpassword').innerHTML="";
        userpassword_valid=true;
    }
}

/*
    用户密码综合检测
*/
function checkall(){
    checkusername();
    checkuserpassword();
    if(userpassword_valid==false | username_valid==false){
        return false;
    }

    if(username_valid && userpassword_valid){
        return true;
    }else{
        username_valid=false;
        userpassword_valid=false;
        return false;
    }
}

