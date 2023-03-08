package com.orange.controller;

import com.orange.services.IStoreProcedureService;
import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("test/admin/procedure")
@RequiredArgsConstructor
public class StoreProcedureController {
    private final IStoreProcedureService procedureService;


    @GetMapping("/getcountallstatusbydate")
    public OgetCountAllStatus getOrderStatus(@RequestParam(defaultValue = "1/1/2021") String fromDate, @RequestParam(defaultValue = "1/1/2025") String toDate) {
        return procedureService.getCountAllStatus(fromDate, toDate);
    }

    @GetMapping("/topproductbystt")
    public List<OgetTopProductByStt> getTopProducts(@RequestParam(defaultValue="1")         Integer status,
                                                    @RequestParam(defaultValue="1/1/2021")  String fromDate,
                                                    @RequestParam(defaultValue="1/1/2023")  String toDate,
                                                    @RequestParam(defaultValue="10")        Integer top) {
        return procedureService.getTopProducts(status, fromDate, toDate, top);
    }

    @GetMapping("/totalAmountByStatus")
    public double getTotalAmountByStatus(@RequestParam(defaultValue="1/1/2021") String fromDate, @RequestParam(defaultValue="1/1/2023") String toDate
            , @RequestParam(defaultValue="1") int status) {
        return procedureService.getTotalAmountByStatus(fromDate, toDate, status);
    }

}
