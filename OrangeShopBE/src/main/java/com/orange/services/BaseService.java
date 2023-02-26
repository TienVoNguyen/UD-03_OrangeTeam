package com.orange.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T, ID>{

    @Async
    @Transactional
    T create(T dto);
    @Async
    @Transactional
    T update(T dto);
    @Async
    @Transactional
    T delete(T dto);

    /**
     "fillAll" method retrieves all orders as a page of a generic object type.
     The generic object type is specified by the caller using the wildcard "?" as a placeholder for the actual type.
     This allows the caller to specify a custom view type that is separate from the underlying entity type.
     @param pageable pagination information.
     @return a page of a generic object type, specified by the caller.
     */
    Page<?> fillAll(Pageable pageable);

    T fillById(ID id);
}
