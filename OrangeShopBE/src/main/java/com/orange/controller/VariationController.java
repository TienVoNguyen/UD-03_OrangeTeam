package com.orange.controller;

import com.orange.domain.dto.VariationDTO;
import com.orange.domain.mapper.IVariationMapper;
import com.orange.services.IVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.orange.common.payload.Page;


@RestController
@RequiredArgsConstructor
@RequestMapping("test/variation")
public class VariationController {
    private final IVariationService variationService;

    @PostMapping("/insertVariation")
    public ResponseEntity<VariationDTO> create(@RequestBody VariationDTO variationDTO) {
        VariationDTO result = variationService.create(variationDTO);
        return ResponseEntity.ok(result);
    }


    @GetMapping("")
    public ResponseEntity<Page<VariationDTO>> getAllVariations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Page<VariationDTO> variations = (Page<VariationDTO>) variationService.fillAll(
                PageRequest.of(page, size, Sort.by(sort[0]).descending())
        );

        return new ResponseEntity<>(variations, HttpStatus.OK);
    }



}
