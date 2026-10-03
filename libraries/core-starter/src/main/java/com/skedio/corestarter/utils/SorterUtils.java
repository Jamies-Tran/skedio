package com.skedio.corestarter.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class SorterUtils {
    public static Sort handleSorter(String sorter) {
        if(!StringUtils.hasText(sorter)) {
            return Sort.unsorted();
        }
        String[] split = sorter.split("_");
        if(split.length == 1) {
            return Sort.by(Sort.Direction.ASC, split[0]);
        }
        if(Objects.equals(split[1], "asc")) {
            return Sort.by(Sort.Direction.ASC, split[0]);
        }
        if(Objects.equals(split[1], "desc")) {
            return Sort.by(Sort.Direction.DESC, split[0]);
        }
        return Sort.by(Sort.Direction.ASC, split[0]);
    }
}
