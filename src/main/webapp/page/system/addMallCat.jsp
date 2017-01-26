<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>添加积分供应商</title>
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
            <ul class="add-lst">
            	<form action="" method="post" id="faction">
            	<input type="hidden" id="type" name="type" value="${type }"/>
            	<input type="hidden" id="mallCatId" name="mallCatId" value="${mallCatId }"/>
            	<input type="hidden" id="mallCatCode" name="mallCatCode" value=""/>
	                <li>
	                    <label class="lbl-txt">类目名称：</label>
	                    <input type="text" class="input-text ver-error" id="mallCatName" name="mallCatName" value="${IcMallCat.mallCatName} "/>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">类目描述：</label>
	                    <textarea class="textarea" id="mallCatDesc" name="mallCatDesc" value="${IcMallCat.mallCatDesc}"></textarea>
	                    <span class="require">*</span>
	                </li>
	                
	                <input type="hidden" id="mallCatPicUrl" name="mallCatPicUrl" value="${IcMallCat.mallCatPicUrl}"/>
	            </form>
                <li>
                    <label class="lbl-txt">图标上传：</label>
                    <div class="upload-box">
                        <input type="text" class="input-text" id="path"/>
                        <input type="file" class="file-upload" onchange="showPath()" id="fileUpload" name="fileUpload"/>
                        <span class="require">*</span>
                        <button class="browse-btn">浏览</button>
                    </div>    
                    <button class="upload-btn">上传</button>
                </li>
            </ul> 
            <div class="form-aciton">
                <button class="submit-btn">确认提交</button>
                <button class="quit-btn">取消</button>
            </div>
        </div>
    </div>
   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
    <script src="<%=rootPath %>/page/system/js/addMallCat.js"></script>
</body>
</html>