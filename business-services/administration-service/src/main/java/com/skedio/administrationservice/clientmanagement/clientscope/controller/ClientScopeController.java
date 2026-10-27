package com.skedio.administrationservice.clientmanagement.clientscope.controller;

import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeAssignListRequest;
import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeAssignRequest;
import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeResponse;
import com.skedio.administrationservice.clientmanagement.clientscope.controller.models.ClientScopeResponseMapper;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.ClientScope;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeAssignList;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientScopeSearch;
import com.skedio.administrationservice.clientmanagement.clientscope.domain.argument.ClientUuid;
import com.skedio.administrationservice.clientmanagement.clientscope.service.command.ClientClearScopeUseCase;
import com.skedio.administrationservice.clientmanagement.clientscope.service.command.ClientScopeAssignListUseCase;
import com.skedio.administrationservice.clientmanagement.clientscope.service.query.ClientScopeSearchScopeInClientUseCase;
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
@RequestMapping("/v1/api/client-scope")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientScopeController {
    ClientScopeAssignListUseCase assignListUseCase;
    ClientScopeSearchScopeInClientUseCase searchScopeInClientUseCase;
    ClientClearScopeUseCase clientClearScopeUseCase;
    ClientScopeResponseMapper responseMapper;

    @PostMapping("/{clientUuid}/add-scopes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void assignList(@PathVariable UUID clientUuid, @RequestBody @Validated ClientScopeAssignListRequest request) {
        List<ClientScopeAssignRequest> assignRequests = request.assigns();
        ClientScopeAssignList assignList = ClientScopeAssignList.from(clientUuid, assignRequests);
        assignListUseCase.handle(assignList);
    }

    @GetMapping("/{clientUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<ClientScopeResponse>> findAll(
            @PathVariable UUID clientUuid,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "scopeName_asc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ClientScopeSearch clientScopeSearch = new ClientScopeSearch(clientUuid, search, sorter, current, pageSize);
        Page<ClientScope> clientScopes = searchScopeInClientUseCase.handle(clientScopeSearch);
        Page<ClientScopeResponse> responses = clientScopes.map(responseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @DeleteMapping("/{clientUuid}/clear-scopes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearScope(@PathVariable UUID clientUuid) {
        ClientUuid uuid = new ClientUuid(clientUuid);
        clientClearScopeUseCase.handle(uuid);
    }
}
