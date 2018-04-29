---
title: JavaScript_归纳习题
date: 2016-05-13 23:33:43
tags: JavaScript
categories: JavaScript
---

# 目录
1. 制作一个表格，显示班级的学生信息
2. 实现选项卡切换的效果


## 1. 制作一个表格，显示班级的学生信息

```
要求：

1. 鼠标移到不同行上时背景色改为色值为 #f2f2f2，移开鼠标时则恢复为原背景色 #fff

2. 点击添加按钮，能动态在最后添加一行

3. 点击删除按钮，则删除当前行


<!DOCTYPE html>
<html>
     <head>
      <title> new document </title>  
      <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>   
      <script type="text/javascript">    
          window.onload = function(){
              var tr=document.getElementsByTagName("tr");
              for(var i= 0;i<tr.length;i++)
              {
                  bgcChange(tr[i]);
              }
         // 鼠标移动改变背景,可以通过给每行绑定鼠标移上事件和鼠标移除事件来改变所在行背景色。
          }         
         function bgcChange(obj)
         {
            obj.onmouseover=function(){  鼠标移开事件
                obj.style.backgroundColor="#f2f2f2";
            }
            obj.onmouseout=function(){    //鼠标经过事件
                obj.style.backgroundColor="#fff";
            }
         }
       
         // 编写一个函数，供添加按钮调用，动态在表格的最后一行添加子节点；
         var num=2;
         function add(){
            num++;
            var tr=document.createElement("tr");
            var xh=document.createElement("td");
            var xm=document.createElement("td");
            xh.innerHTML="xh00"+num;
            xm.innerHTML="第"+num+"学生";
            var del=document.createElement("td"); //创建删除操作
            del.innerHTML="<a href='javascript:;' onclick='del(this)' >删除</a>";
            var tab=document.getElementById("table");
            tab.appendChild(tr);
            tr.appendChild(xh);
            tr.appendChild(xm);
            tr.appendChild(del);
            var tr = document.getElementsByTagName("tr");
              for(var i= 0;i<tr.length;i++)
              {
                  bgcChange(tr[i]);
              }
         }
                
         // 创建删除函数
         function del(obj)  //传入当前参数
         {
             var tr=obj.parentNode.parentNode;
             tr.parentNode.removeChild(tr);
         }
         
        
      </script> 
     </head> 
     <body> 
           <table border="1" width="50%" id="table">
           <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>操作</th>
           </tr>  
           <tr>
            <td>xh001</td>
            <td>王小明</td>
            <td><a href="javascript:;" onclick="del(this);">删除</a></td>   <!--在删除按钮上添加点击事件  -->
           </tr>
           <tr>
            <td>xh002</td>
            <td>刘小芳</td>
            <td><a href="javascript:;" onclick="del(this);">删除</a></td>   <!--在删除按钮上添加点击事件  -->
           </tr>  
           </table>
           <input type="button" value="添加一行" onclick="add()" />   <!--在添加按钮上添加点击事件  -->
     </body>
</html>
```

---


## 2. 实现选项卡切换的效果

```
实现思路

一、HTML页面布局

提示:
选项卡标题使用ul..li
选项卡内容使用div

二、CSS样式制作

提示:
整个选项卡的样式设置
选项卡标题的样式设置
选项卡内容的样式设置
一开始只显示一个选项卡内容，其它选项卡内容隐藏。

三、JS实现选项卡切换

提示:
获取选项卡标题和选项卡内容
选项卡内容多个，需要循环遍历来操作，得知哪个选项卡和哪个选项内容匹配
通过改变DOM的css类名称,当前点击的选项卡显示，其它隐藏。

<!DOCTYPE html>
<html>
    <head lang="en">
      <meta charset="UTF-8">
      <title>实践题 - 选项卡</title>
      <style type="text/css">
       /* CSS样式制作 */  
       *{margin:0;padding:0;font:normal 12px "微软雅黑";color:#000000;}  /*通用选择器*/
       
       ul{list-style-type: none;}/*标签选择器*/
       a{text-decoration: none;}

       #tab-list{width: 275px;height:190px;margin: 20px auto;}/*ID选择器*/
       #tab-list div{border: 1px solid #7396B8;border-top: none;}  /*后代选择器*/
       #tab-list div li{height: 30px;line-height: 30px;text-indent: 8px;}

       #ul1{border-bottom: 2px solid #8B4513;height: 32px;}
       #ul1 li{display: inline-block;width: 60px;line-height: 30px;text-align: center;border: 1px solid #999;border-bottom: none;margin-left: 5px;}
       #ul1 li:hover{cursor: pointer;} /*鼠标转到链接*/
       #ul1 li.active{border-top:2px solid #8B4513;border-bottom:2px solid #FFFFFF;}
                    /*按下链接的格式*/
       
       
       .show{display: block;}  /*将元素设置为块级元素*/
       .hide{display: none;}/*类选择器,此元素不会被显示。*/ 
       
      </style>
      <script type="text/javascript">
           
          window.onload = function() {
              var oUl1 = document.getElementById("ul1");
              var aLi = oUl1.getElementsByTagName("li");
              var oDiv = document.getElementById("tab-list");
              var aDiv = oDiv.getElementsByTagName("div");
              
              for(var i = 0; i < aLi.length; i++) {  //aLi.length=3
                  aLi[i].index = i;   //指定索引位置
                  aLi[i].onmouseover = function() {   //鼠标移开事件
                      for(var i = 0; i < aLi.length; i++) {
                          aLi[i].className = "";  //清空CSS样式
                      }
                      
                      this.className = "active"; //改变当前调用函数对象的CSS样式
                      for(var j = 0; j < aDiv.length; j++) { //aDiv.length=3
                          aDiv[j].className = "hide"; //隐藏文字
                      }
                      aDiv[this.index].className = "show"; //用于显示文字
                  }        
              }
          }
      </script>
   </head>
  <body>
    <!-- HTML页面布局 -->
    <div id="tab-list">
        <ul id="ul1">
            <li class="active">房产</li><li>家居</li><li>二手房</li>
        </ul>
        <div>
            <ul>
                <li><a href="javascript:;">275万购昌平邻铁三居 总价20万买一居</a></li>
                <li><a href="javascript:;">200万内购五环三居 140万安家东三环</a></li>
                <li><a href="javascript:;">北京首现零首付楼盘 53万购东5环50平</a></li>
                <li><a href="javascript:;">京楼盘直降5000 中信府 公园楼王现房</a></li>
            </ul>
        </div>    
        <div class="hide">
            <ul>
                <li><a href="javascript:;">40平出租屋大改造 美少女的混搭小窝</a></li>
                <li><a href="javascript:;">经典清新简欧爱家 90平老房焕发新生</a></li>
                <li><a href="javascript:;">新中式的酷色温情 66平撞色活泼家居</a></li>
                <li><a href="javascript:;">瓷砖就像选好老婆 卫生间烟道的设计</a></li>
            </ul>
        </div>    
        <div class="hide">
            <ul>
                <li><a href="javascript:;">通州豪华3居260万 二环稀缺2居250w甩</a></li>
                <li><a href="javascript:;">西3环通透2居290万 130万2居限量抢购</a></li>
                <li><a href="javascript:;">黄城根小学学区仅260万 121平70万抛!</a></li>
                <li><a href="javascript:;">独家别墅280万 苏州桥2居优惠价248万</a></li>
            </ul>
        </div>
    </div>
  </body>
</html>

```