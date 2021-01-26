$(function () {
    // 抽取由jquery取得的变量
    var empDatagrid, empForm, empDialog;
    empDatagrid = $("#emp_datagrid");
    empForm = $("#emp_form");
    empDialog = $("#emp_dialog");

    empDatagrid.datagrid({
        fit: true,                               // 充满整个屏幕
        fitColumns: true,                        // 使列均匀的充满整个屏幕
        rownumbers: true,                       // 使表格带编号
        pagination: true,                       // 显示分页栏
        toolbar: '#emp_datagrid_bt',            // 引入表格顶部的按钮
        pageList: [1, 5, 10, 20],               // 分页的选择列表
        url: '/employee_list',                  // 请求的地址
        columns: [
            [
                {field: 'username', title: '用户名', align: 'center', width: 1},
                {field: 'realname', title: '姓名', align: 'center', width: 1},
                {field: 'tel', title: '电话', align: 'center', width: 1},
                {field: 'email', title: '邮箱', align: 'center', width: 1},
                {field: 'dept', title: '部门', align: 'center', width: 1, formatter: deptFormatter},
                {field: 'inputTime', title: '入职时间', align: 'center', width: 1},
                {field: 'state', title: '状态', align: 'center', width: 1, formatter: stateFormatter},
                {field: 'admin', title: '是否超级管理员', align: 'center', width: 1,formatter: adminFormatter}
            ]
        ]
    });

    // 初始化新增员工的对话框
    empDialog.dialog({
        width: 255,
        height: 300,
        buttons: "#emp_dialog_bt",      // 将在employee.jsp定义的对话框底部按钮与该对话框关联
        closed: true                    // 默认将对话框关闭，当点击新增按钮时才打开
    });

    var cmdObj = {
        /**
         * 点击新增按钮时弹出对话框
         */
        add: function () {
            // 点击新增弹出对话框
            empDialog.dialog("open");
            // 给对话框设置标题
            empDialog.dialog("setTitle", "新增员工");
            // 每次点击新增清空上一次的输入内容
            empForm.form("clear");
        },

        /**
         * 保存员工
         */
        save: function () {
            // 发送异步请求保存员工
            empForm.form("submit", {
                url: '/employee_save',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            // 请求成功则关闭对话框
                            empDialog.dialog("close");
                            // 并且刷新datagrid
                            empDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
            });
        }
    };

    // 对按钮进行统一的监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
});

/**
 * 对特定的字段进行格式化
 * @param value     原来的值
 * @param record    对应当前的记录
 * @param index     索引
 */
function deptFormatter(value, record, index) {
    // 如果当前部门不是null，则返回当前部门的名字，否则返回空值
    return value ? value.name : "";
}

function stateFormatter(value, record, index) {
    return value ? "<span style='color: green;'>在职</span>" : "<span style='color: red;'>离职</span>";
}

function adminFormatter(value, record, index) {
    return value ? "是" : "否";
}