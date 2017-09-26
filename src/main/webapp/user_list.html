<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>服务统计</title>
    <link href="css/themes/default/easyui.css" rel="stylesheet" />
    <link href="css/themes/icon.css" rel="stylesheet" />
    <link href="css/themes/color.css" rel="stylesheet" />
    <script src="script/jquery.min.js"></script>
    <script src="script/jquery.easyui.min.js"></script>
    <script src="script/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            loadData();
            $("#btnSerach").click(function () {
                var pars = {
                    DisplayName: $("#DisplayName").val(),
                    OrganizationUnit: $("#OrganizationUnit").val(),
                };
                loadData(pars);
            });
        });
        function loadData(pars) {
            $('#date').datagrid({
                url: '',
                //width: 1000,
                //height: 400,
                width: ($(window).width() * 0.90),
                height: ($(window).height() * 0.80),
                fitColumns: true, //列自适应
                nowrap: false,
                idField: 'Id',//主键列的列名
                loadMsg: '正在加载信息...',
                pagination: true,//是否有分页
                //singleSelect: false,//是否单行选择
                pageSize: 10,//每页多少条数据
                pageNumber: 1,//默认的页
                pageList: [10, 20, 30],
                queryParams: pars,//往后台传递参数
                columns: [[
                    { field: 'ck', checkbox: true, align: 'left', width: 30 },
                    { field: 'UserName', title: '登录名', align: 'center', width: 100 },
                    { field: 'DisplayName', title: '姓名', align: 'center', width: 100 },
                      { field: 'PhoneNumber', title: '手机', align: 'center', width: 100 },
                      { field: 'OrganizationUnit', title: '部门', align: 'center', width: 100 },
                      {
                          field: 'operation', title: '操作', align: 'center', width: 100,
                          formatter: function (value, row, index) {
                              var str = "";
                              str += '<a href="javascript:view(\'' + Id + '\');" class="easyui-linkbutton c4" tyle="width:50px" >查看</a>';
                              return str;
                          }
                      }
                ]],
                //toolbar: [{
                //    id: 'btnAdd',
                //    text: '添加',
                //    iconCls: 'icon-add',
                //    handler: function () {

                //    }
                //}],
            });
        }
    </script>
    <style>
        body {
            font-size: 12px;
            overflow-y: hidden;
        }

        input {
            width: 100px;
        }

        select {
            width: 104px;
        }

        #sec {
            /*border:1px solid #0094ff;*/
            width: 600px;
        }

            #sec td {
                width: 100px;
                height: 20px;
                text-align: right;
            }
    </style>
</head>
<body>


    <div>
        <table id="sec">
            <tr>
                <td><a href="javascript:void(0)"  class="easyui-linkbutton c8" style="width:100px">同步AD用户</a></td>
                <td>姓名：</td>
                <td><input type="text"  class="easyui-textbox" id="DisplayName" value=" " /></td>
                <td>部门：</td>
                <td>
                    <select id="OrganizationUnit" class="easyui-combobox">
                        <option>请选择</option>
                        <option value="Hatech">Hatech</option>
                    </select>
                </td>
                <td>
                    <a href="javascript:void(0)" id="btnSerach" class="easyui-linkbutton c1" style="width:70px">查询</a>
                </td>
            </tr>
        </table>
    </div>
    <table id="date"></table>
</body>
</html>
