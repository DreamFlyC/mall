package com.fun.mall.util;

import com.fun.mall.entity.School;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 9:12 2018/12/19tatu
 * @ Description：导出工具类
 * @ Modified By：
 * @ Version    : 1.0$
 */
public class ExportUtil {
    private final static Logger log= LoggerFactory.getLogger(ExportUtil.class);

    public static void exportExcel(HttpServletResponse response, List<School> list, String sheetName,
                                   String fileName) {
        // 创建工作表
        WritableWorkbook book = null;
        response.reset();
        // 设置字符集
        response.setCharacterEncoding("UTF-8");
        // 创建工作流
        OutputStream os = null;
        try {
            // 点击导出excel按钮时候页面显示的默认名称
            fileName = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
            response.setCharacterEncoding("gb2312");
            response.reset();
            response.setContentType("application/OCTET-STREAM;charset=gb2312");
            response.setHeader("pragma", "no-cache");
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + fileName + ".xls\"");
            os = response.getOutputStream();

            // 初始化工作表
            book = Workbook.createWorkbook(os);

        } catch (IOException e1) {
            log.error("导出excel出现IO异常", e1);
        }
        try {

            // 以下为excel表格内容
            WritableSheet sheet = book.createSheet(sheetName, 0);
            // 表字段名
            sheet.addCell(new jxl.write.Label(0, 0, "序号"));
            sheet.addCell(new jxl.write.Label(1, 0, "ID"));
            sheet.addCell(new jxl.write.Label(2, 0, "学校"));
            sheet.addCell(new jxl.write.Label(3, 0, "地址"));
            sheet.addCell(new jxl.write.Label(4, 0, "电话"));
            sheet.addCell(new jxl.write.Label(5, 0, "邮编"));
            sheet.addCell(new jxl.write.Label(6, 0, "经度"));
            sheet.addCell(new jxl.write.Label(7, 0, "纬度"));
            // 将数据追加
            for (int i = 1; i < list.size() + 1; i++) {
                // 序号从1开始
                sheet.addCell(new jxl.write.Label(0, i, String.valueOf(i)));
                sheet.addCell(new jxl.write.Label(1, i, String.valueOf(list.get(i - 1).getId())));
                sheet.addCell(new jxl.write.Label(2, i, list.get(i - 1).getSchool()));
                sheet.addCell(new jxl.write.Label(3, i, list.get(i - 1).getAddress()));
                sheet.addCell(new jxl.write.Label(4, i, list.get(i-1).getTel()));
                sheet.addCell(new jxl.write.Label(5, i, list.get(i - 1).getPost()));
                sheet.addCell(new jxl.write.Label(6, i, list.get(i - 1).getLongitude()));
                sheet.addCell(new jxl.write.Label(7, i, list.get(i - 1).getLatitude()));
            }
            book.write();
            book.close();
        } catch (Exception e) {
            log.error("导出excel出现异常", e);
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("关流出现异常", e);
                    e.printStackTrace();
                }
            }
        }
    }
}

