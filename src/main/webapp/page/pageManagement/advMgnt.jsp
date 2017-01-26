<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/commHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keyword" content="">
    <title>广告位列表</title>

</head>
<body>
    <div class="asideR-cont">
        <div class="query-head clearfix">
            <span class="query-item mr20">
                <label class="query-txt">广告位状态</label>
                <div class="combo" id="simpleCombo1"></div>
                <input type="hidden" name="advState" id="advState" value=""/>
            </span>
            <span class="query-item mr20">
                <label class="query-txt">广告位位置</label>
                <div class="combo" id="simpleCombo2"></div>
                <input type="hidden" name="advPos" id="advPos" value=""/>
            </span>
            <span class="query-item mr20">
                <label class="query-txt">区域</label>
                <div class="combo" id="simpleCombo3"></div>
                <input type="hidden" name="advAreaId" id="advAreaId" value=""/>
            </span>
            <span class="query-item">
                <input class="query-input" placeholder="广告位名称" id="advName" name="advName" value=""/>
            </span>
            <span class="query-btns">
                 <button type="submit" class="btn-search" title="查询"></button>
                 <button type="submit" class="btn-reset" title="重置"></button>
             </span>
        </div>
        <div class="grid-com com-line" id="queryGrid">
        </div>
    </div>

    <script src="<%=rootPath %>/page/pageManagement/js/advMgnt.js"></script>
</body>
</html>