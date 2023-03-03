package com.orange.domain.mapper;

import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreProcedure {
    @Procedure(name = "get_top_product_by_stt")
    List<OgetTopProductByStt> getTopProductsByStatusAndDate(
            @Param("p_formdate") String fromDate,
            @Param("p_todate") String toDate,
            @Param("p_top") int top,
            @Param("p_status") int status
    );

    @Procedure(name = "get_count_all_status")
    List<OgetCountAllStatus> getCountAllStatus(
            @Param("p_formdate") String fromDate,
            @Param("p_todate") String toDate
    );


    @Procedure(name = "total_amount_by_status")
    Double getTotalAmountByStatus(
            @Param("p_fromdate") String fromDate,
            @Param("p_todate") String toDate,
            @Param("p_status") Integer status
    );

}
