<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>客户关系管理系统</title>
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/default/easyui.css"><!-- 样式文件 -->
    <link rel="stylesheet" type="text/css" href="/js/jquery-easyui/themes/icon.css"> <!--图标样式  -->
    <script type="text/javascript" src="/js/jquery-easyui/jquery.min.js"></script> <!-- jQuery核心库 -->
    <script type="text/javascript" src="/js/jquery-easyui/jquery.easyui.min.js"></script>  <!-- EasyUI核心库 -->
    <script type="text/javascript" src="/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> <!-- 语言包 -->
    <script type="text/javascript" src="/js/views/index.js"></script>
</head>
<body>
<div class="easyui-layout" fit="true" style="width:600px;height:400px;">
    <div data-options="region:'north'" style="height:100px;background:url('/img/banner-pic.gif') no-repeat;background-size:cover; ">
        <h1>小码哥客户关系管理系统</h1>
    </div>
    <div data-options="region:'west'" style="width:150px;">
        <!-- 手风琴+菜单 -->
        <div class="easyui-accordion" fit="true">
            <div title="菜单">
                <!-- 使用树组件来定义菜单 -->
                <ul id="menuTree"></ul>
            </div>
            <div title="帮助"></div>
            <div title="简介"></div>
        </div>
    </div>
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <!-- 正文内容 -->
        <div id="myTabs" class="easyui-tabs" fit="true">
            <div title="欢迎页" closable="true">
                <center><h1>欢迎登陆系统</h1></center>
            </div>
        </div>
    </div>
    <div data-options="region:'south'" style="height:30px;background:url('/img/banner-pic.gif') no-repeat;background-size:cover; ">
        <center>版权信息</center>
    </div>
</div>
</body>
</html>