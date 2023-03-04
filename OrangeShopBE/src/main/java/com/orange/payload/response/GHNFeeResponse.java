package com.orange.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GHNFeeResponse {
    @JsonProperty("main_service")
    private Integer mainService;

    @JsonProperty("insurance")
    private Integer insurance;

    @JsonProperty("station_do")
    private Integer stationDo;

    @JsonProperty("station_pu")
    private Integer stationPu;

    @JsonProperty("return")
    private Integer returnFee;

    @JsonProperty("r2s")
    private Integer r2s;

    @JsonProperty("coupon")
    private Integer coupon;
}
