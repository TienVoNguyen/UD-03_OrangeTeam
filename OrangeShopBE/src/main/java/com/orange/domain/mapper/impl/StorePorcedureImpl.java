package com.orange.domain.mapper.impl;

import com.orange.domain.mapper.IStoreProcedure;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StorePorcedureImpl {
    @Autowired
    private IStoreProcedure storeProcedure;

    public List<OgetTopProductByStt> getTopProduct(String fromDate,
                                                   String toDate,
                                                    int top,
                                                   int status){
        return storeProcedure.getTopProductsByStatusAndDate(fromDate, toDate, top, status);
    }

    public List<OgetCountAllStatus> getCountAllStatus(String fromDate,String toDate) {
        return storeProcedure.getCountAllStatus(fromDate, toDate);
    }

    public Double getTotalAmountByStatus(String fromDate, String toDate,Integer status) {
        return storeProcedure.getTotalAmountByStatus(fromDate, toDate, status);
    }

}
