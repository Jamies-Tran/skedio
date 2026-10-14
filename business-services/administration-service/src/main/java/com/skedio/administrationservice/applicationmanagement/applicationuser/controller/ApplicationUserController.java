package com.skedio.administrationservice.applicationmanagement.applicationuser.controller;

import com.skedio.administrationservice.applicationmanagement.applications.domain.argument.ApplicationGroupUuid;
import com.skedio.administrationservice.applicationmanagement.applicationuser.controller.models.ApplicationGroupUserResponse;
import com.skedio.administrationservice.applicationmanagement.applicationuser.controller.models.ApplicationGroupUserResponseMapper;
import com.skedio.administrationservice.applicationmanagement.applicationuser.controller.models.ApplicationUserUuidListRequest;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationGroupGetUser;
import com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument.ApplicationUserAddList;
import com.skedio.administrationservice.applicationmanagement.applicationuser.service.command.ApplicationGroupAddUserListUseCase;
import com.skedio.administrationservice.applicationmanagement.applicationuser.service.command.ApplicationGroupClearUserUserCase;
import com.skedio.administrationservice.applicationmanagement.applicationuser.service.query.ApplicationGroupGetUserUseCase;
import com.skedio.corestarter.template.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/application-user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationUserController {
    ApplicationGroupGetUserUseCase getUserUseCase;
    ApplicationGroupClearUserUserCase clearUserUserCase;
    ApplicationGroupAddUserListUseCase addUserListUseCase;
    ApplicationGroupUserResponseMapper userResponseMapper;

    @GetMapping("/{applicationUuid}/get-users")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<ApplicationGroupUserResponse>> getUserInApplication(
            @PathVariable UUID applicationUuid,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "createdAt_asc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ApplicationGroupGetUser userSearch = ApplicationGroupGetUser.builder()
                .applicationUuid(applicationUuid)
                .search(search)
                .sorter(sorter)
                .current(current)
                .pageSize(pageSize)
                .build();
        Page<ApplicationGroupUserResponse> responses = getUserUseCase.handle(userSearch)
                .map(userResponseMapper::toDomain);
        return ApiResponse.page(responses);
    }

    @PostMapping("/{applicationUuid}/add-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void addUserListToApplication(@PathVariable UUID applicationUuid, @RequestBody @Validated ApplicationUserUuidListRequest request) {
        List<UUID> userUuids = request.userUuids();
        ApplicationUserAddList addList = new ApplicationUserAddList(applicationUuid, userUuids);
        addUserListUseCase.handle(addList);
    }

    @DeleteMapping("/{applicationUuid}/clear-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearUserInApplication(@PathVariable UUID applicationUuid) {
        ApplicationGroupUuid uuid = new ApplicationGroupUuid(applicationUuid);
        clearUserUserCase.handle(uuid);
    }
}
