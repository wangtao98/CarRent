package com.iwiller.stat.utils;

import com.iwiller.bus.domain.BusCustomer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

public class ExportCustomerUtils {

    /**
     * 导出数据类型
     * @param customers
     * @param sheetName
     * @return
     */
    public static ByteArrayOutputStream exportCustomer(List<BusCustomer> customers, String sheetName) {
        //一组装excel文档
        //1.创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建样式
        HSSFCellStyle baseStyle = ExportHSSFCellStyle.createBaseStyle(workbook);
        HSSFCellStyle subTitleStyle = ExportHSSFCellStyle.createSubTitleStyle(workbook);
        HSSFCellStyle tableTitleStyle = ExportHSSFCellStyle.createTableTitleStyle(workbook);
        HSSFCellStyle titleStyle = ExportHSSFCellStyle.createTitleStyle(workbook);
        //3.在工作簿创建sheet
        HSSFSheet sheet = workbook.createSheet();
        //4.设置sheet
        sheet.setDefaultColumnWidth(25);
        //5.合体
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 6);
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 6);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        //6.创建第一行
        int index = 0;
        HSSFRow row1 = sheet.createRow(index);
        //6.1在第一行里面创建一个单元格
        HSSFCell row1Cell = row1.createCell(0);
        //6.2设置标题样式
        row1Cell.setCellStyle(titleStyle);
        //6.3设置单元格内容
        row1Cell.setCellValue("客户数据列表");

        //7.第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        //7.1在第二行里面创建一个单元格
        HSSFCell row2Cell = row2.createCell(0);
        //7.2设置标题样式
        row2Cell.setCellStyle(subTitleStyle);
        //7.3设置单元格内容
        row2Cell.setCellValue("总条数:"+customers.size()+"  导出时间:"+ new Date().toLocaleString());

        //8.第三行
        String[] titles = {"身份证号","客户姓名","客户电话","客户地址","性别","录入时间"};
        index++;
        HSSFRow row3 =sheet.createRow(index);
        for (int i = 0; i < titles.length; i++) {
            HSSFCell row3Cell = row3.createCell(i);
            row3Cell.setCellStyle(tableTitleStyle);
            row3Cell.setCellValue(titles[i]);
        }

        for (int i = 0; i < customers.size(); i++) {
            index++;
            BusCustomer customer = customers.get(i);
            HSSFRow row = sheet.createRow(index);
            //9.1创建列身份证号
            HSSFCell row_identity = row.createCell(0);
            row_identity.setCellStyle(baseStyle);
            row_identity.setCellValue(customer.getIdentity());
            //9.2创建列客户姓名
            HSSFCell row_custname = row.createCell(1);
            row_custname.setCellStyle(baseStyle);
            row_custname.setCellValue(customer.getCustname());
            //9.3创建列客户电话
            HSSFCell row_phone = row.createCell(2);
            row_phone.setCellStyle(baseStyle);
            row_phone.setCellValue(customer.getPhone());
            //9.4创建列客户地址
            HSSFCell row_address= row.createCell(3);
            row_address.setCellStyle(baseStyle);
            row_address.setCellValue(customer.getAddress());
            //9.5创建列客户职位
            HSSFCell row_career = row.createCell(4);
            row_career.setCellStyle(baseStyle);
            row_career.setCellValue(customer.getCareer());
            //9.6创建列性别
            HSSFCell row_sex = row.createCell(5);
            row_sex.setCellStyle(baseStyle);
            row_sex.setCellValue(customer.getSex()==1?"男":"女");
            //9.7创建列录入时间
            HSSFCell row_createtime = row.createCell(6);
            row_createtime.setCellStyle(baseStyle);
            row_createtime.setCellValue(customer.getCreatetime().toLocaleString());

        }
        //到此excel组装完成
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //把workbook里面的数据写到outputStream
        try {
            workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return outputStream;
    }
}
