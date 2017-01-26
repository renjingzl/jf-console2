Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', rootPath +　'/res/extjs/ux/');
Ext.require([
    'Ext.data.*',
    'Ext.grid.*',
    'Ext.util.*',
    'Ext.form.field.ComboBox',
    'Ext.form.FieldSet',
    'Ext.tip.QuickTipManager',
    'Ext.ux.data.PagingMemoryProxy'
    
]);

var data, store, columns, queryGrid,pager,thisAreaId;
Ext.onReady(function(){
    //initGrid();
    onfocus();
    initCombo1();
    initCombo2();
    initCombo3();
    initCombo4();
    initCombo5();
    initBtnFunc();
});


//保存按钮单击事件，保存表单
function initBtnFunc(){
	$(".submit-btn").click(function(){
		var type = $("#type").val();
		var url = "";
		
		if(type == "add") {
			url = rootPath + "/provider/addProvider";
		}else {
			url = rootPath + "/provider/modifyProvider";
		}
		
		$.ajax({
			type:'post',
			dataType:'json',
			url:url,
			data : $("#faction").serialize(),	//表单序列化提交方式传参数
			success:function(data){
				alert(data.msg);
				window.location.href = rootPath + "/page/marchant/marchantMgnt.jsp";
			},
			error:function(data){
				alert(data.msg);
			}
		});
	});
}
//---------------------------------------文件上传---------------------------------------------------		
	/*//多文件上传
	$(".upload-btn").click(function(){
		$.ajaxFileUpload({
			url:rootPath + '/provider/fileUpload',
			dataType:'json',
			type:'post',
			fileElementId : ['uploadFile1','uploadFile2','uploadFile3'],
			success:function(data){
				alert("上传文件成功！");
			},
			error:function(data){
				alert(data.msg);
			}
		});
	});*/
//---------------------------------------单个文件上传-------------------------------
//LOGO上传	
$("#upload-btn1").click(function(){		
	$.ajaxFileUpload({
		url:rootPath + '/provider/fileUpload1',
		dataType:'json',
		type:'post',
		fileElementId : 'uploadFile1',
		success:function(data){
			alert("上传文件成功！");
			$("#providerPicUrl").val(data.msg);
		},
		error:function(data){
			alert(data.msg);
		}
	});
});
//营业执照上传
$("#upload-btn2").click(function(){
	$.ajaxFileUpload({
		url:rootPath + '/provider/fileUpload2',
		dataType:'json',
		type:'post',
		fileElementId : 'uploadFile2',
		success:function(data){
			alert("上传文件成功！");
			$("#bproviderLicense").val(data.msg);
		},
		error:function(data){
			alert(data.msg);
		}
	});
});
//合同上传
$("#upload-btn3").click(function(){
	$.ajaxFileUpload({
		url:rootPath + '/provider/fileUpload3',
		dataType:'json',
		type:'post',
		fileElementId : 'uploadFile3',
		success:function(data){
			alert("上传文件成功！");
			$("#bproviderContract").val(data.msg);
		},
		error:function(data){
			alert(data.msg);
		}
	});
});
	
	
function showPath(){
	var logoPath = $("#uploadFile1").val();
	$("#logoPath").val(logoPath);
}
function showPath2(){
	var path2 = $("#uploadFile2").val();
	$("#path2").val(path2);
}
function showPath3(){
	var path3 = $("#uploadFile3").val();
	$("#path3").val(path3);
}




//-------------------------------------结算方式-------------------------------------------------
/*init combo 结算周期下拉框*/
function initCombo1(){
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        fields: ['codeValue','codeName'],
        proxy: {
  	         type: 'ajax',	// 异步请求
  	         url: rootPath + '/comm/getCodeValue',	// 后台服务地址
  	         reader: {
  	             type: 'json'	// 数据解析的格式json
  	         },
  	         // 设置codeType作为参数
  	         extraParams :{
  	        	 codeType : "1005"
  	         }
  	     },
  	     autoLoad: true
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo',
        displayField: 'codeName',
        valueField:'codeValue',
        width: 280,
        labelWidth: 130,
        store: store,
        typeAhead: true,
        listeners:{
        	select : function(value){
        		$("#balanceTerm").val(this.getValue());
        	},
	        render:function(value) {	//回显下拉框
	        	this.setValue($("#balanceTerm").val());
	        }
        }
    });
}


/*init combo 结算方式下拉框*/
function initCombo2(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['codeValue','codeName'],
		proxy: {
			type: 'ajax',	// 异步请求
			url: rootPath + '/comm/getCodeValue',	// 后台服务地址
			reader: {
				type: 'json'	// 数据解析的格式json
			},
			// 设置codeType作为参数
			extraParams :{
				codeType : "1006"
			}
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		renderTo: 'simpleCombo2',
		displayField: 'codeName',
		valueField:'codeValue',
			width: 280,
			labelWidth: 130,
			store: store,
			typeAhead: true,
			listeners:{
	        	select : function(value){
	        		$("#balanceType").val(this.getValue());
	        	},
		        render:function(value) {	//回显下拉框
		        	this.setValue($("#balanceType").val());
		        }
	        }
	});
}



//-----------------------------地区三级联动下拉框----------------------------------------------
/*init combo 省级下拉框*/
function initCombo3(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['areaId','areaName'],
		proxy: {
			type: 'ajax',	// 异步请求
			url: rootPath + '/provider/getArea',	// 后台服务地址
			reader: {
				type: 'json'	// 数据解析的格式json
			},
			// 设置areaLevel作为参数
			extraParams :{
				areaLevel : "2"
			}
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		id:'sheng',
		renderTo: 'simpleCombo3',
		displayField: 'areaName',
		valueField:'areaId',
			width: 280,
			labelWidth: 130,
			store: store,
			typeAhead: true,
			emptyText:'请选择省：',
			listeners:{
	        	select : function(value){
	        		
	        		var child1 = Ext.getCmp('shi');	//获取下面市级的组件并清空
	        		var child2 = Ext.getCmp('qu');	//获取下面省级的组件并清空
	        		child1.clearValue();
	        		child2.clearValue();
	        		
	        		//先清空再获取当前点击的areaId，否则areaId还是上一次的
	        		//(即：第一次进去时没有传areaId，只用了areaLecel=2查出了甘肃省)
	        		thisAreaId = this.getValue();
	        		$("#provId").val(thisAreaId);
	        		
	        		child1.getStore().proxy.extraParams={
	        			supAreaId : thisAreaId
	        		}
	        	},	
	        	beforequery:function(){
					delete Ext.getCmp('shi').lastQuery;
				},
		        render:function(value) {	//回显下拉框
		        	this.setValue($("#provId").val());
		        }
	        }
	});
}
//init combo 市级下拉框
function initCombo4(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['areaId','areaName'],
		proxy: {
			type: 'ajax',	// 异步请求
			url: rootPath + '/provider/getArea',	// 后台服务地址
			reader: {
				type: 'json'	// 数据解析的格式json
			}
			// 设置supAreaId作为参数
			/*extraParams :{
				supAreaId : ''
			}*/
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		id:'shi',
		renderTo: 'simpleCombo4',
		displayField: 'areaName',
		valueField:'areaId',
		width: 280,
		labelWidth: 130,
		store: store,
		typeAhead: true,
		emptyText:'请选择市：',
		listeners:{
			select : function(value){
				
        		var child = Ext.getCmp('qu');
        		child.clearValue();
        		
        		thisAreaId = this.getValue()
        		$("#cityId").val(thisAreaId);
        		
        		child.getStore().proxy.extraParams={
        			supAreaId : thisAreaId
        		}
			},
			beforequery:function(){
				delete Ext.getCmp('qu').lastQuery;
			},
			render:function(value) {	//回显下拉框
				this.setValue($("#cityId").val());
			}
		}
	});
}
/*init combo 区级下拉框*/
function initCombo5(){
	var store = Ext.create('Ext.data.Store', {
		autoDestroy: true,
		fields: ['areaId','areaName','supAreaId'],
		proxy: {
			type: 'ajax',	// 异步请求
			url: rootPath + '/provider/getArea',	// 后台服务地址
			reader: {
				type: 'json'	// 数据解析的格式json
			}
			// 设置supAreaId作为参数
			/*extraParams :{
				supAreaId : ''
			}*/
		},
		autoLoad: true
	});
	var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
		id:'qu',
		renderTo: 'simpleCombo5',
		displayField: 'areaName',
		valueField:'areaId',
		width: 280,
		labelWidth: 130,
		store: store,
		typeAhead: true,
		emptyText:'请选择区：',
		listeners:{
			select : function(value){
				$("#regionId").val(this.getValue());
			},
			render:function(value) {	//回显下拉框
				this.setValue($("#regionId").val());
			}
		}
	});
}


//==========================================================================================

/*
* 提示文字
*/
function qtips(value, cellmeta, record, rowIndex, colIndex, store){
    return '<span  title="'+ value +'">' + value + '</span>';    
}
/* upload */
//图片路径赋值 
function uploadPath(){
	 var path = $("#uploadFile").val();
     $("#path").val(path);
     $("#img").attr('src',path);
     $("#img").next().css('display','none'); 
}


// 图片上传 及数据保存
function uploadImage() {
	$("#uploadImg").click(function() {
		var ext = '.jpg.jpeg.gif.bmp.png.';
		var f = $("#uploadFile").val();
		if (f == "") {// 先判断是否已选择了文件
			alert("请选择文件！");
			return false;
		}
		f = f.substr(f.lastIndexOf('.') + 1).toLowerCase();
		if (ext.indexOf('.' + f + '.') == -1) {
			alert("图片格式不正确！");
			return false;
		}
		
		$.ajaxFileUpload({
			url :  "/upload",
			secureuri : false,
			fileElementId : 'uploadFile',
			dataType : 'json',
			success : function(data) {
				if (data.status == "0"){
					fileName = data.fileName;
					$("#advPic").val(fileName);
					alert("上传图片成功");
				} else {
					alert(data.message);
				}
			},
			error : function(data, status, e) {
				alert("文件上传失败，请联系系统管理员");
			}
		});
	});
}
/*=======================*/
function onfocus(){
    $("input[type='text']").focus(function(){
        $(this).addClass("blur");
    })
     $("input[type='text']").blur(function(){
        $(this).removeClass("blur");
    })
}