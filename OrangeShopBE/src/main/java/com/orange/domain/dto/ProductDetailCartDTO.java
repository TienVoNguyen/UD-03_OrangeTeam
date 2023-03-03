package com.orange.domain.dto;

import com.orange.domain.model.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
public class ProductDetailCartDTO extends BaseEntity implements Serializable {

    private int productDTO;
    @Size(max = 1000)
    private String images;
    @NotNull
    private Double priceDefault;
    private Double priceSale;
    private Set<VariationOptionDTO> variationOptions;
}
