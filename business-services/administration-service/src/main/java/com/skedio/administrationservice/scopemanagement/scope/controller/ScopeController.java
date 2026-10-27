package com.skedio.administrationservice.scopemanagement.scope.controller;

import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeCreateRequest;
import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeResponse;
import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeResponseMapper;
import com.skedio.administrationservice.scopemanagement.scope.controller.models.ScopeUpdateRequest;
import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeCreate;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeSearch;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUpdate;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeUuid;
import com.skedio.administrationservice.scopemanagement.scope.service.command.*;
import com.skedio.administrationservice.scopemanagement.scope.service.query.ScopeGetDetailUseCase;
import com.skedio.administrationservice.scopemanagement.scope.service.query.ScopeSearchPageUseCase;
import com.skedio.corestarter.template.ApiResponse;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/scopes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeController {
    ScopeCreateUseCase createUseCase;
    ScopeSearchPageUseCase searchPageUseCase;
    ScopeGetDetailUseCase getDetailUseCase;
    ScopeEnableUseCase enableUseCase;
    ScopeDisableUseCase disableUseCase;
    ScopeUpdateUseCase updateUseCase;
    ScopeDeleteUseCase deleteUseCase;
    ScopeResponseMapper responseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<ScopeResponse> create(@RequestBody @Validated ScopeCreateRequest request) {
        ScopeCreate command = ScopeCreate.from(request);
        Void handle = createUseCase.handle(command);
        Scope scope = (Scope) handle.data();
        ScopeResponse response = responseMapper.toDomain(scope);
        return ApiResponse.value(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<ScopeResponse>> findAll(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, defaultValue = "updatedAt_desc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ScopeSearch scopeSearch = new ScopeSearch(search, timeRange, sorter, current, pageSize);
        Page<Scope> scopes = searchPageUseCase.handle(scopeSearch);
        Page<ScopeResponse> response = scopes.map(responseMapper::toDomain);
        return ApiResponse.page(response);
    }

    @GetMapping("/{scopeUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ScopeResponse> findByUuid(@PathVariable UUID scopeUuid) {
        ScopeUuid uuid = new ScopeUuid(scopeUuid);
        Void handle = getDetailUseCase.handle(uuid);
        Scope scope = (Scope) handle.data();
        ScopeResponse response = responseMapper.toDomain(scope);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{scopeUuid}/enable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ScopeResponse> enabled(@PathVariable UUID scopeUuid) {
        ScopeUuid uuid = new ScopeUuid(scopeUuid);
        Void handle = enableUseCase.handle(uuid);
        Scope scope = (Scope) handle.data();
        ScopeResponse response = responseMapper.toDomain(scope);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{scopeUuid}/disable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ScopeResponse> disable(@PathVariable UUID scopeUuid) {
        ScopeUuid uuid = new ScopeUuid(scopeUuid);
        Void handle = disableUseCase.handle(uuid);
        Scope scope = (Scope) handle.data();
        ScopeResponse response = responseMapper.toDomain(scope);
        return ApiResponse.value(response);
    }

    @PutMapping("/{scopeUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ScopeResponse> update(@PathVariable UUID scopeUuid, @RequestBody @Validated ScopeUpdateRequest request) {
        ScopeUpdate command = ScopeUpdate.from(scopeUuid, request);
        Void handle = updateUseCase.handle(command);
        Scope scope = (Scope) handle.data();
        ScopeResponse response = responseMapper.toDomain(scope);
        return ApiResponse.value(response);
    }

    @DeleteMapping("/{scopeUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID scopeUuid) {
        ScopeUuid uuid = new ScopeUuid(scopeUuid);
        deleteUseCase.handle(uuid);
    }
}
