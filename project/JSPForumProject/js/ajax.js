/*当页面载入时，先创建xmlhttp异步传输对象*/
try{
    xmlHttp=new ActiveXObject("Msxml2.XMLTTP");
}catch(e){
    try{
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }catch(e2){
        xmlHttp = false;
    }
}
if(!xmlHttp && typeof XMLHttpRequest !='undefined'){
    
    //创建XMLHttpRequest对象，之后将利用该对象向服务器发出异步请求
    xmlHttp = new XMLHttpRequest(); 
}

/*----------各函数的定义------------------*/

//通用函数----通过id获取DOM对象
function $(id){
    obj =document.getElementById(id);
    return obj;
}

/*
    函数chkuser,在注册页面，用户输入用户名，光标离开输入框时调用
    功能：
        向服务器发起请求，请检查用户名是否重复，并等待查询结果当服务器把处理结果传送回来后，
        调用updatePageUser函数继续处理
*/
function chkuser(){
    var username=$('username').value;

    if(username==null || username==""){
        alert('用户名不能为空，请输入用户名');
        //该方法表示将焦点移动到对象上
        $('username').focus();
        return;
    }

    /*
        设置完成后台检测的url,这里准备检测用户名
        是否重复的Servlet发起请求，同时传递请求参数name(即需要检测的用户名),
        该参数需经过编码，因为可能含有中文
    
        传参数username，three是在web.xml配置的路径,(../是返回根目录)
        encodeURI 方法：返回编码为有效的统一资源标识符 (URI) 的字符串。 
    */
    var url="../three?username="+encodeURI(username); 
                       
    /*
        准备向服务器发起请求，设置请求的url，传递数据的方式为GET，第三个参数true表示采用异步请求
    */
    xmlHttp.open("GET",url,true);
    
   //当服务器的状态改变时候,由函数update进行跟踪处理 (指定由函数update跟踪此次请求的执行过程)
   xmlHttp.onreadystatechange = update;
   
   //发出请求
   xmlHttp.send(null);

}

/*
    函数update
    功能：
        根据服务器传回的处理结果，决定在客户端的页面中显示什么信息
*/
function update(){
    
    /*
     * 读取服务器发回的响应状态码(xmlHttp对象的readyState属性),判断是否已经完成本次请求
     *  
    0：请求未初始化（还没有调用 open()）。
    1：请求已经建立，但是还没有发送（还没有调用 send()）。
    2：请求已发送，正在处理中（通常现在可以从响应中获取内容头）。
    3：请求在处理中；通常响应中已有部分数据可用了，但是服务器还没有完成响应的生成。
    4：响应已完成；您可以获取并使用服务器的响应了。

     */
    if(xmlHttp.readyState!=4){
        //本次请求未完成,在信息提示区显示提示信息
        $('checkusername').innerHTML="正在检测...";
    }else{
        //本次请求完成，并传回响应结果

        //读取服务器发回的响应的内容，这里是服务器执行Servlet(ChkUniqueName)后得到的结果(msg---错误信息)
            var response =xmlHttp.responseText;
            if(response!=""){
                //若在ChkUniqueName.java中检测用户名重复，则返回结果信息不为空
                $('checkusername').innerHTML="很抱歉该用户名已被使用，请重新输入";

                //光标定位在用户名输入框，等待用户重新输入
                $('username').focus();

            }else{
                //若内
                $('checkusername').innerHTML="恭喜，该用户名可以使用";
            }
    }
}//end of function update