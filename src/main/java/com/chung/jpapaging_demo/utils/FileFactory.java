package com.chung.jpapaging_demo.utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class FileFactory {
    public static Workbook getWorkbookStream(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try{
            inputStream = multipartFile.getInputStream();
            // WorkbookFactory được sử dụng để tạo đối tượng workbook mà không cần phải biết trước loại định
            // dạng xlsx hay xls
            Workbook workbook = WorkbookFactory.create(inputStream);
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
