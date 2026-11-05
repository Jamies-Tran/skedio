package com.skedio.administrationservice.permissionmanagement.permissions.controller;

import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionCreateRequest;
import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionResponse;
import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionResponseMapper;
import com.skedio.administrationservice.permissionmanagement.permissions.controller.models.PermissionUpdateRequest;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionCreate;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionSearch;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionUpdate;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionUuid;
import com.skedio.administrationservice.permissionmanagement.permissions.service.command.*;
import com.skedio.administrationservice.permissionmanagement.permissions.service.query.PermissionGetDetailUseCase;
import com.skedio.administrationservice.permissionmanagement.permissions.service.query.PermissionSearchPageUseCase;
import com.skedio.corestarter.template.ApiResponse;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionCreateUseCase createUseCase;
    PermissionUpdateUseCase updateUseCase;
    PermissionSearchPageUseCase searchPageUseCase;
    PermissionGetDetailUseCase getDetailUseCase;
    PermissionEnableUseCase enableUseCase;
    PermissionDisableUseCase disableUseCase;
    PermissionDeleteUseCase deleteUseCase;
    PermissionResponseMapper responseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<PermissionResponse> create(@RequestBody @Validated PermissionCreateRequest request) {
        PermissionCreate command = PermissionCreate.from(request);
        Void handle = createUseCase.handle(command);
        Permission permission = (Permission) handle.data();
        PermissionResponse response = responseMapper.toDto(permission);
        return ApiResponse.value(response);
    }

    @PutMapping("/{permissionUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PermissionResponse> update(@PathVariable UUID permissionUuid, @RequestBody @Validated PermissionUpdateRequest request) {
        PermissionUpdate command = PermissionUpdate.from(permissionUuid, request);
        Void handle = updateUseCase.handle(command);
        Permission permission = (Permission) handle.data();
        PermissionResponse response = responseMapper.toDto(permission);
        return ApiResponse.value(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<PermissionResponse>> findAll(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, defaultValue = "updatedAt_desc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        PermissionSearch query = new PermissionSearch(search, timeRange, enabled, sorter, current, pageSize);
        Page<Permission> permissions = searchPageUseCase.handle(query);
        Page<PermissionResponse> responses = permissions.map(responseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @GetMapping("/{permissionUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PermissionResponse> getDetail(@PathVariable UUID permissionUuid) {
        PermissionUuid query = new PermissionUuid(permissionUuid);
        Void handle = getDetailUseCase.handle(query);
        Permission permission = (Permission) handle.data();
        PermissionResponse response = responseMapper.toDto(permission);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{permissionUuid}/enable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PermissionResponse> enable(@PathVariable UUID permissionUuid) {
        PermissionUuid command = new PermissionUuid(permissionUuid);
        Void handle = enableUseCase.handle(command);
        Permission permission = (Permission) handle.data();
        PermissionResponse response = responseMapper.toDto(permission);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{permissionUuid}/disable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<PermissionResponse> disable(@PathVariable UUID permissionUuid) {
        PermissionUuid command = new PermissionUuid(permissionUuid);
        Void handle = disableUseCase.handle(command);
        Permission permission = (Permission) handle.data();
        PermissionResponse response = responseMapper.toDto(permission);
        return ApiResponse.value(response);
    }

    @DeleteMapping("/{permissionUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByPermissionUuid(@PathVariable UUID permissionUuid) {
        PermissionUuid command = new PermissionUuid(permissionUuid);
        deleteUseCase.handle(command);
    }
}
