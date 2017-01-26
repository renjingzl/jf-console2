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

var data, store, columns, queryGrid,pager,roleId;	//定义所有用到的全局变量
Ext.onReady(function(){
    Ext.QuickTips.init();
    Ext.EventManager.onWindowResize(function(){ 
        queryGrid.getView().refresh() ;
    });
    initGrid();		//列表的函数
    
    initBtnFunc();	//接收参数的函数
    
    getJson();	//构造树的函数
    
    updateTree();	//更新树的函数
    
});


//接收条件查询的参数
function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			roleName : $("#roleName").val(),
		};
		store.load();
	});
}



function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [	//仓库
           {name: 'roleName'},
           {name: 'roleId'}
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            url: rootPath + '/role/getRoleList',
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
                text     : '角色名称',
                sortable : false,
                dataIndex: 'roleName',
                renderer : qtips
            }
        ];
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
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
        listeners:{
        	select : function(model,record,index,eOpts){
        		 roleId = record.data.roleId;	//点击列表获取对应的角色名称的roleId
        		$.ajax({
        			type:'get',
        			dataType:'json',
        			url:rootPath + '/role/getSysFuncByRoleName',
        			data:{roleId:roleId},
        			success:function(data){
        				getTreeJson(data);
        			},
        			error:function(data){
        				
        			}
        		});
        	}
        },
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
            scrollOffset:-10,
        }
    });
    store.load();
   
}
/*
* 操作按钮
*/
/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}


//查询所有功能，生成树
function getJson() {
	$.ajax({
		type:'post',
		dataType:'json',
		url:rootPath + '/role/getAllSysFunc',
		success:function(data){
			getTreeJson(data);
		},
		error:function(data){
			
		}
	});
}


//根据ajxa返回的json字符串构造树
function getTreeJson(data){
	var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "p", "N": "s" }
			},
			data: {
				simpleData: {
					enable: true,
					idKey:'funcId',
					pIdKey:'supFuncId',
					rootPId: 0	//把父节点supFuncId=null的值改为supFuncId=0
				}
			}
	};
	
	var zNodes = data;	//data是后台生成好的json数据
	
	$(document).ready(function(){
		if(treeObj != null){
			treeObj.destroy();
		}
		//初始化树，按配置进行渲染
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//获取ztree对象
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//获取到所有选择状态的节点
		var nodes = treeObj.getCheckedNodes(true);
		//遍历所有节点，并展开，注意：只作用于父节点，因为子节点无法展开
		for (var i = 0; i < nodes.length; i++) {
			treeObj.expandNode(nodes[i], true, true, true);
		}
	});
}



//更新树
function updateTree(){
	$(".submit-btn").click(function(){
		//获取ztree对象
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//获取到所有选择状态的节点
		var nodes = treeObj.getCheckedNodes(true);
		var funcIdStr = "";
		for(var i = 0; i < nodes.length; i++) {
			var sysFuncId = nodes[i].funcId;	//拿到所有被选中的funcId
			funcIdStr = funcIdStr + "," + sysFuncId;	//将被选中的funcId用,拼接传给后台
		}
		
		$.ajax({
			url:rootPath + '/role/updatSysFunc?sysFuncIdStr=' + funcIdStr + '&roleId=' + roleId,
			type:'get',
			dataType:'json',
			success:function(data){
				alert("保存成功")
			},
			error:function(data){
				alert("保存时发生错误")
			}
		});
	});
}
