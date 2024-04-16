package com.khoavm.lifeup.module.common.mapper;

import com.khoavm.lifeup.module.common.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


@Component
public class Mapper {
    public <T> PageDto<T> pageToDto(Page<T> page) {
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setPage(page.getPageable().getPageNumber());
        pageDto.setSize(page.getPageable().getPageSize());
        pageDto.setTotal(page.getTotalPages());
        pageDto.setList(page.stream().toList());
        return pageDto;
    }
}
