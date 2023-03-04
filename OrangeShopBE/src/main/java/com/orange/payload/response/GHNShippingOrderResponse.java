package com.orange.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GHNShippingOrderResponse {
    @JsonProperty("order_code")
    private String orderCode;

    @JsonProperty("sort_code")
    private String sortCode;

    @JsonProperty("trans_type")
    private String transType;

    @JsonProperty("ward_encode")
    private String wardEncode;

    @JsonProperty("district_encode")
    private String districtEncode;

    @JsonProperty("fee")
    private GHNFeeResponse fee;

    @JsonProperty("total_fee")
    private String totalFee;

    @JsonProperty("expected_delivery_time")
    private String expectedDeliveryTime;

}
