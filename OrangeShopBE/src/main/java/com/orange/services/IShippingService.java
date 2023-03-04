package com.orange.services;

import com.orange.domain.model.GHN.GHNProvince;
import com.orange.domain.model.GHN.GHNShippingOrder;
import com.orange.payload.response.GHNShippingOrderResponse;

import java.util.List;

public interface IShippingService {
    List<GHNProvince> getProvince();

    GHNShippingOrderResponse createShippingOrder(GHNShippingOrder ghnShippingOrder);

}
