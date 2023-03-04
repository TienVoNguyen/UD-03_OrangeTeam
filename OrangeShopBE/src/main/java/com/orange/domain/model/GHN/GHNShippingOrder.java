package com.orange.domain.model.GHN;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * from_name: Tên người gửi hàng.
 * <p>
 * from_phone: Số điện thoại người gửi hàng.
 * <p>
 * from_address: Địa chỉ Shipper tới lấy hàng.
 * <p>
 * from_ward_name: Phường/Xã của người gửi hàng.
 * <p>
 * from_district_name: Quận/Huyện của người gửi hàng.
 * <p>
 * from_province_name: Tỉnh/Thành của người gửi hàng.
 * <p>
 * to_name: Tên người nhận hàng.
 * <p>
 * to_phone: Số điện thoại người nhận hàng.
 * <p>
 * to_address: Địa chỉ Shiper tới giao hàng.
 * <p>
 * to_ward_name: Phường/Xã của người nhận hàng.
 * <p>
 * to_district_name: Quận/Huyện của người nhận hàng.
 * <p>
 * to_province_name: Tỉnh/Thành của người gửi hàng.
 * <p>
 * return_name: Tên người trả hàng khi không giao được.
 * <p>
 * return_phone: Số điện thoại trả hàng khi không giao được.
 * <p>
 * return_address: Địa chỉ trả hàng khi không giao được.
 * <p>
 * return_district_name: Quận/Huyện của người nhận hàng trả.
 * <p>
 * return_ward_name: Phường/Xã của người nhận hàng trả.
 * <p>
 * return_province_name: Tỉnh/Thành của người nhận hàng trả.
 * <p>
 * client_order_code: Mã đơn hàng riêng của khách hàng.
 * <p>
 * Giá trị mặc định: Lưu ý : Client_order_code thì sẽ lấy lại đơn hàng đã có Client_order_code này
 * <p>
 * cod_amount: Tiền thu hộ cho người gửi. Maximum :10.000.000. Giá trị mặc định: 0
 * <p>
 * content: Nội dung của đơn hàng.
 * <p>
 * weight: Khối lượng của đơn hàng (gram). Tối đa : 30000 gram
 * <p>
 * length: Chiều dài của đơn hàng (cm). Tối đa : 150 cm
 * <p>
 * width: Chiều rộng của đơn hàng (cm). Tối đa : 150 cm
 * <p>
 * height: Chiều cao của đơn hàng (cm). Tối đa : 150 cm
 * <p>
 * pick_station_id: Mã bưu cục để gửi hàng tại điểm.
 * insurance_value: Giá trị của đơn hàng ( Trường hợp mất hàng , bể hàng sẽ đền theo giá trị của đơn hàng). Tối đa 5.000.000
 * <p>
 * coupon: Mã giảm giá.
 * <p>
 * service_type_id: Mã loại dịch vụ: Gọi API lấy gói dịch vụ để lấy mã loại dịch vụ. Mã loại dịch vụ cố định. Trong đó:   2:Chuyển phát thương mại điện tử. Lưu ý: Nếu đã truyền service_id thì không cần truyền service_type_id .
 * <p>
 * service_id: Để lấy thông tin chính xác từng dịch vụ phù hợp với tuyến đường nên gọi API lấy gói dịch vụ
 * <p>
 * payment_type_id: Mã người thanh toán phí dịch vụ. 1: Người bán/Người gửi. 2: Người mua/Người nhận.
 * <p>
 * note: Người gửi ghi chú cho tài xế.
 * <p>
 * required_note: Ghi chú bắt buộc, Bao gồm: CHOTHUHANG, CHOXEMHANGKHONGTHU, KHONGCHOXEMHANG
 * <p>
 * pick_shift	: Dùng để truyền ca lấy hàng , Sử dụng API Lấy danh sách ca lấy
 * <p>
 * pickup_time: Dùng để hẹn ngày Lấy hàng. Có thể để: null
 * <p>
 * Items: Thông tin sản phẩm.
 * <p>
 * name: Tên của sản phẩm.
 * <p>
 * code: Mã của sản phẩm.
 * <p>
 * quantity: Số lượng của sản phẩm.
 * <p>
 * price: Giá của sản phẩm.
 * <p>
 * length: Chiều dài của sản phẩm.
 * <p>
 * width: Chiều rộng của sản phẩm.
 * <p>
 * height: Chiều cao của sản phẩm.
 * <p>
 * category: Danh mục sản phẩm được phân chia 3 cấp độlevel1, level2, level3
 */
@Data
public class GHNShippingOrder {

    @NotNull
    @JsonProperty("payment_type_id")
    private int paymentTypeId;

    @JsonProperty("note")
    private String note;

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("from_phone")
    private String fromPhone;

    @JsonProperty("from_address")
    private String fromAddress;

    @JsonProperty("from_ward_name")
    private String fromWardName;

    @JsonProperty("from_district_name")
    private String fromDistrictName;

    @JsonProperty("from_province_name")
    private String fromProvinceName;

    @NotNull
    @JsonProperty("required_note")
    private String requiredNote;

    @JsonProperty("return_name")
    private String returnName;

    @JsonProperty("return_phone")
    private String returnPhone;

    @JsonProperty("return_address")
    private String returnAddress;

    @JsonProperty("return_ward_name")
    private String returnWardName;

    @JsonProperty("return_district_name")
    private String returnDistrictName;

    @JsonProperty("return_province_name")
    private String returnProvinceName;

    @JsonProperty("client_order_code")
    private String clientOrderCode;

    @NotNull
    @JsonProperty("to_name")
    private String toName;

    @NotNull
    @JsonProperty("to_phone")
    private String toPhone;

    @NotNull
    @JsonProperty("to_address")
    private String toAddress;

    @NotNull
    @JsonProperty("to_ward_name")
    private String toWardName;

    @NotNull
    @JsonProperty("to_district_name")
    private String toDistrictName;

    @NotNull
    @JsonProperty("to_province_name")
    private String toProvinceName;

    @JsonProperty("cod_amount")
    private int codAmount;

    @JsonProperty("content")
    private String content;

    @NotNull
    @JsonProperty("weight")
    private int weight;

    @NotNull
    @JsonProperty("length")
    private int length;

    @NotNull
    @JsonProperty("width")
    private int width;

    @NotNull
    @JsonProperty("height")
    private int height;

    @JsonProperty("pick_station_id")
    private int pickStationId;

    @JsonProperty("deliver_station_id")
    private Integer deliverStationId;

    @JsonProperty("insurance_value")
    private int insuranceValue;

    @NotNull
    @JsonProperty("service_id")
    private int serviceId;

    @NotNull
    @JsonProperty("service_type_id")
    private int serviceTypeId;

    @JsonProperty("coupon")
    private String coupon;

    @JsonProperty("pick_shift")
    private String pickShift;

    @JsonProperty("pickup_time")
    private long pickupTime;

    @NotNull
    @JsonProperty("items")
    private List<GHNItem> items;
}
