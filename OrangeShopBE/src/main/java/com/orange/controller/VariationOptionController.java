package com.orange.controller;

import com.orange.domain.dto.VariationOptionDTO;
import com.orange.services.IVariationOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.orange.common.payload.Page;

import java.awt.print.Pageable;

@RestController
@RequestMapping("test/variation-options")
@RequiredArgsConstructor
public class VariationOptionController {
    private final IVariationOptionService variationOptionService;

    @PostMapping("create")
    public ResponseEntity<VariationOptionDTO> create(@RequestBody VariationOptionDTO dto) {
        VariationOptionDTO result = variationOptionService.create(dto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(result);
    }

    @PostMapping("update")
    public ResponseEntity<VariationOptionDTO> update(@RequestBody VariationOptionDTO dto) {
        VariationOptionDTO result = variationOptionService.update(dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        VariationOptionDTO dto = new VariationOptionDTO();
        dto.setId(id);
        variationOptionService.delete(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getme")
    public ResponseEntity<Page<VariationOptionDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {
        Page<VariationOptionDTO> variationOptionDTOPages = (Page<VariationOptionDTO>) variationOptionService.fillAll(PageRequest.of(page, size, Sort.by(sort[0]).descending()));
        return ResponseEntity.ok(variationOptionDTOPages);
    }


    @GetMapping("getById/{id}")
    public ResponseEntity<VariationOptionDTO> getById(@PathVariable Long id) {
        VariationOptionDTO result = variationOptionService.findById(id);
        return ResponseEntity.ok(result);
    }
}
