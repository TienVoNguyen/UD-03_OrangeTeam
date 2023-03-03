package com.orange.domain.model.ModelProcedure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OgetCountAllStatus {
    private Integer Completed;
    private Integer Cancel;
    private Integer WaitForPay;
    private Integer DeliveryInProgress;
    private Integer WaitForConfirmation;
    private Integer Confirmed;
}
