package com.orange.domain.mapper;

import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreProcedure {
    OgetCountAllStatus getCountAllStatus(String fromDate, String toDate);
    List<OgetTopProductByStt> getTopProducts(Integer status, String fromDate, String toDate, Integer top);
    double getTotalAmountByStatus(String fromDate, String toDate, int status);


}
