package com.skedio.corestarter.utils;

import org.springframework.util.StringUtils;

import java.text.Normalizer;

public class StringConvertUtils {
    public static String normalizeWhiteSpace(String source) {
        if(!StringUtils.hasText(source)) {
            return "";
        }
        String normalized = Normalizer.normalize(source, Normalizer.Form.NFD);
        return normalized
                .replaceAll(" ", "")
                .replaceAll("\\p{M}", "")
                .replace("đ", "d")
                .replace("Đ", "d")
                .toLowerCase();
    }
}
