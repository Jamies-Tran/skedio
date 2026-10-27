package com.skedio.administrationservice.clientmanagement.clientuser.controller;

import com.skedio.administrationservice.clientmanagement.clientuser.controller.models.ClientUserListRequest;
import com.skedio.administrationservice.clientmanagement.clientuser.controller.models.UserResponse;
import com.skedio.administrationservice.clientmanagement.clientuser.controller.models.UserResponseMapper;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserAssign;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.clientuser.service.command.ClientAssignUserListUseCase;
import com.skedio.administrationservice.clientmanagement.clientuser.service.query.ClientSearchUserUseCase;
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
@RequestMapping("/v1/api/client-users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientUserController {
    ClientAssignUserListUseCase assignUserListUseCase;
    ClientSearchUserUseCase searchUserUseCase;
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
        Page<ClientUser> clientUsers = searchUserUseCase.handle(clientUserSearch);
        Page<UserResponse> responses = clientUsers.map(userResponseMapper::toDto);
        return ApiResponse.page(responses);
    }
}
