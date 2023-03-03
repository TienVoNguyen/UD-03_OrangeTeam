package com.orange.controller;

import com.orange.common.payload.Result;
import com.orange.domain.mapper.impl.StorePorcedureImpl;
import com.orange.domain.model.ModelProcedure.OgetCountAllStatus;
import com.orange.domain.model.ModelProcedure.OgetTopProductByStt;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("admin/procedure")
public class StoreProcedureController {

    private StorePorcedureImpl storeService;


    @GetMapping("/topProducts")
    public Result<?> getTopProductsByStatusAndDate(
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam int top,
            @RequestParam int status
    ) {
        List<OgetTopProductByStt> result = storeService.getTopProduct(fromDate,toDate,top,status);
        return Result.result(HttpStatus.OK.value(),"Lấy Dữ Liệu Thành Công",result );
    }



    @GetMapping("/countAllStatus")
    private Result<?> getCountAllStatus(@RequestParam String fromDate,
                                        @RequestParam String toDate){
        List<OgetCountAllStatus> result = storeService.getCountAllStatus(fromDate,toDate);
        return Result.result(HttpStatus.OK.value(),"Lấy Dữ Liệu Thành Công",result );
    }



    @GetMapping("/totalAmountByStatus")
    public Result<?> getTotalAmountByStatus(
            @RequestParam String fromDate,
            @RequestParam String toDate,
            @RequestParam Integer status
    ) {
        Double result = storeService.getTotalAmountByStatus(fromDate,toDate,status);
        return Result.result(HttpStatus.OK.value(),"Lấy Dữ Liệu Thành Công",result);
    }




}
