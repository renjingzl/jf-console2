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
    onfocus();
    initDateTime();
    initCombo();	//区域下拉框
    initBtnFunc();	//添加或修改的函数
});
function initDateTime() {
	// 开始时间
	$("#startDateBox").live("click", function() {
		WdatePicker({
					el : "startDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
	// 结束时间
	$("#endDateBox").live("click", function() {
		WdatePicker({
					el : "endDate",
					dateFmt : "yyyy-MM-dd HH:mm:ss"
				});
	});
}
/*init combo 区域下拉框*/
function initCombo(){
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        fields: ['areaId','areaName'],
        proxy: {
 	         type: 'ajax',	
 	         url: rootPath + '/adv/selectAllArea',	
 	         reader: {
 	             type: 'json'	
 	         },
 	         extraParams :{
 	        	 //查全国的地区信息，这里不需要条件
 	         }
 	     },
 	     autoLoad: true
    });
    var simpleCombo = Ext.create('Ext.form.field.ComboBox', {
        renderTo: 'simpleCombo',
        displayField: 'areaName',
        valueField:'areaId',
        width: 280,
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

//---------------------------------添加------------------------------------------
function initBtnFunc(){
	//发布按钮(保存到数据库，广告位状态为：2-已上架)
	$("#fabu").click(function(){
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/adv/fabuAdv";
		}else {
			url = rootPath + "/adv/modifyFabuAdv";
		}
		$.ajax({
			type:'post',
			dataType:'json',
			data : $("#faction").serialize(),	//表单序列化提交方式传参数
			url:url,
			success:function(data){
				alert(data.msg);
				window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
			},
			error:function(data){
				alert(data.msg);
			}
		});
	});
	
	
	
	//保存按钮(保存到数据库，广告位状态为：1-待发布)
	$("#save").click(function(){
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/adv/saveAdv";
		}else {
			url = rootPath + "/adv/modifySaveAdv";
		}
		$.ajax({
			type:'post',
			dataType:'json',
			data : $("#faction").serialize(),	//表单序列化提交方式传参数
			url:url,
			success:function(data){
				alert(data.msg);
				window.location.href = rootPath + '/page/pageManagement/advMgnt.jsp';
			},
			error:function(data){
				alert(data.msg);
			}
		});
	});
}


$(".upload-btn").click(function(){
	$.ajaxFileUpload({
		url:rootPath + '/adv/fileUpload',
		dataType:'json',
		type:'post',
		fileElementId : 'fileUpload',
		success:function(data){
			alert("上传文件成功！");
			$("#advPic").val(data.msg);
		},
		error:function(data){
			alert(data.msg);
		}
	});
});

function showPath(){
	var path = $("#fileUpload").val();
	$("#path").val(path);
}


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