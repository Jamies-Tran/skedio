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
        String traceId,
        Boolean isSuccess,
        String message,
        LocalDateTime responseAt,
        String errorCode,
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
            return null;
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

    public static <T> ApiResponse<List<T>> list(List<T> data) {
        Metadata metadata = Metadata.ofList(data);
        return ApiResponse.<List<T>>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .message("success")
                .traceId(MDC.get("traceId"))
                .metadata(metadata)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<List<T>> page(Page<T> data) {
        Metadata metadata = Metadata.ofPage(data);
        List<T> content = data.getContent();
        return ApiResponse.<List<T>>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .message("success")
                .metadata(metadata)
                .traceId(MDC.get("traceId"))
                .data(content)
                .build();
    }

    public static <T> ApiResponse<T> value(T data) {
        Metadata metadata = Metadata.ofEmpty();
        return ApiResponse.<T>builder()
                .responseAt(LocalDateTime.now())
                .isSuccess(true)
                .message("success")
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
                .errorCode(applicationException.getErrorCode().getCode())
                .message(applicationException.getMessage())
                .metadata(metadata)
                .traceId(MDC.get("traceId"))
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
