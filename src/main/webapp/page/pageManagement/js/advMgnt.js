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
    
    initCombo1();	//广告位状态下拉框
    initCombo2();	//广告位位置下拉框
    initCombo3();	//区域下拉框
    
    initBtnFunc();
});



//接收条件查询的参数,从jsp中获取，给下面的列表查询用
function initBtnFunc(){
	$(".btn-search").click(function(){
		store.proxy.extraParams = {
			advState : $("#advState").val(),
			advPos : $("#advPos").val(),
			advAreaId : $("#advAreaId").val(),
			advName : $("#advName").val()
		};
		store.load();
	});
}

//------------------------------------------下拉框-----------------------------------------------
/*init combo 广告位状态下拉框 */
function initCombo1(){
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        fields: ['codeValue','codeName'],
        proxy: {
  	         type: 'ajax',	
  	         url: rootPath + '/comm/getCodeValue',	
  	         reader: {
  	             type: 'json'	
  	         },
  	         extraParams :{
  	        	 codeType : "2007"
  	         }
  	     },
  	     autoLoad: true
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo1',
        displayField: 'codeName',
        valueField:'codeValue',
        width: 220,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select:function(value){
        		$("#advState").val(this.getValue());
        	}
        }
    });
}
/*init combo 广告位位置下拉框 */
function initCombo2(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['codeValue','codeName'],
		proxy: {
			type: 'ajax',	
			url: rootPath + '/comm/getCodeValue',	
			reader: {
				type: 'json'	
			},
			extraParams :{
				codeType : "2001"
			}
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		renderTo: 'simpleCombo2',
		displayField: 'codeName',
		valueField:'codeValue',
		width: 220,
		labelWidth: 130,
		store: store,
		typeAhead: true,
		listeners:{
			select:function(value){
				$("#advPos").val(this.getValue());
			}
		}
	});
}
/*init combo 区域下拉框 */
function initCombo3(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['areaId','areaName'],
		proxy: {
			type: 'ajax',	
			url: rootPath + '/provider/getArea',	
			reader: {
				type: 'json'	
			},
			extraParams :{
				//codeType : "2007"
			}
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		renderTo: 'simpleCombo3',
		displayField: 'areaName',
		valueField:'areaId',
		width: 220,
		labelWidth: 130,
		store: store,
		typeAhead: true,
		listeners:{
			select:function(value){
				$("#advAreaId").val(this.getValue());
			}
		}
	});
}




//-----------------------------------列表--------------------------------------------------
function initGrid(){
    // create the data store
    store = Ext.create('Ext.data.Store', {
        fields: [
           {name: 'advId',type: 'auto'},
           {name: 'advAreaId',type: 'auto'},
           {name: 'advName',type: 'auto'},
           {name: 'advPos',type: 'auto'},
           {name: 'advPosName',type: 'auto'},
           {name: 'advAreaName',type: 'auto'},
           {name: 'advPic',type: 'auto'},
           {name: 'advDesc',type: 'auto'},
           {name: 'advUrl',type: 'auto'},
           {name: 'advStartTime',type: 'auto'},
           {name: 'advEndTime',type: 'auto'},
           {name: 'advState',type: 'auto'},
           {name: 'advStateName',type: 'auto'},
           {name: 'advOrder',type: 'auto'},
           {name: 'maxAdvOrder',type: 'auto'},
           {name: 'minAdvOrder',type: 'auto'}
        ],  
        remoteSort: true,
        pageSize: 3,
        proxy: {
            type: 'ajax',
            url: rootPath +　'/adv/selectAllAdv',
            data: data,
            reader: {
            	type: 'json',
            	root: 'list',	
    	        totalProperty: 'total'	
            },
            actionMethods:{		//处理条件查询的中文乱码
               read : "POST"
            }
        },autoLoad: true	//动态查询生成列表必须写这句话，否则没有数据
    });
    // width确定的宽度
    columns = [
            {
                text     : '广告位名称',
                width    : 80,
                sortable : false,
                dataIndex: 'advName',
                renderer : qtips
            },
            {
                text     : '广告位区域',
                width    : 80,
                sortable : true,
                dataIndex: 'advPosName'
            },
            {
                text     : '区域',
                width    : 40,
                sortable : true,
                dataIndex: 'advAreaName',
                align    : 'right'
            },
            {
                text     : '广告图片',
                width    : 80,
                sortable : true,
                dataIndex: 'advPic',
                align    : 'right'
            },
            {
                text     : '广告位说明',
                width    : 150,
                sortable : true,
                dataIndex: 'advDesc',
                align    : 'center'
            },
            {
                text     : '广告链接地址',
                width    : 110,
                sortable : true,
                dataIndex: 'advUrl'
            },
            {
                text     : '播放开始时间',
                width    : 110,
                sortable : true,
                dataIndex: 'advStartTime'
            },
            {
                text     : '播放结束时间',
                width    : 85,
                sortable : true,
                dataIndex: 'advEndTime'
            },
            {
            	text     : '广告位状态',
            	width    : 70,
            	sortable : true,
            	dataIndex: 'advStateName'
            },
            {
            	text     : '顺序调整',
            	width    : 85,
            	sortable : true,
            	dataIndex: 'advOrder',
            	renderer:function(value, meta, record, rowIndex, colIndex, store){
            		var advOrder = record.data.advOrder;
            		var advId = record.data.advId;
            		var maxAdvOrder = record.data.maxAdvOrder;
            		var minAdvOrder = record.data.minAdvOrder;
            		var up = "";
            		var down = "";
            		
            		if(advOrder == minAdvOrder){
            			up = "";
            			down = '<a href="'+ rootPath +'/adv/updateAdvOrder?type=down&advOrder='+advOrder+'&advId='+advId+'">向下</a>';
            		}else if(advOrder == maxAdvOrder) {
            			up = '<a href="'+ rootPath +'/adv/updateAdvOrder?type=up&advOrder='+advOrder+'&advId='+advId+'">向上</a>';
	            		down = "";
            		}else {
            			up = '<a href="'+ rootPath +'/adv/updateAdvOrder?type=up&advOrder='+advOrder+'&advId='+advId+'">向上/</a>';
            			down = '<a href="'+ rootPath +'/adv/updateAdvOrder?type=down&advOrder='+advOrder+'&advId='+advId+'">向下</a>';
            		}
            		return up + down;
            	}
            },
            {
                text: '操作',
                menuDisabled: true,
                sortable: false,
                width: 123,
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
							window.location.href = rootPath + "/adv/toAddOrModifyUI?type=add";
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
    var advId = record.data.advId;
    var advState = record.data.advState;
    if(advState == 1) {
    	returnValue += '<em class="modify-ico" title="修改" onclick="modifyAdv('+ advId +')"></em>'+
    	'<em class="del-ico" title="删除" onclick="deleteAdv('+ advId +')"></em>' + 
    	'<a href="#">查看/</a>' + 
    	'<a href="'+ rootPath +'/adv/updateState?advId='+ advId +'">发布</a>';
    }else {
    	returnValue += '<em class="modify-ico" title="修改" onclick="modifyAdv('+ advId +')"></em>'+
    	'<em class="del-ico" title="删除" onclick="deleteAdv('+ advId +')"></em>' + 
    	'<a href="#">查看</a>' + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    }
    return returnValue;
}

//修改
function modifyAdv(advId){
	window.location.href = rootPath + "/adv/toAddOrModifyUI?type=modify&advId=" + advId;
}

//删除
function deleteAdv(advId){
	$.ajax({
		url:rootPath + '/adv/deleteAdv?advId=' + advId,
		dataType:'json',
		type:'get',
		success:function(data){
			alert(data.msg);
			window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
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

