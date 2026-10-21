package com.skedio.administrationservice.applicationmanagement.applications.controller;

import com.skedio.administrationservice.applicationmanagement.applications.controller.models.*;
import com.skedio.administrationservice.applicationmanagement.applications.domain.ApplicationGroup;
import com.skedio.administrationservice.applicationmanagement.applications.domain.Client;
import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.*;
import com.skedio.administrationservice.applicationmanagement.applications.service.command.*;
import com.skedio.administrationservice.applicationmanagement.applications.service.query.ApplicationGroupGetDetailUseCase;
import com.skedio.administrationservice.applicationmanagement.applications.service.query.ApplicationGroupSearchPageUseCase;
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
@RequestMapping("/v1/api/applications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationGroupController {
    ApplicationGroupCreateListUseCase createListUseCase;
    ApplicationGroupSearchPageUseCase searchPageUseCase;
    ApplicationGroupGetDetailUseCase getDetailUseCase;
    ApplicationGroupUpdateUseCase updateUseCase;
    ApplicationGroupEnableUseCase enableUseCase;
    ApplicationGroupDisableUseCase disableUseCase;
    ApplicationGroupDeleteUseCase deleteUseCase;
    ApplicationGroupResponseMapper responseMapper;
    ClientResponseMapper clientResponseMapper;

    @PostMapping("/create-list")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<List<ApplicationGroupResponse>> createList(@RequestBody @Validated ApplicationGroupRequestList requestList) {
        List<ApplicationGroupRequest> applicationGroupRequest = requestList.applicationGroups();
        List<ApplicationGroupCreate> applicationGroupCreates = ApplicationGroupCreate.from(applicationGroupRequest);
        ApplicationGroupCreateList applicationGroupCreateList = new ApplicationGroupCreateList(applicationGroupCreates);
        List<ApplicationGroup> saved  = createListUseCase.handle(applicationGroupCreateList);
        List<ApplicationGroupResponse> responses = responseMapper.toDto(saved);
        return ApiResponse.list(responses);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<ApplicationGroupResponse>> findAll(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "true") Boolean enabled,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, defaultValue = "updatedAt_desc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ApplicationGroupSearch groupSearch = new ApplicationGroupSearch(search, enabled, timeRange, sorter, current, pageSize);
        Page<ApplicationGroup> page = searchPageUseCase.handle(groupSearch);
        Page<ApplicationGroupResponse> responses = page.map(responseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @GetMapping("/get-by-uuid/{applicationUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ApplicationGroupResponse> getDetail(@PathVariable UUID applicationUuid) {
        ApplicationGroupUuid applicationGroupUuid = new ApplicationGroupUuid(applicationUuid);
        Void found = getDetailUseCase.handle(applicationGroupUuid);
        ApplicationGroup applicationGroup = (ApplicationGroup)  found.data();
        ApplicationGroupResponse response = responseMapper.toDto(applicationGroup);
        return ApiResponse.value(response);
    }

    @PutMapping("/{applicationGroupUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ApplicationGroupResponse> update(@PathVariable UUID applicationGroupUuid, @RequestBody ApplicationGroupUpdateRequest request) {
        ApplicationGroupUpdate command = ApplicationGroupUpdate.from(applicationGroupUuid, request);
        Void update = updateUseCase.handle(command);
        ApplicationGroup applicationGroup = (ApplicationGroup)  update.data();
        ApplicationGroupResponse response = responseMapper.toDto(applicationGroup);
        return ApiResponse.value(response);
    }

    @PatchMapping("/enable/{applicationGroupUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ApplicationGroupResponse> enable(@PathVariable UUID applicationGroupUuid) {
        ApplicationGroupUuid uuid = new ApplicationGroupUuid(applicationGroupUuid);
        Void enabled = enableUseCase.handle(uuid);
        ApplicationGroup applicationGroup = (ApplicationGroup)  enabled.data();
        ApplicationGroupResponse response = responseMapper.toDto(applicationGroup);
        return ApiResponse.value(response);
    }

    @PatchMapping("/disable/{applicationGroupUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ApplicationGroupResponse> disable(@PathVariable UUID applicationGroupUuid) {
        ApplicationGroupUuid uuid = new ApplicationGroupUuid(applicationGroupUuid);
        Void disabled = disableUseCase.handle(uuid);
        ApplicationGroup applicationGroup = (ApplicationGroup)  disabled.data();
        ApplicationGroupResponse response = responseMapper.toDto(applicationGroup);
        return ApiResponse.value(response);
    }

    @DeleteMapping("/{applicationGroupUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID applicationGroupUuid) {
        ApplicationGroupUuid uuid = new ApplicationGroupUuid(applicationGroupUuid);
        deleteUseCase.handle(uuid);
    }
}
