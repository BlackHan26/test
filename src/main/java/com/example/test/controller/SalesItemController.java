package com.example.test.controller;

import com.example.test.dto.ResponseDto;
import com.example.test.service.SalesItemService;
import com.example.test.dto.SalesItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class SalesItemController {
    private final SalesItemService salesItemService;

    //Post/articles
    @PostMapping
    public ResponseDto create(@RequestBody SalesItemDto dto) {
        return salesItemService.createSalesItem(dto);
    }

    @GetMapping("/{id}")
    public SalesItemDto read(@PathVariable("id") Long id) {
        return salesItemService.readSalesItem(id);
    }

    @PutMapping("/{id}")
    public ResponseDto update(
            @PathVariable("id") Long id,
            @RequestBody SalesItemDto dto) {
        return salesItemService.updateSale(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto delete(@PathVariable("id") Long id, @RequestBody SalesItemDto dto) {
        return salesItemService.deleteSale(id, dto.getWriter(), dto.getPassword());
    }


}
