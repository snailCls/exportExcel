package zzh.excel.excel.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * excel样式类
 * @author snail
 */
public class ExcelFormatUtil {

    /**
     * 设置报表头样式
     * @param workbook
     * @return
     */
    public static CellStyle headStyle(SXSSFWorkbook workbook) {
        //设置style1的样式，此样式运用在第二行
        CellStyle style1 = workbook.createCellStyle();
        //设置单元格背景色，设置单元格背景色一下两句不许同时设置
        //设置填充样式
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置填充样式
        style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        //设置单元格上、下、左、右的边框线
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //创建字体对象
        Font font = workbook.createFont();
        font.setBoldweight((short)10);
        font.setFontHeightInPoints((short)10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style1.setFont(font);
        //设置自动换行
        style1.setWrapText(true);
        //设置单元格字体显示居中(左右方向）
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置单元格字体显示居中(上下方向）
        style1.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style1;
    }

    /**
     * 设置报表体样式
     * @param sheets
     * @return
     */
    public static CellStyle contentStyle(SXSSFWorkbook sheets) {

        //设置style的样式，此样式运用在第二行
        CellStyle style = sheets.createCellStyle();
        //设置单元格上下左右的边框线
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);

        //设置自动换行
        style.setWrapText(true);
        //设置单元格字体显示居中（左右居中)
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        //设置单元格字体居中(上下)
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    public static HSSFCellStyle titleStyle(HSSFWorkbook workbook,
                                           short color, short fontSize) {
        //设置style样式，此样式运用在第二行
        HSSFCellStyle style = workbook.createCellStyle();
        //设置单元格背景色，设置单元格背景色一下两句必须设置
        //设置填充样式
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        short fcolor = color;
        if(color != HSSFColor.WHITE.index) {
            //设置填充色
            style.setFillForegroundColor(color);
        }

        //设置单元格上下左右的边框线
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(fontSize);
        font.setFontHeightInPoints(fontSize);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;

    }


    public static void initTileEX(SXSSFSheet sheet, CellStyle header
            , String[] title, int[] titleLength) {
        SXSSFRow row = sheet.createRow(0);
        row.setHeight((short) 800);
        for(int j = 0; j < title.length; j++) {
            SXSSFCell cell = row.createCell(j);

            //设置每一列的字段名
            cell.setCellValue(title[j]);
            cell.setCellStyle(header);
            sheet.setColumnWidth(j, titleLength[j]);
        }
    }


































































}
