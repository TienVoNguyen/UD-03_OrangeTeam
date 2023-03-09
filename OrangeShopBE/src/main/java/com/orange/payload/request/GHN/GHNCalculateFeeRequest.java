package com.orange.payload.request.GHN;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class GHNCalculateFeeRequest {

    @JsonProperty("from_district_id")
    private Integer fromDistrictId;

    @JsonProperty("service_id")
    private Integer serviceId;

    @JsonProperty("service_type_id")
    private Integer serviceTypeId;

    @JsonProperty("to_district_id")
    private Integer toDistrictId;

    @JsonProperty("to_ward_code")
    private String toWardCode;

    @JsonProperty("height")
    @Value("50")
    private Integer height;

    @JsonProperty("length")
    @Value("20")
    private Integer length;

    @JsonProperty("weight")
    @Value("200")
    private Integer weight;

    @JsonProperty("width")
    @Value("20")
    private Integer width;

    @JsonProperty("insurance_value")
    private Integer insuranceValue;

    @JsonProperty("coupon")
    private String coupon;
}
