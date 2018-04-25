/*
    函数bodybegin ----用户进入页面的时候被触发
*/

var editor=null;
function bodybegin(){
    /*1.
        1s=1000ms
        每隔1000毫秒调用函数time,不停的调用
    */
    setInterval(time,1000); 
    /*2.
        X秒后进行调用函数,至调用一次
    　  setTimeout(代码，延迟时间);
    */

    //计时器，计算今天还有多少秒
    setInterval(residuetimes,1000);
    
    
    //4.限制首页模块展示
    limitindex();
    
   editor =CKEDITOR.replace("showCkeditor"); 
}

/*通用函数，根据id获取obj*/
function $(id){
    obj = document.getElementById(id);
    return obj;
}


/*
    计时器
*/
function time(){
    var nowtime =new Date();
    var timeformat;

    var Y =nowtime.getFullYear();
    //返回值是 0（一月） 到 11（十二月） 之间的一个整数。
    var M =nowtime.getMonth()+1;
    var D =nowtime.getDate();

    var weekday=new Array(7);
      weekday[0]="星期日";
      weekday[1]="星期一";
      weekday[2]="星期二";
      weekday[3]="星期三";
      weekday[4]="星期四";
      weekday[5]="星期五";
      weekday[6]="星期六";

    var d=weekday[nowtime.getDay()];

    var h = nowtime.getHours();
    var m = nowtime.getMinutes();
    var s = nowtime.getSeconds();

    m=packms(m);
    s=packms(s);

    timeformat=Y+"."+M+"."+D+"   "+d+"  "+h+":"+m+":"+s;

    var timeshow= $('timeshow');
    timeshow.innerHTML=timeformat;
}
/*包装分和秒*/
function packms(i){
    if(i<10){
        i="0"+i;
    }
     return i;
}


/*  
     函数residuetimes ---计算现在一天还有多少秒
*/
function residuetimes(){
    var time =$('residuetime');
    var today=new Date();
    var hms =today.getHours()*3600+today.getMinutes()*60+today.getSeconds();


    var residuetime =86400-hms;

    time.innerHTML="today(秒)还有:"+residuetime+"s";

}


/*页面底部的计时器*/
var h=0,m=0,s=0,ms=1;
var i;
var time;
function startCountTime(){
    if(ms%100==0){
         s+=1;
         ms=1;
     }
     if(s>0 &&(s%60)==0){
         m+=1;
         s=0;
     }
    if(m>0 && (m%60)==0){
      h+=1;
      m=0;
     }
     time =h+":"+m+":"+s+":"+ms;
    document.getElementById('counttime').value=time;
    ms+=1;
    i=setTimeout("startCountTime()",1);
}

/*停止计时器*/
function stopCountTime(){
        clearTimeout(i);
}
/*清零计时器*/
function cleanCountTime(){
    clearTimeout(i);
    h=0,m=0,s=0,ms=0;
    time =h+":"+m+":"+s+":"+ms;
    document.getElementById('counttime').value=time;
}

/*
    用于首页切换模块
 */
function changeModule(num){
     var middleshowfirst =$('middleshowfirst');
     var middleshowsecond =$('middleshowsecond');
     var middleshowthird =$('middleshowthird');
     var middleshowfourth =$('middleshowfourth');
     var middleshowfifth=$('middleshowfifth');
     
    if(num==1){ //首页模块
       middleshowfirst.style.display="block";
       middleshowsecond.style.display="none";
       middleshowthird.style.display="none";
       middleshowfourth.style.display="none";
       middleshowfifth.style.display="none";
    }
    else if(num==2){ //所有帖子展示
        middleshowfirst.style.display="none";
        middleshowsecond.style.display="block";
       middleshowthird.style.display="none";
       middleshowfourth.style.display="none";
       middleshowfifth.style.display="none";
    }
    else if(num==3){//待定
        middleshowfirst.style.display="none";
        middleshowsecond.style.display="none";
        middleshowthird.style.display="block";
       middleshowfourth.style.display="none";
       middleshowfifth.style.display="none";
    }
    else if(num==4){//待定
        middleshowfirst.style.display="none";
        middleshowsecond.style.display="none";
        middleshowthird.style.display="none";
        middleshowfourth.style.display="block";
        middleshowfifth.style.display="none";
    }
    else if(num==5){
        middleshowfirst.style.display="none";
        middleshowsecond.style.display="none";
        middleshowthird.style.display="none";
        middleshowfourth.style.display="none";
        middleshowfifth.style.display="block";
    }
 }
 
 function limitindex(){
     $('middleshowfirst').style.display="none";
     $('middleshowthird').style.display="none";
     $('middleshowfourth').style.display="none";
     $('middleshowfifth').style.display="none";
 }
 
 //清空输入框内容
 function cleaninput(obj){
     obj.value="";
 }
 
 function $(id){
     obj=document.getElementById(id);
     return obj;
 }
 
 
 var valid_pid=false;
 //JS验证回贴id
 function checkpid(){
      var pid=$('pid').value;
      
      var pattern =/\D/g; //正则表达式，\D 元字符用于查找非数字字符
      
       if(pid==null || pid==""){
         alert('id不能为空，请输入贴子id');
         $('checkpid').innerHTML="1-1.贴子id不能为空。";
         return;
     }
     else if(pid.match(pattern)){
          alert('贴子id包含特殊字符，请重新输入');
          $('checkpid').innerHTML="1-2.贴子id不能包含数字以外的字符";
          return;
     }else{        
         $('checkpid').innerHTML="";
         valid_pid=true;
     }
 }
 var valid_ptitle=false; 
 //JS验证回贴标题_2
 function checkptitle(){
     var ptitle=$('ptitle').value;
     if(ptitle==null || ptitle==""){
         alert('标题不能为空，请输入标题');
         $('checkptitle').innerHTML="2-1.标题不能为空。";
         return;
     }else if(ptitle.length<0 || ptitle.length>20){
         alert('标题长度不符合要求');
         $('checkptitle').innerHTML="2-2.标题长度不符合要求(要求大于0 and 不超过20)";
         return;
     }else{
          $('checkptitle').innerHTML="";
         valid_ptitle=true;
     }
 }
 
 valid_checkckeditor=false;
 //JS验证回贴内容_3
 function checkckeditor(){
    
     var showCkeditor=editor.document.getBody().getText(); //取文本形式的值
     if(showCkeditor==null || showCkeditor==""){
         alert('回帖内容不能为空,请重新输入');
         $('checkshowCkeditor').innerHTML="3-1.回帖内容不能为空!";
         return;
     }else if(showCkeditor.length<5 || showCkeditor.length>200){
         alert('回帖内容长度不符合要求，请重新输入');
         $('checkshowCkeditor').innerHTML="3-2.回复内容长度不符合(要求：大于5 and 不超过200)";
         return;
     }else{
         $('checkshowCkeditor').innerHTML="";
          valid_checkckeditor=true;
     }
     
 }
 
 //综合验证1.2
 function checkPtitleCkeditor(){
     checkpid()
     checkptitle();
     checkckeditor();
     if(valid_ptitle==false || valid_checkckeditor==false || valid_pid==false){   
         return false;
     }
     if(valid_ptitle && valid_checkckeditor && valid_pid){
         return confirm('回复内容符合要求,是否将信息提交至服务器?');;
     }else{
         valid_ptitle=false;
         valid_checkckeditor=false;
         valid_pid=false;
         return false;
     }
 }
 
 