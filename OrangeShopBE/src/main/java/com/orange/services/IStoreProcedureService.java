package com.orange.services;

import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;

import java.util.List;

public interface IStoreProcedureService {
    OgetCountAllStatus getCountAllStatus(String fromDate, String toDate);
    List<OgetTopProductByStt> getTopProducts(Integer status, String fromDate, String toDate, Integer top);
    double getTotalAmountByStatus(String fromDate, String toDate, int status);


}
