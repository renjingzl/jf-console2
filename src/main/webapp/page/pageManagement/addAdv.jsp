<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<!-- 日期控件CSS -->
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/skinex/skinex.css" />
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>添加或修改广告位</title>
</head>
<body>
    <div class="asideR-cont">
        <div class="add-cnt">
            <ul class="add-lst">
            	<form action="" method="post" id="faction">
            		<input type="hidden" id="advId" name="advId" value="${advId }"/>
            		<input type="hidden" id="type" name="type" value="${type }"/>
	                <li>
	                    <label class="lbl-txt">广告位位置：</label>
	                    <span class="form-input">
	                    	<c:if test="${advId == null }">
	                        	<label class="mr30"><input type="radio" name="advPos" value="1"/> 首位</label>
	                        	<label><input type="radio" name="advPos" value="2"/> 特价区</label>
	                       	</c:if>
	                    	<c:if test="${advId != null && adv.advPos == 1 }">
	                        	<label class="mr30"><input type="radio" name="advPos" value="1" checked="checked"/> 首位</label>
	                        	<label><input type="radio" name="advPos" value="2"/> 特价区</label>
	                       	</c:if>
	                       	<c:if test="${advId != null && adv.advPos == 2 }">
	                        	<label class="mr30"><input type="radio" name="advPos" value="1" checked="checked"/> 首位</label>
	                        	<label><input type="radio" name="advPos" value="2" checked="checked"/> 特价区</label>
	                    	</c:if>
	                    </span>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">区域：</label>
	                    <span class="query-item">
	                        <div class="combo" id="simpleCombo"></div>
	                        <input type="hidden" id="advAreaId" name="advAreaId" value=""/>
	                    </span>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">广告位名称：</label>
	                    <input type="text" class="input-text" id="advName" name="advName" value="${adv.advName }"/>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">广告位链接地址：</label>
	                    <input type="text" class="input-text" id="advUrl" name="advUrl" value="${adv.advUrl }""/>
	                    <span class="require">*</span>
	                </li>
	                <li>
		                 <label class="lbl-txt">广告位说明：</label>
		                 <textarea class="textarea" id="advDesc" name="advDesc" value="${adv.advDesc }" ></textarea>
		                 <span class="require">*</span>
		            </li>
	                <li>
	                    <label class="lbl-txt">开始播放时间：</label>
	                    <span class="posR">
	                        <input readonly="readonly" id="startDate" name="advStartTime" type="text" class="input-text" value="${adv.advStartTime }">
	                        <i class="cal-ico" id="startDateBox"></i>
	                        <span class="require">*</span>
	                    </span> 
	                    <label class="lbl-txt">结束播放时间：</label>
	                    <span class="posR">
	                        <input readonly="readonly" id="endDate" name="advEndTime" type="text" class="input-text" value="${adv.advEndTime }"/>
	                        <i class="cal-ico" id="endDateBox"></i>
	                        <span class="require">*</span>
	                    </span>
	                </li>
	                <input type="hidden" id="advPic" name="advPic" value=""/>
	            </form>
                <li>
                    <label class="lbl-txt">广告图片：</label>
                    <div class="upload-box">
                        <input type="text" class="input-text" id="path"/>
                        <input type="file" class="file-upload" onchange="showPath()" id="fileUpload" name="fileUpload"/>
                        <span class="require">*</span>
                        <button class="browse-btn">浏览</button>
                    </div>    
                    <button class="upload-btn">上传</button>
                </li>
                <li class="bot-line"></li>
            </ul>
            <div class="form-aciton">
                <button class="submit-btn" id="fabu">发布</button>
                <button class="submit-btn" id="save">保存</button>
                <button class="quit-btn">取消</button>
            </div>
        </div>
    </div>

   	<!-- 日期控件JS  -->
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/res/cui/app/datepicker/js/dateField.js"></script>
   
    <script src="<%=rootPath %>/page/pageManagement/js/addAdv.js"></script>
</body>
</html>