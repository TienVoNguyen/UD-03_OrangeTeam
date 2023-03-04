package com.orange.services;

import com.orange.common.payload.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T, ID>{

    T create(T dto);
    T update(T dto);
    T delete(T dto);

    /**
     "fillAll" method retrieves all orders as a page of a generic object type.
     The generic object type is specified by the caller using the wildcard "?" as a placeholder for the actual type.
     This allows the caller to specify a custom view type that is separate from the underlying entity type.
     @param pageable pagination information.
     @return a page of a generic object type, specified by the caller.
     */
    Page<?> fillAll(Pageable pageable);

    T findById(ID id);
}
