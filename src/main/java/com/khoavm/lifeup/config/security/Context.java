package com.khoavm.lifeup.config.security;

import com.khoavm.lifeup.module.common.dto.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface Context {
    UUID getUserId();
    String getUsername();
    String getTraceId();
    Integer getOffset();
    Integer getPage();

    Integer getLimit();

    Pageable getPageable();

    List<Query> getQuery();

    Sort getSort();
}
