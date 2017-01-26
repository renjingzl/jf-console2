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
    initGrid();
    initCombo();
    
    initBtnFunc();
});



//接收条件查询的参数给下面的列表用
function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			providerKind : $("#providerKind").val(),
			providerName : $("#providerName").val()
		};
		store.load();
	});
}


/*init combo */
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
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
   	        	 codeType : "1004"
   	         }
   	     },
   	     autoLoad: true
   	});
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo',
        displayField: 'codeName',
        valueField: 'codeValue',	// 提交的值
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select:function(value){
        		$("#providerKind").val(this.getValue());
        	}
        }
    });
}
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'providerId',type: 'auto'},
           {name: 'providerName',type: 'auto'},
           {name: 'providerKind',type: 'auto'},
           {name: 'providerType',type: 'auto'},
           {name: 'providerPointRatio',type: 'auto'},
           {name: 'providerPointFee',type: 'auto'},
           {name: 'balanceTerm',type: 'auto'},
           {name: 'balanceType',type: 'auto'},
           {name: 'contactName',type: 'auto'},
           {name: 'contactPhone',type: 'auto'},
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            url: rootPath + '/provider/getProviderList',
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
                text     : '供应商名称',
                flex     : 1,
                sortable : false,
                dataIndex: 'providerName',
                renderer:function(value, meta, record, rowIndex, colIndex, store){
                	var url = rootPath + "/provider/toShowMarchant?providerId=" + record.data.providerId;
                	var str = "<a href='"+ url +"'>"+value+"</a>"
                	return str;
                }
            },
            {
                text     : '供应商类型',
                width    : 110,
                sortable : true,
                dataIndex: 'providerType'
            },
            {
                text     : '兑换比率',
                width    : 110,
                sortable : true,
                dataIndex: 'providerPointRatio',
                align    : 'right'
            },
            {
                text     : '兑换费',
                width    : 110,
                sortable : true,
                dataIndex: 'providerPointFee',
                align    : 'right'
            },
            {
                text     : '结算周期',
                width    : 120,
                sortable : true,
                dataIndex: 'balanceTerm',
                align    : 'center'
            },
            {
                text     : '结算方式',
                width    : 110,
                sortable : true,
                dataIndex: 'balanceType'
            },
            {
                text     : '联系人',
                width    : 110,
                sortable : true,
                dataIndex: 'contactName'
            },
            {
                text     : '联系电话',
                width    : 85,
                sortable : true,
                dataIndex: 'contactPhone'
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
							window.location.href = rootPath + "/provider/toAddMarchantUI?type=add";
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
    var providerId = record.data.providerId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyProvider(' + providerId + ')"></em>'+
                    '<em class="del-ico" title="删除" onclick="delectProvider(' + providerId + ')"></em>';
    
    return returnValue;
}

//修改
function modifyProvider(providerId){
	window.location.href = rootPath + "/provider/toAddMarchantUI?type=modify&providerId=" + providerId;
}

//删除
function delectProvider(providerId){
	//window.location.href = rootPath + "/provider/deleteProvider?providerId=" + providerId;
	
	$.ajax({
		url:rootPath + '/provider/deleteProvider?providerId=' + providerId,
		dataType:'json',
		type:'get',
		success:function(data){
			alert(data.msg);
			window.location.href = rootPath + "/page/marchant/marchantMgnt.jsp";
		},
		error:function(data){
			alert(data.msg);
		}
	});
}

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}

