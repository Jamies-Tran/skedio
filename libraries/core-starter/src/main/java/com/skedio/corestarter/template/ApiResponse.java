package com.skedio.corestarter.template;

import com.skedio.corestarter.advice.ApplicationException;
import lombok.Builder;
import org.slf4j.MDC;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Builder
public record ApiResponse<T>(
        LocalDateTime responseAt,
        Boolean isSuccess,
        String errorCode,
        String traceId,
        Metadata metadata,
        T data
) {

    @Builder
    public record Metadata(
            Integer current,
            Integer pageSize,
            Long totalElements,
            Integer totalPages
    ) {
        public static Metadata ofEmpty() {
            return Metadata.builder().build();
        }

        public static Metadata ofList(List<?> data) {
            return Metadata.builder()
                    .current(0)
                    .pageSize(data.size())
                    .totalElements((long) data.size())
                    .totalPages(1)
                    .build();
        }

        public static Metadata ofPage(Page<?> data) {
            return Metadata.builder()
                    .current(data.getNumber())
                    .pageSize(data.getSize())
                    .totalElements(data.getTotalElements())
                    .totalPages(data.getTotalPages())
                    .build();
        }
    }

    public static <T> ApiResponse<T> list(T data) {
        if (!(data instanceof List<?>)) {
            throw new RuntimeException("data is not a list");
        }
        Metadata metadata = Metadata.ofList((List<?>) data);

        return ApiResponse.<T>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .traceId(MDC.get("traceId"))
                .metadata(metadata)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> page(T data) {
        if (!(data instanceof Page<?>)) {
            throw new RuntimeException("data is not a page");
        }
        Metadata metadata = Metadata.ofPage((Page<?>) data);

        return ApiResponse.<T>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .metadata(metadata)
                .traceId(MDC.get("traceId"))
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> value(T data) {
        if (data instanceof Collection<?>) {
            throw new RuntimeException("data is not valid");
        }
        Metadata metadata = Metadata.ofPage((Page<?>) data);
        return ApiResponse.<T>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .metadata(metadata)
                .traceId(MDC.get("traceId"))
                .data(data)
                .build();
    }

    public static ApiResponse<String> ofApplicationException(ApplicationException applicationException) {
        Metadata metadata = Metadata.ofEmpty();
        return ApiResponse.<String>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(false)
                .metadata(metadata)
                .traceId(MDC.get("traceId"))
                .data(applicationException.getMessage())
                .build();
    }

    public static ApiResponse<List<String>> ofListException(List<String> errors) {
        Metadata metadata = Metadata.ofEmpty();
        return ApiResponse.<List<String>>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(false)
                .traceId(MDC.get("traceId"))
                .metadata(metadata)
                .data(errors)
                .build();
    }
}
