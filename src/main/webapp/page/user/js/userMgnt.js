Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath + '/res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager;
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();	 // 初始化列表
    initCombo();	// 初始化下拉框
    /*自定义table的滚动条
    initDateTime();*/
    
    initBtnFunc();
    
});

//接收条件查询的参数
function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			opName : $("#opName").val(),
			opKind : $("#opKind").val()
		};
		store.load();
	});
}

/*日期组件
function initDateTime() {
	// 开始时间
	$("#timeStartBox").live("click", function() {
		WdatePicker({
					el : "timeStart",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#timeEndBox").live("click", function() {
		WdatePicker({
					el : "timeEnd",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}*/

/*init combo */
function initCombo(){	//下拉框
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 定义模型
        fields: ['codeValue','codeName'],
   	    // 动态加载
   	    proxy: {
   	         type: 'ajax',	// 异步请求
   	         url: rootPath + '/comm/getCodeValue',	// 后台服务地址
   	         reader: {
   	             type: 'json'	// 数据解析的格式json
   	         },
   	         // 设置codeType作为参数
   	         extraParams :{
   	        	 codeType : "1003"
   	         }
   	     },
   	     autoLoad: true
   	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'opKindCombo',
        displayField: 'codeName',	//显示的值
        valueField: 'codeValue',	// 提交的值
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#opKind").val(this.getValue());	//将下拉框选的值放到隐藏域 id=opKind的 value属性中，提交查询条件
        	}
        }
    });
}
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [{name: 'opId',  type: 'auto'},
                 {name: 'opName',  type: 'auto'},
                 {name: 'opCode',  type: 'auto'},
                 {name: 'opType',  type: 'auto'},
                 {name: 'opPic',  type: 'auto'},
                 {name: 'mobileNo',  type: 'auto'},
                 {name: 'emailAdress',  type: 'auto'},
                 {name: 'loginCode',  type: 'auto'},
                 {name: 'loginPasswd',  type: 'auto'},
                 {name: 'lockFlag',  type: 'auto'},
                 {name: 'dataState',  type: 'auto'},
                 {name: 'creator',  type: 'auto'},
                 {name: 'createDate',  type: 'auto'},
                 {name: 'modifier',  type: 'auto'},
                 {name: 'modifyDate',  type: 'auto'},
                 
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            url: rootPath + '/user/getSysOpPageList',
            data: data,
            reader: {
                type: 'json',
                root: 'list',	//handler中ajax查询完数据库返回的UserList
	            totalProperty: 'total'	// handler中ajax查询完数据库返回的总条数
            },
            actionMethods:{		//处理条件查询的中文乱码
            	read : "POST"
            }
        },autoLoad: true	//动态查询生成列表必须写这句话，否则没有数据
    });
    // width确定的宽度
    columns = [
            {
                text     : '操作员名称',
                sortable : false,
                dataIndex: 'opName',
                renderer : qtips
            },
            {
                text     : '编码',
                width    : 110,
                sortable : true,
                dataIndex: 'opCode'
            },
            {
            	text     : '操作员类型',
            	width    : 110,
            	sortable : true,
            	dataIndex: 'opType'
            },
            {
                text     : '手机号',
                width    : 110,
                sortable : true,
                dataIndex: 'mobileNo',
                align    : 'right'
            },
            {
                text     : '电子邮箱地址',
                width    : 120,
                sortable : true,
                dataIndex: 'emailAdress',
                align    : 'center'
            },
            {
                text     : '登录名',
                width    : 110,
                sortable : true,
                dataIndex: 'loginCode'
            },
            {
                text     : '是否锁定',
                width    : 110,
                sortable : true,
                dataIndex: 'lockFlag',
                renderer:function(value){
             	   var lockFlag = value;
             	   var str = "";
             	   if(lockFlag == null || lockFlag == '1'){
             		  str = "未锁定";
             	   }else{
             		  str = "已锁定";
             	   }
             	   return str;
                }
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 75,
                renderer: buttonRender,
                align   : 'center'
            }
        ];
    // 操作区域
    var dockedItems = [/*{
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            layout: {
                pack: 'center'
            }
        }, */{
            xtype: 'toolbar',
            items: [{
                text:'',
                tooltip:'新建',
                minWidth: 30,
                minHeight:30,
                iconCls:'new-ico',
				listeners : {
					click : {
						element : 'el',
						fn : function() {
							window.location.href = rootPath + "/user/addUserPage?type=add";
						}
					}
				}
            }]
        }];
    // 多选
    var selModel = Ext.create('Ext.selection.CheckboxModel', {
        listeners: {
            selectionchange: function(sm, selections) {
            }
        }
    });
    //pager
    pager = Ext.create('Ext.PagingToolbar', {
            store: store,
            displayInfo: true,
            displayMsg : '显示第 {0} 条到 {1} 条记录,一共 {2} 条'
    });
    // create the Grid
    queryGrid = Ext.create('Ext.grid.Panel', {
        store: store,
        stateful: true,
        collapsible: false,
        multiSelect: true,
        stateId: 'stateGrid',
        columns: columns,
        selModel: selModel,
        dockedItems: dockedItems,
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        /*resizable: {
          handles: 's',
          minHeight: 100
        },*/
        bbar: pager,
        viewConfig: {
            stripeRows: true,
            enableTextSelection: true,
            deferRowRender : false,
            forceFit : true,
            emptyText : "<font class='emptyText'>没有符合条件的记录</font>",
            autoScroll:true,
            scrollOffset:-10
        }
    });
    store.load();
   
}
/*
* 操作按钮
*/
function buttonRender(value, meta, record, rowIndex, colIndex, store) {
    var returnValue = "";
    var opId = record.data.opId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyUser(' + opId + ')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteUser('+ opId +')"></em>';
    
    return returnValue;
}

//修改的onclick方法
function modifyUser(opId) {
	window.location.href = rootPath + "/user/addUserPage?type=modify&opId=" + opId;
}
//删除的onclick方法
function deleteUser(opId) {
	//window.location.href = rootPath + "/user/deleteUser?opId=" + opId; 直接删除方式
	
	$.ajax({	//ajax删除方式
		url : rootPath + "/user/deleteUser",
		dataType : 'json',
		type : 'GET',
		data : {
			opId:opId
		},
		success:function(data) {
			alert(data.msg);
			window.location.href = rootPath + '/page/user/userMgnt.jsp';	//跳转到列表页面
		},
		error:function(data) {
			alert("系统错误！");
		}
	});
}



/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

