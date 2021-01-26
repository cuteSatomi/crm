<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>员工管理</title>
    <%@include file="common.jsp" %>
    <script type="text/javascript" src="/js/views/employee.js"></script>
</head>
<body>
    <table id="emp_datagrid"></table>
    <!-- 数据表格的顶部按钮 -->
    <div id="emp_datagrid_tb">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true">离职</a>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </div>
</body>
</html>
