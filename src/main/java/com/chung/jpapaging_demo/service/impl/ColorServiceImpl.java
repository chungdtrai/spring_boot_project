package com.chung.jpapaging_demo.service.impl;

import com.chung.jpapaging_demo.dto.ColorDto;
import com.chung.jpapaging_demo.entity.Color;
import com.chung.jpapaging_demo.exception.ResoureNotFoundException;
import com.chung.jpapaging_demo.repository.ColorRepository;
import com.chung.jpapaging_demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public ColorDto create(ColorDto colorDto) {
        Color color = colorRepository.save(Color.builder()
                .colorName(colorDto.getColorName())
                .description(colorDto.getDescription())

                .build());
        return ColorDto.builder()
                .id(color.getId())
                .colorName(colorDto.getColorName())
                .description(colorDto.getDescription())

                .build();
    }

    @Override
    public ColorDto update(ColorDto colorDto) {
        Color color = colorRepository.save(Color.builder()
                        .id(colorDto.getId())
                        .colorName(colorDto.getColorName())
                        .description(colorDto.getDescription())
                    .build());
        return ColorDto.builder()
                .id(color.getId())
                .colorName(color.getColorName())
                .description(color.getDescription())
                .build();
    }

    @Override
    public ColorDto getColorById(Long id) {
        Color color = colorRepository.findById(id).orElseThrow(() -> new ResoureNotFoundException("can not find color with id: "+id));
        return ColorDto.builder()
                .id(color.getId())
                .colorName(color.getColorName())
                .description(color.getDescription())
                .build();
    }

    @Override
    public List<ColorDto> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        return colors.stream().map(item ->{
            return ColorDto.builder()
                    .id(item.getId())
                    .colorName(item.getColorName())
                    .description(item.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteColorById(Long id) {
        colorRepository.deleteById(id);
    }
}
