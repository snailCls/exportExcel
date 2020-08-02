package zzh.excel.excel.service.impl;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zzh.excel.excel.controller.BaseFrontController;
import zzh.excel.excel.entity.User;
import zzh.excel.excel.service.ExportService;
import zzh.excel.excel.util.ExcelFormatUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author snail
 */
@Service
public class ExportServiceImpl implements ExportService {

    private static final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);


    @Override
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info(">>>>>>>>>>开始导出excel>>>>>>>>>>");

            // 造几条数据
            List<User> list = new ArrayList<>();
            list.add(new User("唐三藏", "男", 30, "13411111111", "东土大唐", "取西经"));
            list.add(new User("孙悟空", "男", 29, "13411111112", "菩提院", "打妖怪"));
            list.add(new User("猪八戒", "男", 28, "13411111113", "高老庄", "偷懒"));
            list.add(new User("沙悟净", "男", 27, "13411111114", "流沙河", "挑担子"));

            BaseFrontController baseFrontController = new BaseFrontController();
            return baseFrontController.buildResponseEntity(export((List<User>) list), "用户表.xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>导出excel 异常，原因为：" + e.getMessage());
        }
        return null;
    }

    private InputStream export(List<User> list) {
        logger.info(">>>>>>>>>>>>>>开始进入导出方法>>>>>>>>>>>>>>");
        ByteArrayOutputStream output = null;
        InputStream inputStream = null;
        //保留1000条数据在内存中
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
        SXSSFSheet sheet = workbook.createSheet();

        //设置报表头样式
        CellStyle header = ExcelFormatUtil.headStyle(workbook);
        CellStyle content = ExcelFormatUtil.contentStyle(workbook);

        //每一列字段名
        String[] strs = new String[] {"姓名", "性别", "年龄", "手机号", "地址", "爱好"};


        //字段名所在表格的宽度
        int[] ints = new int[] {5000, 5000, 5000, 5000, 5000, 5000};

        //设置表头样式
        ExcelFormatUtil.initTileEX(sheet, header, strs, ints);
        logger.info(">>>>>>>>>>>>>表头样式设置完成>>>>>>>>>>>>>>>");


        if(list != null && list.size() > 0 ) {
            logger.info(">>>>>>>>>>>>>>开始便利数据组装单元格内容>>>>>>>>>>>>");
            for(int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                SXSSFRow row = sheet.createRow(i + 1);
                int j = 0;

                SXSSFCell cell = row.createCell(j++);
                cell.setCellValue(user.getName());
                cell.setCellStyle(content);

                //性别
                cell = row.createCell(j++);
                cell.setCellValue(user.getSex());
                cell.setCellStyle(content);

                //年龄
                cell = row.createCell(j++);
                cell.setCellValue(user.getAge());
                cell.setCellStyle(content);

                //手机号
                cell = row.createCell(j++);
                cell.setCellValue(user.getPhoneNo());
                cell.setCellStyle(content);

                //地址
                cell = row.createCell(j++);
                cell.setCellValue(user.getAddress());
                cell.setCellStyle(content);

                //爱好
                cell = row.createCell(j++);
                cell.setCellValue(user.getHobby());
                cell.setCellStyle(content);
            }
            logger.info(">>>>>>>>>>>>>>>...结束便利数据组装单元格>>>>>>>>>>>>.");
        }

        try{
            output = new ByteArrayOutputStream();
            workbook.write(output);
            inputStream = new ByteArrayInputStream(output.toByteArray());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(output != null) {
                    output.close();
                    if(inputStream != null) {
                        inputStream.close();
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }
}
