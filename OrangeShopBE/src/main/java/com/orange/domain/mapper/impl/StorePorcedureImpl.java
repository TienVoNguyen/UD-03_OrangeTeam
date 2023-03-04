package com.orange.domain.mapper.impl;

import com.orange.domain.mapper.IStoreProcedure;
import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StorePorcedureImpl implements IStoreProcedure{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StorePorcedureImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public OgetCountAllStatus getCountAllStatus(String fromDate, String toDate) {
        String query = "CALL get_count_all_status(?, ?)";
        return jdbcTemplate.queryForObject(query, new Object[]{fromDate, toDate}, (rs, rowNum) -> {
            OgetCountAllStatus countAllStatus = new OgetCountAllStatus();
            countAllStatus.setCompleted(rs.getInt("Completed"));
            countAllStatus.setCancel(rs.getInt("Cancel"));
            countAllStatus.setWaitForPay(rs.getInt("Wait For Pay"));
            countAllStatus.setDeliveryInProgress(rs.getInt("Delivery In Progress"));
            countAllStatus.setWaitForConfirmation(rs.getInt("Wait For Confirmation"));
            countAllStatus.setConfirmed(rs.getInt("Confirmed"));
            return countAllStatus;
        });
    }

    @Override
    public List<OgetTopProductByStt> getTopProducts(Integer status, String fromDate, String toDate, Integer top) {
            String query = "CALL get_top_product_by_stt(?, ?, ?, ?)";
            List<OgetTopProductByStt> topProducts = jdbcTemplate.query(query, new Object[]{fromDate, toDate, top, status},
                    (rs, rowNum) -> {
                        OgetTopProductByStt ogetTopProductByStt = new OgetTopProductByStt();
                        ogetTopProductByStt.setProductDetailsId(rs.getLong("product_detail_id"));
                        ogetTopProductByStt.setSum(rs.getInt("Sum"));
                        return ogetTopProductByStt;
                    });
            return topProducts;
    }

    @Override
    public double getTotalAmountByStatus(String fromDate, String toDate, int status) {
        String sql = "CALL total_amount_by_status(?, ?, ?)";
        Double result = jdbcTemplate.queryForObject(sql, new Object[]{fromDate, toDate, status},
                new RowMapper<Double>() {
                    @Override
                    public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getDouble("total_sold");
                    }
                });
        return result != null ? result : 0;
    }

}
