package com.khoavm.lifeup.module.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private int page;
    private int total;
    private int size;

    List<T> list;
}
