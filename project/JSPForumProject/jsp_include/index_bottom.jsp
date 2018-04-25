<%-- 
    Document   : index_bottom
    Created on : 2016-6-13, 12:51:07
    Author     : Liu-shuwei
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="bottomintroduce">
                <p>Suvan&nbsp;&nbsp;email:13202405189@163.com
                &nbsp;&nbsp;neusoft school</p>
            </div>
            <div class="bottomlink">
                <a href="" target="_blank">网站首页</a>
                <a href="" target="_blank">联系方式</a>
                <a href="" target="_blank">意见反馈</a>
                <a href="" target="_blank">帮助</a>
            </div>
            <div class="bottomsign">
                <img src="../image/my.jpeg" width="50" height="50" align="right">
            </div>
            <div class="bottomicon">
                <i class="icon iconfont">&#xe60e;</i><!--爱心-->
                <i class="icon iconfont">&#xe609;</i><!--WIFI -->
                <i> 
                    <!-- 计时器-->
                    <input type="text"  id="counttime" value="计时器..." onfocus='this.value=""' />
                    <input type="button" value="Start" onClick="startCountTime()" />
                    <input type="button" value="Stop" onClick="stopCountTime()" />
                    <input type="button" value="Clean" onClick="cleanCountTime()" />
                </i>
            </div>