package com.admin.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class PoiHelper {
	
	
	public static void createXls(OutputStream out,com.admin.logic.model.ExportXlsVo vo) throws IOException {
        Workbook wb=new HSSFWorkbook();
        Sheet sheet=wb.createSheet(vo.getSheetName());
        int x=0;
        int y=0;
        Row titles=sheet.createRow(x);
        for(String title:vo.getTitles()){
            titles.createCell(y++).setCellValue(title);
        }
        for(List<String> rowList:vo.getRows()){
            y=0;
            Row row=sheet.createRow(++x);
            for(String object:rowList){
                row.createCell(y++).setCellValue(object);
            }
        }
        wb.write(out);
        out.flush();
        out.close();
    }
	
    /**
     * 读取Excel数据内容
     * @param is
     * @return
     * @throws IOException 
     */
    public static List<Map<Integer, String>> readExcelContent(InputStream is) throws IOException {
    	List<Map<Integer, String>> result=new ArrayList<Map<Integer, String>>();
		POIFSFileSystem fs=new POIFSFileSystem(is);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet sheet=wb.getSheetAt(0);
		
		int rowNum = sheet.getLastRowNum()+1;			// 得到总行数
		if(rowNum==0){
			return result;
		}
		HSSFRow row= sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        
        for(int i=0;i<rowNum;i++){
        	Map<Integer, String> line = new HashMap<Integer, String>();
        	row=sheet.getRow(i);
        	for(int j=0;j<colNum;j++){
        		line.put(j, getCellFormatValue(row.getCell(j)).trim());
        	}
        	result.add(line);
        }
        return result;
    }


    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(HSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
            // 如果当前Cell的Type为NUMERIC
            case HSSFCell.CELL_TYPE_NUMERIC:
            case HSSFCell.CELL_TYPE_FORMULA: {
                // 判断当前的cell是否为Date
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellvalue = sdf.format(date);
                    
                }
                // 如果是纯数字
                else {
                    // 取得当前Cell的数值
                    cellvalue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            }
            // 如果当前Cell的Type为STRIN
            case HSSFCell.CELL_TYPE_STRING:
                // 取得当前的Cell字符串
                cellvalue = cell.getRichStringCellValue().getString();
                break;
            // 默认的Cell值
            default:
                cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
	
}
