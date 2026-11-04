package com.skedio.administrationservice.permissionmanagement.permission.controller;

import com.skedio.administrationservice.permissionmanagement.permission.controller.models.PermissionCreateRequest;
import com.skedio.administrationservice.permissionmanagement.permission.controller.models.PermissionResponse;
import com.skedio.administrationservice.permissionmanagement.permission.controller.models.PermissionResponseMapper;
import com.skedio.administrationservice.permissionmanagement.permission.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permission.domain.argument.PermissionCreate;
import com.skedio.administrationservice.permissionmanagement.permission.repository.PermissionRepository;
import com.skedio.administrationservice.permissionmanagement.permission.service.command.PermissionCreateUseCase;
import com.skedio.corestarter.template.ApiResponse;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/permissions")
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionCreateUseCase createUseCase;
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
}
