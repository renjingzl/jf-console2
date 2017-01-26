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
});



function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'mallCatId',type: 'auto'},
           {name: 'mallCatCode',type: 'auto'},
           {name: 'mallCatName',type: 'auto'},
           {name: 'mallCatPicUrl',type: 'auto'},
           {name: 'mallCatDesc',type: 'auto'},
        ],  
        remoteSort: true,
        pageSize: 10,
        proxy: {
            type: 'ajax',
            url: rootPath + '/mallCat/getMallCat',
            data: data,
            reader: {
	            type: 'json',
	            root: 'list',	
	            totalProperty: 'total'	
            },
            actionMethods:{		//处理条件查询的中文乱码
            	read : "POST"
            }
        },autoLoad: true
    });
    // width确定的宽度
    columns = [
            {
                text     : '类目编码',
                width    : 110,
                sortable : false,
                dataIndex: 'mallCatCode',
                renderer : qtips
            },
            {
                text     : '类目名称',
                width    : 220,
                sortable : true,
                dataIndex: 'mallCatName'
            },
            {
                text     : '图标',
                width    : 80,
                sortable : true,
                dataIndex: 'mallCatLvlPath',
                align    : 'center',
                renderer:function(value){
                	var url = rootPath + '/page/system/img/*.png';
                	return "<img src='"+ url +"'></img>"
                }
            },
            {
                text     : '类目描述',
                flex     : 1,
                sortable : true,
                dataIndex: 'mallCatDesc',
                align    : 'center'
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
    var dockedItems = [{
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
							window.location.href = rootPath + "/mallCat/toAddOrModifyUI?type=add";
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
        height:"500",
        autoHeight: true,
        autoWidth: true,
        renderTo: 'queryGrid',
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
    var mallCatId = record.data.mallCatId;
    returnValue += '<em class="modify-ico" title="修改" onclick="modifyMallCat('+ mallCatId +')"></em>'+
                    '<em class="del-ico" title="删除" onclick="deleteMallCat('+ mallCatId +')"></em>';
    
    return returnValue;
}

//修改
function modifyMallCat(mallCatId){
	window.location.href = rootPath + "/mallCat/toAddOrModifyUI?type=modify&mallCatId=" + mallCatId;
}

//删除
function deleteMallCat(mallCatId){
	//window.location.href = rootPath + "/mallCat/deleteMallCat?mallCatId=" + mallCatId;
	
	$.ajax({
		url:rootPath + '/mallCat/deleteMallCat?mallCatId=' + mallCatId,
		type:'get',
		dataType:'json',
		success:function(data){
			alert(data.msg);
			window.location.href = rootPath + '/page/system/mallCatMgnt.jsp';
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
