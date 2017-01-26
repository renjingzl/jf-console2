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
    <title>供应商查看详情</title>
    
</head>

<body>
    <div class="asideR-cont">
        <div class="add-cnt">
            <ul class="add-lst">
            		<!-- Handler中去添加或修改页面的方法给赋的值 -->
	            	<input type="hidden" id="type" name="type" value="${type }">
	            	<input type="hidden" id="providerId" name="providerId" value="${providerId }">
	                <li>
	                    <label class="lbl-txt">积分供应商名称：</label>
	                    <input type="text" class="input-text ver-error" id="providerName" name="providerName" value="${provider.providerName }"/>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">详细地址：</label>
	                    <input type="text" class="input-text-lg" value="详细地址，如成都市成华区荆翠中路120号" 
	                     id="providerAddress" name="providerAddress"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">客服电话：</label>
	                    <input type="text" class="input-text" id="custsrvPhone" name="custsrvPhone" value="${provider.custsrvPhone }" />
	                    <span class="require">*</span>
	                </li>
	                <li class="bot-line"></li>
	                <li>
	                    <label class="lbl-txt">联系人姓名：</label>
	                    <input type="text" class="input-text" id="contactName" name="contactName" value="${provider.contactName }"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">联系人电话：</label>
	                    <input type="text" class="input-text" value="如80478980或80478980-2380" id="contactPhone" name="contactPhone"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">联系人邮箱：</label>
	                    <input type="text" class="input-text" value="如13688411889" id="contactEmail" name="contactEmail"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">供应商电话：</label>
	                    <input type="text" class="input-text" value="如13688411889" id="providerPhone" name="providerPhone"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">供应商网址：</label>
	                    <input type="text" class="input-text" value="如13688411889" id="providerWeburl" name="providerWeburl"/>
	                </li>
            </ul>
        </div>
    </div>
    
    
    <%-- <script src="<%=rootPath %>/page/marchant/js/addMarchant.js"></script> --%>
</body>
</html>