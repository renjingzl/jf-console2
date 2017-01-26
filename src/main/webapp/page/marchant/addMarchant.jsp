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
            <form id="faction" action="" method="post">
            		<!-- Handler中去添加或修改页面的方法给赋的值 -->
	            	<input type="hidden" id="type" name="type" value="${type }">
	            	<input type="hidden" id="providerId" name="providerId" value="${providerId }">
	                <li>
	                    <label class="lbl-txt">积分供应商名称：</label>
	                    <input type="text" class="input-text ver-error" id="providerName" name="providerName" value="${provider.providerName }"/>
	                    <span class="require">*</span>
	                    <p class="error-tip">请输入正确的名称</p>
	                </li>
	                <li>
	                    <label class="lbl-txt">积分供应商描述：</label>
	                    <textarea class="textarea" id="providerDesc" name="providerDesc" value="${provider.providerDesc }" ></textarea>
	                    <span class="require">*</span>
	                    <p class="error-tip">请输入正确的名称</p>
	                </li>
	                <li>
	                    <label class="lbl-txt">别名：</label>
	                    <input type="text" class="input-text ver-right" id="providerShortName" name="providerShortName" value="${provider.providerShortName }" />
	                    <span class="require">*</span>
	                </li>
	                <li class="bot-line"></li>
	                <li>
	                    <label class="lbl-txt">积分兑换比率：</label>
	                    <input type="text" class="input-text" id="providerPointRatio" name="providerPointRatio" value="${provider.providerPointRatio }"/>
	                    <span class="require">*</span>例如100联通积分兑换1积分币，填写100
	                </li>
	                <li>
	                    <label class="lbl-txt">积分兑换费率：</label>
	                    <input type="text" class="input-text" id="providerPointFee" name="providerPointFee" value="${provider.providerPointFee }"/>
	                    <span class="require">*</span>
	                    兑换1积分币需要供应商积分
	                </li>
	                <li class="bot-line"></li>
	                <li>
	                    <label class="lbl-txt">结算周期：</label>
	                    <span class="query-item">
	                        <div class="combo" id="simpleCombo"></div>
	                        <input type="hidden" id="balanceTerm"  name="balanceTerm" value="${provider.balanceTerm}">
	                    </span>
	                    <span class="require">*</span>
	                </li>
	                <li>
	                    <label class="lbl-txt">结算方式：</label>
	                    <span class="query-item">
	                        <div class="combo" id="simpleCombo2"></div>
	                        <input type="hidden" id="balanceType"  name="balanceType" value="${provider.balanceType}">
	                    </span>
	                    <span class="require">*</span>
	                </li>
	                <li class="bot-line"></li>
	                <li>
	                    <label class="lbl-txt">地址：</label>
	                    <span class="query-item mr10">
	                        <div class="combo" id="simpleCombo3"></div>
	                        <input type="hidden" id="provId"  name="provId" value="${provider.provId}">
	                    </span>
	                    <span class="query-item">
	                        <div class="combo" id="simpleCombo4"></div>
	                        <input type="hidden" id="cityId"  name="cityId" value="${provider.cityId}">
	                    </span>
	                    <span class="query-item">
	                        <div class="combo" id="simpleCombo5"></div>
	                        <input type="hidden" id="regionId"  name="regionId" value="${provider.regionId}">
	                    </span>
	                </li>
	                <li>
	                    <label class="lbl-txt">详细地址：</label>
	                    <input type="text" class="input-text-lg" value="详细地址，如成都市成华区荆翠中路120号" 
	                     id="providerAddress" name="providerAddress"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">联系人：</label>
	                    <input type="text" class="input-text" id="contactName" name="contactName" value="${provider.contactName }"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">电话：</label>
	                    <input type="text" class="input-text" value="如80478980或80478980-2380" id="providerPhone" name="providerPhone"/>
	                </li>
	                <li>
	                    <label class="lbl-txt">手机号码：</label>
	                    <input type="text" class="input-text" value="如13688411889" id="contactPhone" name="contactPhone"/>
	                </li>
	                
	                <!-- 将上传的文件名放到隐藏域，提交表单时保存到数据库 -->
	                <input type="hidden" class="input-text"  id="providerPicUrl" name="providerPicUrl" value="${provider.providerPicUrl }"/>
	                <input type="hidden" class="input-text"  id="bproviderLicense" name="bproviderLicense" value="${provider.bproviderLicense }"/>
	                <input type="hidden" class="input-text"  id="bproviderContract" name="bproviderContract" value="${provider.bproviderContract }"/>
                </form>
                <li>
	            	<label class="lbl-txt">积分供应商LOGO：</label>
	                   <div class="upload-box">
	                      <input type="text" class="input-text" id="logoPath"/>
	                      <input type="file" class="file-upload" onchange="showPath()" id="uploadFile1" name="uploadFile1"/>
	                      <span class="require">*</span>
	                      <button class="browse-btn">浏览</button>
	                   </div>    
	                <button class="upload-btn" id="upload-btn1">上传</button>
	            </li>
                <li>
	               <label class="lbl-txt">企业营业执照：</label>
	                  <div class="upload-box">
	                      <input type="text" class="input-text" id="path2"/>
	                      <input type="file" class="file-upload" onchange="showPath2()" id="uploadFile2" name="uploadFile2"/>
	                      <span class="require">*</span>
	                      <button class="browse-btn">浏览</button>
	                  </div>    
	               <button class="upload-btn" id="upload-btn2">上传</button>
	            </li>
	            <li>
	               <label class="lbl-txt">合同扫描件：</label>
	                  <div class="upload-box">
	                     <input type="text" class="input-text" id="path3"/>
	                     <input type="file" class="file-upload" onchange="showPath3()" id="uploadFile3" name="uploadFile3"/>
	                     <span class="require">*</span>
	                     <button class="browse-btn">浏览</button>
	                  </div>    
	                <button class="upload-btn" id="upload-btn3">上传</button>
	            </li>
            </ul>
            <div class="form-aciton">
                <button class="submit-btn">确认提交</button>
                <button class="quit-btn">取消</button>
            </div>
        </div>
    </div>
    
    
    <script src="<%=rootPath %>/page/marchant/js/addMarchant.js"></script>
</body>
</html>