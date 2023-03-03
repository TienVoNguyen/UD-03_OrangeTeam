package com.orange.domain.model.GHN;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class GHNProvince {

    @JsonProperty
    private Integer ProvinceID;
    @JsonProperty
    private String ProvinceName;
    @JsonProperty
    private Integer CountryID;
    @JsonProperty
    private Integer Code;
    @JsonProperty
    private String[] NameExtension;
    @JsonProperty
    private Integer IsEnable;
    @JsonProperty
    private Integer RegionID;
    @JsonProperty
    private Integer RegionCPN;
    @JsonProperty
    private Long UpdatedBy;
    @JsonProperty
    private String CreatedAt;
    @JsonProperty
    private String UpdatedAt;
    @JsonProperty
    private Boolean CanUpdateCOD;
    @JsonProperty
    private Integer Status;
}
