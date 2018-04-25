/*加载注册页面body的时候调用*/
function bodywelcome(){
   
}
function highchoiceshow(){
    var highchoice =document.getElementById("middlehighchoiceone");
    if(highchoice.style.display=="block")
        highchoice.style.display="none";
    else
        highchoice.style.display="block";
}
/*
    通用函数--获取指定id的object
*/
function $(id){
    obj = document.getElementById(id);
    return obj;
}


var username_valid=false;
var userpassword_valid=false;
var useremail_valid=false;
var userphone_valid=false;

/*
    用户名验证
        函数checkusername--检查用户名的有效性
*/
function checkusername(){
    var username =$('username').value;
    /*
        \W 元字符用于查找非单词字符。
        单词字符包括：a-z、A-Z、0-9，以及下划线。
    */
    var pattern =/\W/g;  
    if(username==""|| username==null){
        alert('用户名不能为空，请重新输入');
        $('checkusername').innerHTML="用户名不能为空";
        return;
    }
    else if(username.match(pattern) != null){
        alert('用户名包含特殊字符，请重新输入');
        $('checkusername').innerHTML="用户名不能包含除a-z、A-Z、0-9，以及下划线之外的特殊字符";
        return;
    }
    else if(username.length<=3 || username.length>=20 ){
        alert('用户名长度超出指定范围，请重新输入');
        $('checkusername').innerHTML="用户名长度超出指定范围(大于3 and 小于20)";
        return;
    }else{
        $('checkusername').innerHTML="";
        username_valid=true;
    }
}


/*
    密码，确认密码验证
        函数checkuserpassword--检验密码是否有效
*/
function checkuserpassword(){
    var userpassword=$('userpassword').value;
    var userpasswordtwo=$('userpasswordtwo').value;

    var pattern2 =/^([\w*%$-_]){5,15}$/;

    if(userpassword=="" || userpassword==null){
        alert('密码不能为空，请重新输入');
        $('checkuserpassword').innerHTML="密码不能为空";
        return;
        
    }
    else if(userpassword.length<=5 || userpassword.length>=15){
        alert('密码长度超出指定范围，请重新输入');
        $('checkuserpassword').innerHTML="密码长度超出指定范围(大于5 and 小于15)";
        return;
    }
    else if(!pattern2.test(userpassword)){
        alert('密码存在在无效字符，请重新输入');
        $('checkuserpassword').innerHTML="密码中只能由5-15位字母，数字或者符号%,*,$,-,_组成。";
        return;
    }
    else{
        $('checkuserpassword').innerHTML="";
    }

    if(userpassword != userpasswordtwo){
        alert('确认密码输入密码不符合，请重新输入');
        $('checkuserpasswordtwo').innerHTML="两次输入密码不符合，确认密码请与上面密码相符合";
        return;
    }else{
         userpassword_valid=true;
         $('checkuserpasswordtwo').innerHTML="";
         return;
    }
}

/*
    邮箱验证
        函数checkuseremail ---检验email是否符合规定的格式

        另一种正则表达式
        vrr myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

             1.  /^$/ 这个是个通用的格式。
              ^ 匹配输入字符串的开始位置；$匹配输入字符串的结束位置
             2. 里面输入需要实现的功能。
             * 匹配前面的子表达式零次或多次；
              + 匹配前面的子表达式一次或多次；
                 ？匹配前面的子表达式零次或一次；
             \d  匹配一个数字字符，等价于[0-9]

        pattern2.test(usereamil),检测字符串usereamil是i否符合pattern2所指明的格式
*/
function checkuseremail(){

    var useremail =$('useremail').value;
    var pattern3 = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if(useremail=="" || useremail==null){
        alert('邮箱不能为空，请重新输入');
        $('checkuseremail').innerHTML="邮箱不能为空";
        return;
    }
    else if(!pattern3.test(useremail)){
        alert('邮箱格式错误，请重新输入');
        $('checkuseremail').innerHTML="邮箱格式：xxxxx@xxx.xxx";
        return;
    }
    else{
        useremail_valid=true;
         $('checkuseremail').innerHTML="";
         return;
    }
}

/*
    手机验证
        函数checkuserphone---检测手机号是否有效

        方法1：/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
        方法2：
            /^0?1[3|4|5|8][0-9]\d{8}$/
*/
function checkuserphone(){
    var userphone =$('userphone').value;  
            // 验证130-139,150-159,180-189号码段的手机号码,共11位数
    var pattern4 =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(userphone =="" || userphone==null){
        alert('手机不能为空，请重新输入');
        $('checkuserphone').innerHTML="手机号码不能为空";
        return;
    }
    else if(!pattern4.test(userphone)){
        alert('手机格式错误，请重新输入');
        $('checkuserphone').innerHTML="手机格式错误，正确格式为130-139,150-159,180-189号码段的手机号码,共11位数";
        return;
    }
    else{
        userphone_valid=true;
        $('checkuserphone').innerHTML="";
        return true;
    }
}

/*
    综合检测
        检测是否所有输入项都合法，是则允许提交数据
*/
function checkall(){
    checkusername();
    if(username_valid==false){
        return false;
    }

    checkuserpassword();
    if(userpassword_valid==false){
        return false;
    }

    checkuseremail();
    if(useremail_valid==false){
        return false;
    }

    checkuserphone();
    if(userphone_valid==false){
        return false;
    }

    if(username_valid && userpassword_valid && useremail_valid && userphone_valid){
        return confirm('恭喜你通过了全部验证,是否将信息提交至服务器?');;
    }else{
        username_valid=false;
        userpassword_valid=false;
        useremail_valid=false;
        userphone_valid=false;
    }
}


/*
    当输入框获得焦点的时候,清空内容
    函数inputchangecolor1
*/
function cleanvalue(obj){
    obj.value="";
}

/*
    当鼠标移动到输入框范围内,改变颜色
    函数inputchangecolor2
*/
function inputchangecolor1(obj){
    obj.style.background="#CCDDFF";
}

/*
    当鼠标离开输入框范围,改变颜色
    函数inputchnagecolor3
*/
function inputchangecolor2(obj){
    obj.style.background="#FFF8DC";
}



/*短信验证码点击后倒计时*/
var one=60;
 function noteverify(){
    var x=document.getElementById("noteverify");
    x.innerHTML=one+"s后重新发送";
     one=one-1;
    if(one<=0){
        x.innerHTML="发送完毕";
     }
    setTimeout("noteverify()",1000);
}