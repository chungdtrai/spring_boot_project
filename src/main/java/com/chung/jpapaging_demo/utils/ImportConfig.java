package com.chung.jpapaging_demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

// sử dụng để định nghĩa cách nhập dữ liệu từ một file Excel vào một đối tượng cụ thể
public class ImportConfig {
    // chỉ ố của sheet cần nhập liệu
    private int sheetIndex;
    // chỉ số của hàng tiều đề
    private int headerIndex;
    // chỉ số bắt đầu của hàng dữ liệu
    private int startRow;
    // lớp Java mà dứ liệu excel được ánh xạ vào
    // Class là lớp đại diện cho một lớp hoặc một kiểu dữ liệu trong chương trình
    // Class cho biết thông tin về lớp, các phương thức, thuộc tính, hàm, các lớp cha, các giao diện mà nó thực thi
    // Cơ chế reflection: có thể tạo đối tượng từ Class
    private Class dataClass;
    // ánh xạ các cột trong excel với từng thuộc tính dược ánh xạ
    private List<CellConfig> cellConfigList;

    public static final ImportConfig categoryImportConfig; ;
    static {
        categoryImportConfig = new ImportConfig();
        categoryImportConfig.setSheetIndex(0);
        categoryImportConfig.setHeaderIndex(0);
        categoryImportConfig.setStartRow(1);
        categoryImportConfig.setDataClass(CategoryImportDto.class);
        List<CellConfig> categoryCellConfigList = new ArrayList<>();
        categoryCellConfigList.add(new CellConfig(0, "id"));
        categoryCellConfigList.add(new CellConfig(1, "name"));
        categoryCellConfigList.add(new CellConfig(2, "description"));
        categoryImportConfig.setCellConfigList(categoryCellConfigList);
    }
}
