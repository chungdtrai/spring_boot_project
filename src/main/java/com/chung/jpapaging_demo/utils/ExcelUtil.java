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
                if(countRow < importConfig.getStartRow()){
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
                        // Thiết lập tất cả các kiểu dữ liệu về kiểu string
                        cell.setCellType(CellType.STRING);
                        // Đọc dữ liệu từ cell truyền ra đối tượng object
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

    // set dữ liệu từ cell vào đối tượng cụ thể thông qua Field
    private static <T> void setFieldValue(Object instance, Field field, Object cellValue) {
        if(ObjectUtils.isEmpty(instance)||ObjectUtils.isEmpty(field)){
            return;
        }
        // Xác định kiểu của trường dữ liệu
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
            case "char":
                cellValue = parseChar(cellValue);
                break;
            case "String":
                cellValue = cellValue.toString().trim();
                break;
            case "boolean":
            case "Boolean":
                cellValue = parseBoolean(cellValue);
                break;
            case "byte":
            case "Byte":
                cellValue = parseByte(cellValue);
                break;
            case "short":
            case "Short":
                cellValue = parseShort(cellValue);
                break;
            case "int":
            case "Integer":
                cellValue = parseInteger(cellValue);
                break;
            case "long":
            case "Long":
                cellValue = parseLong(cellValue);
                break;
            case "float":
            case "Float":
                cellValue = parseFloat(cellValue);
                break;
            case "double":
            case "Double":
                cellValue = parseDouble(cellValue);
                break;
            case "Date":
//                cellValue = parseDate(cellValue);
                break;
            case "Instant":
//                cellValue = parseInstant(cellValue);
                break;
            case "Enum":
//                cellValue = parseEnum(cellValue);
                break;
            case "Map":
//                cellValue = parseMap(cellValue);
                break;
            case "BigDecimal":
//                cellValue = parseBigDecimal(cellValue);
                break;
            default:
                break;
        }

        return cellValue;
    }

    private static Object parseChar(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (char)cellValue;
    }

    private static Object parseBoolean(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Boolean)cellValue;
    }

    private static Object parseByte(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Byte)cellValue;
    }

    private static Object parseShort(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Short)cellValue;
    }

    private static Object parseInteger(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Integer)cellValue;
    }

    private static Object parseLong(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Long)cellValue;
    }

    private static Object parseFloat(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Float)cellValue;
    }

    private static Object parseDouble(Object cellValue) {
        return ObjectUtils.isEmpty(cellValue) ? null : (Double)cellValue;
    }


}
