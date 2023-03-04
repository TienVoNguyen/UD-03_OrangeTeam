package com.orange.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.Utils.JsonUtils;
import com.orange.domain.dto.CartDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component

public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;
    private final static String HASH_KEY = "Cart_";
    public CartDTO save(CartDTO cartDTO) {
        String value = JsonUtils.toJson(cartDTO);
        redisTemplate.opsForHash().put(HASH_KEY + String.valueOf(cartDTO.getUserId()), String.valueOf(cartDTO.getCartId()), value);
        return cartDTO;
    }

    public List<Object> getAll(String key) {
        return redisTemplate.opsForHash().values(HASH_KEY + key);
    }

    public CartDTO findProductById(String key, int id) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        CartDTO cartDTO = null;
        try {
            jsonNode = objectMapper.readTree(redisTemplate.opsForHash().get(HASH_KEY + key, String.valueOf(id)).toString());
            cartDTO = objectMapper.treeToValue(jsonNode, CartDTO.class);
        } catch (Exception e){
            return null;
        }
        return cartDTO;
    }

    public String deleteCartProductbyId(String key, int id) {
        redisTemplate.opsForHash().delete(HASH_KEY + key, String.valueOf(id));
        return "Product remove !!";
    }


    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public void hdel(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }
}
