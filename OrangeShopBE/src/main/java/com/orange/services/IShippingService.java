package com.orange.services;

import com.orange.domain.model.GHN.GHNProvince;

import java.util.List;

public interface IShippingService {
    List<GHNProvince> getProvince();
}
