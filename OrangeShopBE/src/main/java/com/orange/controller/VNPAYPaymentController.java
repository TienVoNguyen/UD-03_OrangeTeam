package com.orange.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.orange.Utils.VNPAY.VNPAYUtils;
import com.orange.common.payload.Result;
import com.orange.domain.model.VNPAY.VNPAY;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin
@RequestMapping("/test/payment")
@RequiredArgsConstructor
public class VNPAYPaymentController {

    @PostMapping
    public Result<?> createUrlPayment() throws UnsupportedEncodingException {
        Integer amount = 10000*100;
        String vnp_TxnRef = VNPAYUtils.getRandomNumber(8);
        Map<String, String> vnp_Params = new HashMap();
        vnp_Params.put(VNPAY.VNPAY_VERSION.getKey(), VNPAY.VNPAY_VERSION.getValue());
        vnp_Params.put(VNPAY.VNPAY_COMMAND.getKey(), VNPAY.VNPAY_COMMAND.getValue());
        vnp_Params.put(VNPAY.VNPAY_TMN_CODE.getKey(), VNPAY.VNPAY_TMN_CODE.getValue());
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        /*String bank_code = req.getParameter("bankcode");
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }*/
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Nap tien cho thue bao 0123456789. So tien 100,000 VND");
        vnp_Params.put(VNPAY.VNPAY_ORDER_TYPE.getKey(), VNPAY.VNPAY_ORDER_TYPE.getValue());
        vnp_Params.put(VNPAY.VNPAY_LOCALE_DEFAULT.getKey(), VNPAY.VNPAY_LOCALE_DEFAULT.getValue());

        /*String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }*/
        vnp_Params.put(VNPAY.VNPAY_RETURN_URL.getKey(), VNPAY.VNPAY_RETURN_URL.getValue());
        vnp_Params.put("vnp_IpAddr", "0:0:0:0:0:0:0:1");
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
//        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        /*//Billing
        vnp_Params.put("vnp_Bill_Mobile", req.getParameter("txt_billing_mobile"));
        vnp_Params.put("vnp_Bill_Email", req.getParameter("txt_billing_email"));
        String fullName = (req.getParameter("txt_billing_fullname")).trim();
        if (fullName != null && !fullName.isEmpty()) {
            int idx = fullName.indexOf(' ');
            String firstName = fullName.substring(0, idx);
            String lastName = fullName.substring(fullName.lastIndexOf(' ') + 1);
            vnp_Params.put("vnp_Bill_FirstName", firstName);
            vnp_Params.put("vnp_Bill_LastName", lastName);

        }*/
        /*vnp_Params.put("vnp_Bill_Address", req.getParameter("txt_inv_addr1"));
        vnp_Params.put("vnp_Bill_City", req.getParameter("txt_bill_city"));
        vnp_Params.put("vnp_Bill_Country", req.getParameter("txt_bill_country"));
        if (req.getParameter("txt_bill_state") != null && !req.getParameter("txt_bill_state").isEmpty()) {
            vnp_Params.put("vnp_Bill_State", req.getParameter("txt_bill_state"));
        }
        // Invoice
        vnp_Params.put("vnp_Inv_Phone", req.getParameter("txt_inv_mobile"));
        vnp_Params.put("vnp_Inv_Email", req.getParameter("txt_inv_email"));
        vnp_Params.put("vnp_Inv_Customer", req.getParameter("txt_inv_customer"));
        vnp_Params.put("vnp_Inv_Address", req.getParameter("txt_inv_addr1"));
        vnp_Params.put("vnp_Inv_Company", req.getParameter("txt_inv_company"));
        vnp_Params.put("vnp_Inv_Taxcode", req.getParameter("txt_inv_taxcode"));
        vnp_Params.put("vnp_Inv_Type", req.getParameter("cbo_inv_type"));*/
        //Build data to hash and querystring
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPAYUtils.hmacSHA512(VNPAY.VNPAY_HASH_SECRET.getValue(), hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAY.VNPAY_URL.getValue() + "?" + queryUrl;

        return Result.result(HttpStatus.OK.value(), "success", paymentUrl);
    }

    @GetMapping("payment-info")
    public Result<?> transactionHandle(){
        return Result.result(HttpStatus.OK.value(), "success", "ok");
    }
}
