package com.chung.jpapaging_demo.service;

import com.chung.jpapaging_demo.dto.ColorDto;

import java.util.List;

public interface ColorService {
    ColorDto create(ColorDto colorDto);
    ColorDto update(ColorDto colorDto);
    ColorDto getColorById(Long id);
    List<ColorDto> getAllColors();
    void deleteColorById(Long id);
}
