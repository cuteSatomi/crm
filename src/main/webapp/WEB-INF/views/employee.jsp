<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>
<!-- 新增和更新的对话框 -->
<div id="emp_dialog">
    <form id="emp_form" method="post">
        <table align="center" style="margin-top: 10px">
            <input type="hidden" name="id"/>
            <tr>
                <td>账号</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="realname"></td>
            </tr>
            <tr>
                <td>电话</td>
                <td><input type="text" name="tel"></td>
            </tr>
            <tr>
                <td>邮箱</td>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>部门</td>
                <td><input type="text" name="dept.id" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/department_queryForEmp'"></td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td><input type="text" name="inputTime" class="easyui-datebox"></td>
            </tr>
            <tr>
                <td>角色</td>
                <td><input id="emp_roles" type="text" class="easyui-combobox"
                           data-options="valueField:'id',textField:'name',url:'/role_queryForEmp',multiple:true"></td>
            </tr>
        </table>
    </form>
</div>
<!-- 数据表格的顶部按钮 -->
<div id="emp_datagrid_bt">
    <!-- 增删改查的按钮 -->
    <a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
    <a id="emp_datagrid_bt_edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    <a id="emp_datagrid_bt_del" class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">离职</a>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>

    <!-- 高级查询 -->
    <span style="position: fixed;right: 20px">
        高级搜索：<input name="keyWord"><a class="easyui-linkbutton" iconCls="icon-search" data-cmd="search">搜索</a>
    </span>

</div>
<!-- 对话框的底部按钮 -->
<div id="emp_dialog_bt">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
