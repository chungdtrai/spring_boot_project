package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.dto.ColorDto;
import com.chung.jpapaging_demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping("/create")
    public ResponseEntity<ColorDto> create(@RequestBody ColorDto colorDto){
        return ResponseEntity.ok(colorService.create(colorDto));
    }

    @PutMapping("/update")
    public ResponseEntity<ColorDto> update(@RequestBody ColorDto colorDto){
        return ResponseEntity.ok(colorService.update(colorDto));
    }

    @GetMapping("/search")
    public ResponseEntity<ColorDto> search(@RequestParam(value = "id") Long id){
        return ResponseEntity.ok(colorService.getColorById(id));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<ColorDto>> findAll(){
        return ResponseEntity.ok(colorService.getAllColors());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam(value = "id") Long id){
        colorService.deleteColorById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    

}
