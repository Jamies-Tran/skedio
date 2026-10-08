package com.skedio.corestarter.template;

import lombok.Builder;

@Builder
public record Void(
        Object data
) {
    public static Void of(Object data) {
        return new Void(data);
    }
}
