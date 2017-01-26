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
    <title>供应商管理列表</title>
</head>

<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">供应商品类型</label>
                <div class="combo" id="simpleCombo"></div>
                <input type="hidden" id="providerKind" value="">
            </span>
            <span class="query-item">
                <input class="query-input" placeholder="供应商名称" id="providerName">
            </span>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询"></button>
                 <button type="submit" class="btn-reset" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>
    
    
    <script src="<%=rootPath %>/page/marchant/js/marchantMgnt.js"></script>
</body>
</html>