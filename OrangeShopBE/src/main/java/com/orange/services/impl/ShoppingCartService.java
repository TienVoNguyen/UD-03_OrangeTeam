package com.orange.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.Utils.AccountUtils;
import com.orange.Utils.JsonUtils;
import com.orange.domain.dto.CartDTO;
import com.orange.domain.model.ProductDetail;
import com.orange.exception.EntityType;
import com.orange.exception.ExceptionType;
import com.orange.exception.GlobalException;
import com.orange.redis.CacheService;
import com.orange.repositories.IProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {

    private final static String HASH_KEY = "Cart_";
    private final static Long TIMEOUT = 30L;
    private final static TimeUnit timeUnit = TimeUnit.DAYS;
    private final CacheService cacheService;
    private final IProductDetailRepository productDetailRepository;

    private CartDTO save(CartDTO newCart) {
        String value = JsonUtils.toJson(newCart);
        cacheService.hmSetWithExpire(HASH_KEY + AccountUtils.getUsername(), String.valueOf(newCart.getProductDetailId()), value, TIMEOUT, TimeUnit.DAYS);
        return newCart;
    }

    public CartDTO add(CartDTO newCart) {
        CartDTO oldCart = findCartItemById(newCart.getProductDetailId());
        if (oldCart != null) {
            newCart.setQuantity(oldCart.getQuantity() + newCart.getQuantity());
        }
        return save(newCart);
    }

    public CartDTO update(Long id, Integer quantity) {
        Optional<CartDTO> oldCart = Optional.ofNullable(findCartItemById(id));
        if (oldCart.isPresent()) {
            CartDTO newCart = oldCart.get();
            newCart.setQuantity(quantity);
            return save(newCart);
        } else {
            throw GlobalException.throwException(EntityType.product,
                    ExceptionType.ENTITY_NOT_FOUND,
                    "Không có sản phẩm này trong giảo hàng");
        }
    }

    public List<CartDTO> getAll(String key) {
        List<CartDTO> carts = cacheService.hmGetValues(HASH_KEY + key).stream()
                .map(c -> {
                    try {
                        return JsonUtils.fromJson(c.toString(), CartDTO.class);
                    } catch (JsonProcessingException e) {
                        throw GlobalException.throwException(EntityType.product,
                                ExceptionType.ENTITY_NOT_FOUND,
                                "Có lỗi sảy ra khi lấy cart từ redis server!");
                    }
                }).collect(Collectors.toList());

        List<Long> ids = carts.stream().map(CartDTO::getProductDetailId).toList();

        Map<Long, Double> productsPrice = this.productDetailRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(ProductDetail::getId, ProductDetail::getPriceSale));
        for (CartDTO cart : carts) {
            Double price = productsPrice.get(cart.getProductDetailId());
            if (price != null) {
                cart.setPrice(price);
            }
        }
        return carts;
    }

    public CartDTO findCartItemById(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        CartDTO cartDTO = null;
        String key = String.valueOf(id);
        try {
            cartDTO = JsonUtils.fromJson(cacheService.hmGet(HASH_KEY + AccountUtils.getUsername(), key).toString(), CartDTO.class);
        } catch (Exception e) {
            return null;
        }
        return cartDTO;
    }

    public String deleteCartItemById(Long id) {
        cacheService.hmDelete(HASH_KEY + AccountUtils.getUsername(), String.valueOf(id));
        return "Product remove !!";
    }
}
