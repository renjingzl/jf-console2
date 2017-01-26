Ext.onReady(function(){
	initBtnFunc();	//声明表单提交
	initCombo();	//声明下拉框
});

//下拉框
function initCombo(){	
    var store = Ext.create('Ext.data.Store', {
        autoDestroy: true,
        // 定义模型
        fields: ['codeValue','codeName'],
        actionMethods :{
          	 read: "POST"
           },
   	    // 通过静态的数据来加载下拉框
   	    // 动态加载
   	    proxy: {
   	    	 // 异步请求
   	         type: 'ajax',
   	         // 后台服务地址
   	         url: rootPath + '/comm/getCodeValue',
   	         reader: {
   	        	 // 数据解析的格式json
   	             type: 'json'
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
        		$("#opKind").val(this.getValue());
        	},
	        render:function(value) {	//回显下拉框
	        	this.setValue($("#opKind").val());
	        }
        }
    });
}



//添加或修改表单提交方法
function initBtnFunc() {
	$(".submit-btn").click(function(){
		/*//传统表单提交方式
		var type = $("#type").val();
		if(type == "add") {
			$("#faction").attr("action",rootPath + "/user/addUser");
		}else {
			$("#faction").attr("action",rootPath + "/user/modifyUser");
		}
		
		$("#faction").submit();*/
		
		
		//ajax提交表单
		var type = $("#type").val();
		var url = '';
		if(type == "add"){
			url = rootPath + "/user/addUser";
		}else{
			url = rootPath + "/user/modifyUser";
		}
		$.ajax({
			url : url,
			dataType : 'json',
			type : 'post',
			data : $("#faction").serialize(),	//表单序列化提交方式传参数
			success: function(value){
				alert(value.msg);	// 将后台传入前台的提示，打印出来
				window.location.href = rootPath + '/page/user/userMgnt.jsp';	//跳转到列表页面
			},
			error: function(value){
				alert("发生错误！");
			}
		});
	});
	
	
	
	//ajax方式文件上传
	$(".upload-btn").click(function(){
		$.ajaxFileUpload({		//使用ajax上传文件必须用$.ajaxFileUpload({});
			url : rootPath + '/user/fileUpload',
			type : 'post',
			dataType : 'json',
			fileElementId : 'uploadFile',
			data : {
				
			},
			success:function(data) {
				if(data.result == "success"){
					$("#opPic").val(data.msg);	//将文件名放到隐藏域value="opPic"中，提交表单时保存到数据库
					alert(data.msg + "上传文件成功！");
				}
			},
			error:function(data) {
				alert("发生错误！");
			}
		});
	});
	
	
	
	/*//阿里云对象存储方式文件上传
	$(".upload-btn").click(function(){
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
		// ajax方式上传文件
		$.ajaxFileUpload({
			url : rootPath + '/user/uploadFileByOss',
			type : 'post',
			dataType : 'json',
			fileElementId : 'uploadFile',	// 文本域的id
			data :{
				
			},
			success:function(data){
				if(data.result == "success"){
					$("#opPic").val(data.msg);
				}
				alert(data.msg);
			},
			error:function(data){
				
			}
		});
	});*/
}


function showPath(){	//文件路径的显示
	var filePath = $("#uploadFile").val();
	$("#path").val(filePath);
}