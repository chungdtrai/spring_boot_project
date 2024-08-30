package com.chung.jpapaging_demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    // Truyền dữ liệu từ workbook sang list
    public static <T> List<T> getImportData(Workbook workbook, ImportConfig importConfig) {
        List<T> list = new ArrayList<T>();
        // danh sách các cột trong excel tương ứng với các trường dữ liệu của đối tượng java
        List<CellConfig> cellConfigs = importConfig.getCellConfigList();
        int countSheet= 0;

        for(Sheet sheet : workbook) {
            if(countSheet != importConfig.getSheetIndex()){
                countSheet++;
                continue;
            }
            int countRow = 0;
            for(Row row:sheet){
                if(countRow != importConfig.getStartRow()){
                    countRow++;
                    continue;
                }
                // Đọc dữ liệu của hàng ra đối tượng java
                T rowData = getRowData(row, cellConfigs, importConfig.getDataClass());
                list.add(rowData);
                countRow++;
            }
            countSheet++;
        }
        return list;
    }

    // Truyền dữ liệu từ một row cụ thể sang đối tượng trong java
    private static <T> T getRowData(Row row, List<CellConfig> cellConfigs, Class dataClass) {
        T instance = null;
        try{
            // tạo đối tượng mà class đó đại diện
            instance = (T)dataClass.getDeclaredConstructor().newInstance();
            for(int i=0; i<cellConfigs.size(); i++){
                CellConfig currentCellConfig = cellConfigs.get(i);
                try{
                    // Lớp Field đai diện cho một trường, một lớp hoặc 1 interface
                    // Thông qua lớp Field có thể thao tác trực tiếp với thuộc tính của một lớp
                    // Cho phép truy xuất, thay đổi giá trị của các trường trong một lớp mà không cần biết tên
                    //trước khi biên dịch
                    // Lấy dữ liệu về trường
                    Field field = getDeclaredField(dataClass, currentCellConfig.getColumnName());
                    // Lấy ô muốn import dữ liệu
                    Cell cell = row.getCell(currentCellConfig.getColumnIndex());
                    if(!ObjectUtils.isEmpty(cell)){
                        // Thiết lập tất cả các kiểu dữ liệu
                        cell.setCellType(CellType.STRING);
                        Object cellValue = cell.getStringCellValue();
                        setFieldValue(instance, field, cellValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return instance;
    }

    private static <T> void setFieldValue(Object instance, Field field, Object cellValue) {
        if(ObjectUtils.isEmpty(instance)||ObjectUtils.isEmpty(field)){
            return;
        }
        Class clazz = field.getType();
        Object valueConverted = parseValueByClass(clazz, cellValue);
        field.setAccessible(true);
        try{
            field.set(instance, valueConverted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // gán đối tượng Field có một trường dữ liệu cụ thể của lớp
    private static Field getDeclaredField(Class clazz, String fieldName) {
        if(ObjectUtils.isEmpty(clazz)){
            return null;
        }
        do{
            try{
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {
                log.info(""+ e);
            }
        }while((clazz = clazz.getSuperclass()) != null);
        return null;
    }

    private static Object parseValueByClass(Class clazz, Object cellValue) {
        if(ObjectUtils.isEmpty(cellValue) || ObjectUtils.isEmpty(clazz)){
            return null;
        }
        String clazzName = clazz.getSimpleName();
        switch(clazzName){

        }
    }
}
