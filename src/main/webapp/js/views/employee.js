$(function () {
    // 抽取由jquery取得的变量
    var empDatagrid, empForm, empDialog, editAndDelBtn, empFormId;
    empDatagrid = $("#emp_datagrid");
    empForm = $("#emp_form");
    empDialog = $("#emp_dialog");
    editAndDelBtn = $("#emp_datagrid_bt_edit,#emp_datagrid_bt_del");
    empFormId = $("#emp_form [name='id']");

    empDatagrid.datagrid({
        fit: true,                                      // 充满整个屏幕
        fitColumns: true,                               // 使列均匀的充满整个屏幕
        rownumbers: true,                               // 使表格带编号
        singleSelect: true,                             // 将表格设置成单选
        pagination: true,                               // 显示分页栏
        toolbar: '#emp_datagrid_bt',                    // 引入表格顶部的按钮
        pageList: [1, 5, 10, 20],                       // 分页的选择列表
        url: '/employee_list',                          // 请求的地址
        onClickRow: function (rowIndex, rowData) {      // 当单击选中的员工为已离职状态时，让离职和编辑按钮变得不可用
            if (!rowData.state) {
                // 如果当前员工是离职状态，则禁用按钮
                editAndDelBtn.linkbutton("disable");
            } else {
                // 选择在职的员工时，让按钮变得可用
                editAndDelBtn.linkbutton("enable");
            }
        },
        onDblClickRow: function () {                    // 双击调用edit方法弹出编辑框
            cmdObj.edit();
        },
        columns: [
            [
                {field: 'username', title: '用户名', align: 'center', width: 1},
                {field: 'realname', title: '姓名', align: 'center', width: 1},
                {field: 'tel', title: '电话', align: 'center', width: 1},
                {field: 'email', title: '邮箱', align: 'center', width: 1},
                {field: 'dept', title: '部门', align: 'center', width: 1, formatter: deptFormatter},
                {field: 'inputTime', title: '入职时间', align: 'center', width: 1},
                {field: 'state', title: '状态', align: 'center', width: 1, formatter: stateFormatter},
                {field: 'admin', title: '是否超级管理员', align: 'center', width: 1, formatter: adminFormatter}
            ]
        ]
    })
    ;

    // 初始化新增员工的对话框
    empDialog.dialog({
        width: 280,
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
            // 编辑还是新增其实都是走的save方法，区分是新增还是编辑就是看隐藏域中的id有没有值
            var idVal = empFormId.val();
            var url;
            if (idVal) {
                // 如果id有值就是编辑
                url = "/employee_update";
            } else {
                url = "/employee_update";
            }
            // 发送异步请求保存员工
            empForm.form("submit", {
                url: url,
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
                        // 第一个参数是标题，第二个参数是提示内容，第三个参数是显示的图标
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
            });
        },

        /**
         * 编辑选中的员工
         */
        edit: function () {
            // 获取选中的数据
            var rowData = empDatagrid.datagrid("getSelected");
            // 有选中的行才做下列操作
            if (rowData) {
                // 点击编辑弹出对话框
                empDialog.dialog("open");
                // 给对话框设置标题
                empDialog.dialog("setTitle", "编辑员工");
                // 每次点击新增清空上一次的输入内容
                empForm.form("clear");
                // 读取选中行的数据进行回显，基于同名匹配规则，因此部门的回显需要做特殊处理
                if (rowData.dept) {
                    // 给rowData对象设置dept.id属性
                    rowData["dept.id"] = rowData.dept.id;
                }
                empForm.form("load", rowData);
            } else {
                // 没有选中记录则弹出提示信息
                $.messager.alert("温馨提示", "请选择需要编辑的员工", "info");
            }
        },
        // 将选中的员工离职
        del: function () {
            // 获取选中的数据
            var rowData = empDatagrid.datagrid("getSelected");
            // 有选中数据才进行离职操作
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定要将该员工离职吗", function (yes) {
                    // 如果点击了是则发送请求进行离职操作
                    if (yes) {
                        $.get("/employee_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    // 离职成功刷新表格，更新员工状态
                                    empDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json");
                    }
                });
            } else {
                // 否则弹出提示框
                $.messager.alert("温馨提示", "请选择需要离职的员工", "info");
            }
        },

        /**
         * 高级搜索
         */
        search: function () {
            var value = $("[name='keyWord']").val();
            // 让datagrid去加载数据，第二个参数表示额外的参数，即查询条件
            empDatagrid.datagrid("load", {
                keyWord: value
            });
        },

        /**
         * 刷新操作
         */
        reload: function () {
            empDatagrid.datagrid("reload");
        },

        /**
         * 取消按钮
         */
        cancel: function () {
            empDialog.dialog("close");
        }
    };

    // 对按钮进行统一的监听
    /*
        监听所有带"data-cmd"属性的a标签，监听到点击事件时执行function方法体中的代码
        $(this)表示将document对象转为jQuery对象，这样就可以使用jQuery的方法
        $(this).data("cmd")其实就是获取a标签中"data-cmd"的值，而"data-cmd"的值是各个方法名
        如果获取到的值不为空，假设 var cmd = "add"; 其实cmdObj[cmd]()就是 cmd["add"]() 表示执行cmdObj中的add方法
    */
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