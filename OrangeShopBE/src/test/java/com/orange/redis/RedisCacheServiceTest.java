package com.orange.redis;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RedisCacheService.class})
@ExtendWith(SpringExtension.class)
class RedisCacheServiceTest {
    @Autowired
    private RedisCacheService redisCacheService;

    /**
     * Method under test: {@link RedisCacheService#hmGetValues(Object)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHmGetValues() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        Object hashKey = null;

        // Act
        List<Object> actualHmGetValuesResult = this.redisCacheService.hmGetValues(hashKey);

        // Assert
        // TODO: Add assertions on result
    }
}

