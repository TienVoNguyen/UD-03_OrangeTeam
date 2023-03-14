package com.orange.payload.response;

import com.orange.domain.model.ghn.GHNProvince;
import lombok.Data;

import java.util.List;

@Data
public class GHNProvinceResponse {
    private List<GHNProvince> data;
}
