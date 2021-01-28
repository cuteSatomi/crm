<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>角色管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/role.js"></script>
</head>
<body>
<table id="role_datagrid"></table>
<!-- 新增和更新的对话框 -->
<div id="role_dialog">
    <form id="role_form" method="post">
        <table align="center" style="margin-top: 10px">
            <input type="hidden" name="id"/>
            <tr>
                <td>角色名称: <input type="text" name="name"></td>
                <td>角色编号: <input type="text" name="sn"></td>
            </tr>
            <tr>
                <!-- 所有的权限 -->
                <td><table id="allPermissions"></table></td>
                <!-- 自身的权限 -->
                <td><table id="selfPermissions"></table></td>
            </tr>
        </table>
    </form>
</div>
<!-- 数据表格的顶部按钮 -->
<div id="role_datagrid_bt">
    <!-- 增删改查的按钮 -->
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a id="role_datagrid_bt_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a id="role_datagrid_bt_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">删除</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>

    <!-- 高级查询 -->
    <span style="position: fixed;right: 20px">
        高级搜索：<input name="keyWord"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="search">搜索</a>
    </span>

</div>
<!-- 对话框的底部按钮 -->
<div id="role_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
