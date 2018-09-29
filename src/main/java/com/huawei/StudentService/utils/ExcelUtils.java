package com.huawei.StudentService.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelUtils {

	private ExcelUtils() {

	}

	/**
	 * 从Excel文件中读取数据，返回List<Map<String, Object>>，键String为excel模板中第二行的fieldName
	 * 
	 * @param excelFile
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> parseExcelFileToList(File excelFile) throws Exception {
		List<Map<String, Object>> excelData = new ArrayList<Map<String, Object>>();
		if (!excelFile.exists()) {
			return excelData;
		}
		Workbook workbook = Workbook.getWorkbook(excelFile);
		if (workbook.getSheets().length == 0) {
			return excelData;
		}
		Sheet sheet = workbook.getSheet(0);
		if (sheet.getRows() < 2 || sheet.getColumns() < 1) {
			return excelData;
		}
		for (int i = 2; i < sheet.getRows(); i++) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			for (int j = 0; j < sheet.getColumns(); j++) {
				String fieldName = sheet.getCell(j, 1).getContents();
				Object value = sheet.getCell(j, i).getContents();
				rowData.put(fieldName, value);
			}
			excelData.add(rowData);
		}
		return excelData;
	}
}
