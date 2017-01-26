package com.atguigu.jf.console.trigger.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.jf.console.baseapi.trigger.PcExChangeLogMapper;
import com.atguigu.jf.console.trigger.bean.bo.ExchangeLogBean;
import com.atguigu.jf.console.trigger.service.CreateExcelService;

@Service
public class CreateExcelServiceImpl implements CreateExcelService {
	
	@Autowired
	private PcExChangeLogMapper pcExChangeLogMapper;

	
	
	
	@Override
	public String createExcel() throws Exception {
		// 创建excel，参考例1
		String path = "C:\\jfTestExcel\\";
		File f_path = new File(path);
		if(!f_path.exists()){
			f_path.mkdirs();
		}
		
		// 定义文件名(调用下面的方法)
		String fileName = path + this.getFileName("Import_Log_");
		
		File file = new File(fileName);
		
		// 创建工作簿
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		// 创建工作表
		Sheet sheet = wb.createSheet("new sheet");
		
		/**
		 * 我们目前有什么：
		 * 1、有表头："积分导入流水","业务发生时间","积分供应商","导入积分","积分兑换比例","兑换手续费率","结算金额","平台利润","结算状态","结算日期","导出状态","导出时间"
		 * 2、有兑换流水的结果集
		 * 
		 * 开工：
		 * 1、先构造表头
		 * 表头放在第一行
		 * 2、循环处理换流水的结果集
		 * ----> 第一次循环
		 * ---------> 第一种如果不使用循环，挨个属性处理
		 * ---------> 第二种，自己构造成数组，再循环
		 * 
		 * 最终目标：
		 * 每5秒钟创建一个excel，要求文件名不重复
		 */
		// 1、构造表头
		String title[] = {"积分导入流水","业务发生时间","积分供应商","导入积分","积分兑换比例","兑换手续费率","结算金额","平台利润","结算状态","结算日期","导出状态","导出时间"};
		
		// 创建一个行对象，代表着第一行
	    Row row = sheet.createRow((short)0);
	    
	    CellStyle style = wb.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	    // 循环放入表头
	    for (int i = 0; i < title.length; i++) {
	    	Cell cell = row.createCell(i);
	    	cell.setCellValue(createHelper.createRichTextString(title[i]));
	    	cell.setCellStyle(style);
		}

	    // 2、循环创建创建单元格
	    Map<String,Object> map = new HashMap<>();
	    List<ExchangeLogBean> list = pcExChangeLogMapper.selectPcExchangeLog(map);
	    // 第一次循环
	    for (int i = 0; i < list.size(); i++) {
	    	// 从第二行开始创建行对象
	    	row = sheet.createRow(i + 1);
	    	ExchangeLogBean logBean = list.get(i);
	    	String exchange[] = this.createRowArray(logBean);	//调用下面的方法
	    	// 第二次循环处理
	    	for (int j = 0; j < exchange.length; j++) {
	    		// 循环写入单元格
	    		row.createCell(j).setCellValue(createHelper.createRichTextString(exchange[j]));
			}
		}
	    // 生成excel
	    FileOutputStream fileOut = new FileOutputStream(file);
	    wb.write(fileOut);
	    fileOut.close();
	    
		return fileName;
	}

	
	
	//获取文件名的方法
	public String getFileName(String pre){
		String fileName = pre + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".xls";
		return fileName;
	}


	
	
	//将兑换流水实体转换为数组
	public String [] createRowArray(ExchangeLogBean bean){
		String str[] = {bean.getId(),bean.getImpDate(),bean.getProviderName(),bean.getImpPoint(),bean.getRate(),
				bean.getFee(),bean.getAmount(),bean.getProfit(),bean.getExchangeState(),bean.getExchangeDate(),bean.getExportState()
				,bean.getExportDate()};
		return str;
	}
}
