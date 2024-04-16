package com.khoavm.lifeup.module.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    private int page;
    private int total;
    private int size;

    List<T> list;

}
