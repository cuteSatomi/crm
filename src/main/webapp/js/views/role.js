$(function () {
    // 抽取由jquery取得的变量
    var roleDatagrid, roleForm, roleDialog, editAndDelBtn, roleFormId, allPermissions, selfPermissions;
    roleDatagrid = $("#role_datagrid");
    roleForm = $("#role_form");
    roleDialog = $("#role_dialog");
    editAndDelBtn = $("#role_datagrid_bt_edit,#role_datagrid_bt_del");
    roleFormId = $("#role_form [name='id']");
    allPermissions = $("#allPermissions");
    selfPermissions = $("#selfPermissions");

    roleDatagrid.datagrid({
        fit: true,                                      // 充满整个屏幕
        fitColumns: true,                               // 使列均匀的充满整个屏幕
        rownumbers: true,                               // 使表格带编号
        singleSelect: true,                             // 将表格设置成单选
        pagination: true,                               // 显示分页栏
        toolbar: '#role_datagrid_bt',                    // 引入表格顶部的按钮
        pageList: [1, 5, 10, 20],                       // 分页的选择列表
        url: '/role_list',                              // 请求的地址
        /*onClickRow: function (rowIndex, rowData) {      // 当单击选中的角色为已离职状态时，让离职和编辑按钮变得不可用
            if (!rowData.state) {
                // 如果当前角色是离职状态，则禁用按钮
                editAndDelBtn.linkbutton("disable");
            } else {
                // 选择在职的角色时，让按钮变得可用
                editAndDelBtn.linkbutton("enable");
            }
        },*/
        onDblClickRow: function () {                    // 双击调用edit方法弹出编辑框
            cmdObj.edit();
        },
        columns: [
            [
                {field: 'sn', title: '角色编号', align: 'center', width: 1},
                {field: 'name', title: '角色名称', align: 'center', width: 1}
            ]
        ]
    });

    // 初始化所有权限的datagrid
    allPermissions.datagrid({
        width: 285,
        height: 300,
        title: "所有权限",
        url: "/permission_list",
        singleSelect: true,
        fitColumns: true,                                       // 使权限名称占满整行
        rownumbers: true,                                       // 带编号
        pagination: true,                                       // 显示分页栏
        onDblClickRow: function (rowIndex, rowData) {           // 双击将选中的权限分配给当前角色
            var rows = selfPermissions.datagrid("getRows");
            var flag = true;
            var index = -1;
            for (var i = 0; i < rows.length; i++) {
                // 如果该角色已经拥有要新增的那个权限，则将flag标记为false
                if (rows[i].id == rowData.id) {
                    flag = false;
                    index = i;
                    break;
                }
            }
            // 循环结束后如果已经存在则flag为false，如果不存在则为true
            if (flag) {
                selfPermissions.datagrid("appendRow", rowData);
            } else {
                selfPermissions.datagrid("selectRow", index);
            }
        },
        columns: [
            [
                {field: "name", title: "权限名称", align: "center", width: 1},
            ]
        ]
    });
    // 设置所有权限的分页栏为简洁的样式
    var pager = allPermissions.datagrid("getPager");
    pager.pagination({
        showPageList: false,
        showRefresh: false,
        displayMsg: ''
    });

    // 初始化自身拥有权限的datagrid
    selfPermissions.datagrid({
        width: 285,
        height: 300,
        title: "已有权限",
        singleSelect: true,
        fitColumns: true,       // 使权限名称占满整行
        rownumbers: true,       // 带编号
        onDblClickRow: function (rowIndex, rowData) {           // 双击将当前角色的权限删除
            selfPermissions.datagrid("deleteRow", rowIndex);
        },
        columns: [
            [
                {field: "name", title: "权限名称", align: "center", width: 1},
            ]
        ]
    });

    // 初始化新增角色的对话框
    roleDialog.dialog({
        width: 600,
        height: 430,
        buttons: "#role_dialog_bt",      // role.jsp定义的对话框底部按钮与该对话框关联
        closed: true                    // 默认将对话框关闭，当点击新增按钮时才打开
    });

    var cmdObj = {
        /**
         * 点击新增按钮时弹出对话框
         */
        add: function () {
            // 点击新增弹出对话框
            roleDialog.dialog("open");
            // 给对话框设置标题
            roleDialog.dialog("setTitle", "新增角色");
            // 每次点击新增清空上一次的输入内容，除了分页栏的信息
            $("[name='id'],[name='name'],[name='sn']").val("");
            // 清空表格数据，加载本地的空数据
            selfPermissions.datagrid("loadData", {total: 0, rows: []});
        },

        /**
         * 保存角色
         */
        save: function () {
            // 编辑还是新增其实都是走的save方法，区分是新增还是编辑就是看隐藏域中的id有没有值
            var idVal = roleFormId.val();
            var url;
            if (idVal) {
                // 如果id有值就是编辑
                url = "/role_update";
            } else {
                url = "/role_save";
            }
            // 发送异步请求保存角色
            roleForm.form("submit", {
                url: url,
                onSubmit: function (param) {    // 将datagrid中选中的权限也加入到表单中一起提交
                    // 先得到所有要添加的记录
                    var rows = selfPermissions.datagrid("getRows");
                    for (var i = 0; i < rows.length; i++) {
                        param["permissions[" + i + "].id"] = rows[i].id;
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            // 请求成功则关闭对话框
                            roleDialog.dialog("close");
                            // 并且刷新datagrid
                            roleDatagrid.datagrid("reload");
                        });
                    } else {
                        // 第一个参数是标题，第二个参数是提示内容，第三个参数是显示的图标
                        $.messager.alert("温馨提示", data.msg, "info");
                    }
                }
            });
        },

        /**
         * 编辑选中的角色
         */
        edit: function () {
            // 获取选中的数据
            var rowData = roleDatagrid.datagrid("getSelected");
            // 有选中的行才做下列操作
            if (rowData) {
                // 点击编辑弹出对话框
                roleDialog.dialog("open");
                // 给对话框设置标题
                roleDialog.dialog("setTitle", "编辑角色");
                // 每次点击新增清空上一次的输入内容，除了分页栏的信息
                $("[name='id'],[name='name'],[name='sn']").val("");
                // 需要查询当前编辑角色的所有权限来进行表单回显
                var options = selfPermissions.datagrid("options");
                options.url = "/permission_queryByRId";
                selfPermissions.datagrid("load", {
                    rid: rowData.id
                });

                roleForm.form("load", rowData);
            } else {
                // 没有选中记录则弹出提示信息
                $.messager.alert("温馨提示", "请选择需要编辑的角色", "info");
            }
        },
        // 将选中的角色删除
        del: function () {
            // 获取选中的数据
            var rowData = roleDatagrid.datagrid("getSelected");
            // 有选中数据才进行离职操作
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定要删除该角色吗", function (yes) {
                    // 如果点击了是则发送请求进行离职操作
                    if (yes) {
                        $.get("/role_delete?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    // 删除成功刷新表格
                                    roleDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "info");
                            }
                        }, "json");
                    }
                });
            } else {
                // 否则弹出提示框
                $.messager.alert("温馨提示", "请选择需要删除的角色", "info");
            }
        },

        /**
         * 高级搜索
         */
        search: function () {
            var value = $("[name='keyWord']").val();
            // 让datagrid去加载数据，第二个参数表示额外的参数，即查询条件
            roleDatagrid.datagrid("load", {
                keyWord: value
            });
        },

        /**
         * 刷新操作
         */
        reload: function () {
            roleDatagrid.datagrid("reload");
        },

        /**
         * 取消按钮
         */
        cancel: function () {
            roleDialog.dialog("close");
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