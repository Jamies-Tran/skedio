package com.skedio.administrationservice.clientmanagement.references.clientuser.controller;

import com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models.ClientUserListRequest;
import com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models.UserResponse;
import com.skedio.administrationservice.clientmanagement.references.clientuser.controller.models.UserResponseMapper;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUserAssign;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUuid;
import com.skedio.administrationservice.clientmanagement.references.clientuser.service.command.ClientAssignUserListUseCase;
import com.skedio.administrationservice.clientmanagement.references.clientuser.service.command.ClientClearUserUseCase;
import com.skedio.administrationservice.clientmanagement.references.clientuser.service.query.ClientSearchUserUseCase;
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
@RequestMapping("/v1/api/clients/client-users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClClientUserController {
    ClientAssignUserListUseCase assignUserListUseCase;
    ClientSearchUserUseCase searchUserUseCase;
    ClientClearUserUseCase clearUserUseCase;
    UserResponseMapper userResponseMapper;

    @PostMapping("/{clientUuid}/assign-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void assignUserList(@PathVariable UUID clientUuid, @RequestBody @Validated ClientUserListRequest request) {
        ClientUserAssign command = ClientUserAssign.from(clientUuid, request);
        assignUserListUseCase.handle(command);
    }

    @GetMapping("/{clientUuid}/get-users")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<UserResponse>> findUserInClient(
            @PathVariable UUID clientUuid,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "username_asc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ClientUserSearch clientUserSearch = new ClientUserSearch(clientUuid, search, sorter, current, pageSize);
        Page<ClClientUser> clientUsers = searchUserUseCase.handle(clientUserSearch);
        Page<UserResponse> responses = clientUsers.map(userResponseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @DeleteMapping("/{clientUuid}/clear-users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearUser(@PathVariable UUID clientUuid) {
        ClientUuid command = new ClientUuid(clientUuid);
        clearUserUseCase.handle(command);
    }
}
