package com.prediction_crime.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtils {
    public static <T, U> Page<U> mapPage(Page<T> page, Function<T, U> mapper) {
        List<U> content = page.getContent().stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}