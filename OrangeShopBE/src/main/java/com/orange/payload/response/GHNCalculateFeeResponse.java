package com.orange.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GHNCalculateFeeResponse {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("service_fee")
    private Integer serviceFee;

    @JsonProperty("insurance_fee")
    private Integer insuranceFee;

    @JsonProperty("pick_station_fee")
    private Integer pickStationFee;

    @JsonProperty("coupon_value")
    private Integer couponValue;

    @JsonProperty("r2s_fee")
    private Integer r2sFee;
}
