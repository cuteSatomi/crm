$(function () {
    $("#emp_datagrid").datagrid({
        fit: true,                      // 充满整个屏幕
        fitColumns: true,               // 使列均匀的充满整个屏幕
        rownumbers: true,               // 使表格带编号
        pagination: true,               // 显示分页栏
        toolbar: '#emp_datagrid_tb',    // 引入表格顶部的按钮
        url: '/employee_list',          // 请求的地址
        columns: [
            [
                {field: 'username', title: '用户名', align: 'center', width: 1},
                {field: 'realname', title: '姓名', align: 'center', width: 1},
                {field: 'tel', title: '电话', align: 'center', width: 1},
                {field: 'email', title: '邮箱', align: 'center', width: 1},
                {field: 'dept', title: '部门', align: 'center', width: 1},
                {field: 'inputTime', title: '入职时间', align: 'center', width: 1},
                {field: 'state', title: '状态', align: 'center', width: 1},
                {field: 'admin', title: '是否超级管理员', align: 'center', width: 1},
            ]
        ]
    });
});