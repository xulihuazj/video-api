package com.yinfeixing.utils.excel;

import com.yinfeixing.utils.log.LogHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class ExcelUtil {

    private final static String xls = "xls";

    private final static String xlsx = "xlsx";

    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            LogHelper.info(e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
        //数字
        case Cell.CELL_TYPE_NUMERIC:
            cellValue = String.valueOf(cell.getNumericCellValue());
            break;
        //字符串
        case Cell.CELL_TYPE_STRING:
            cellValue = String.valueOf(cell.getStringCellValue());
            break;
        //Boolean
        case Cell.CELL_TYPE_BOOLEAN:
            cellValue = String.valueOf(cell.getBooleanCellValue());
            break;
        //公式
        case Cell.CELL_TYPE_FORMULA:
            cellValue = String.valueOf(cell.getCellFormula());
            break;
        //空值
        case Cell.CELL_TYPE_BLANK:
            cellValue = "";
            break;
        //故障
        case Cell.CELL_TYPE_ERROR:
            cellValue = "非法字符";
            break;
        default:
            cellValue = "未知类型";
            break;
        }
        return cellValue;
    }
}
