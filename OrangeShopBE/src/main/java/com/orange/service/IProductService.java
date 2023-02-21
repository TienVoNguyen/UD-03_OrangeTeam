package com.orange.service;

import org.springframework.data.domain.Page;

public interface IProductService {
     Page findAll(String pageParam, String limitParam);
}
