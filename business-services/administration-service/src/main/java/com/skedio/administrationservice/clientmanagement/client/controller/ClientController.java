package com.skedio.administrationservice.clientmanagement.client.controller;

import com.skedio.administrationservice.clientmanagement.client.controller.models.*;
import com.skedio.administrationservice.clientmanagement.client.domain.Client;
import com.skedio.administrationservice.clientmanagement.client.domain.argument.*;
import com.skedio.administrationservice.clientmanagement.client.service.command.*;
import com.skedio.administrationservice.clientmanagement.client.service.query.ClientGetDetailUseCase;
import com.skedio.administrationservice.clientmanagement.client.service.query.ClientSearchPageUseCase;
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
@RequestMapping("/v1/api/clients")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClientController {
    ClientCreateListUseCase createListUseCase;
    ClientSearchPageUseCase searchPageUseCase;
    ClientGetDetailUseCase getDetailUseCase;
    ClientUpdateUseCase updateUseCase;
    ClientEnableUseCase enableUseCase;
    ClientDisableUseCase disableUseCase;
    ClientDeleteUseCase deleteUseCase;
    ClientDeleteInApplicationUseCase deleteInApplicationUseCase;
    ClientResponseMapper responseMapper;

    @PostMapping("/create-list")
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<List<ClientResponse>> createList(@RequestParam UUID applicationUuid, @RequestBody @Validated ClientCreateListRequest request) {
        List<ClientCreateRequest> createRequests = request.createList();
        ClientCreateList createList = ClientCreateList.from(applicationUuid, createRequests);
        List<Client> clients  = createListUseCase.handle(createList);
        List<ClientResponse> responses = responseMapper.toDto(clients);
        return ApiResponse.list(responses);
    }

    @GetMapping("/in-application/{applicationUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<ClientResponse>> findAll(
            @PathVariable UUID applicationUuid,
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") Boolean enabled,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, defaultValue = "updatedAt_desc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        ClientSearch clientSearch = new ClientSearch(applicationUuid, search, enabled, timeRange, sorter, current, pageSize);
        Page<Client> clients = searchPageUseCase.handle(clientSearch);
        Page<ClientResponse> responses = clients.map(responseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @GetMapping("/{clientUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ClientResponse> findByClientUuid(@PathVariable UUID clientUuid) {
        ClientUuid uuid = new ClientUuid(clientUuid);
        Void find = getDetailUseCase.handle(uuid);
        Client client = (Client) find.data();
        ClientResponse response = responseMapper.toDto(client);
        return ApiResponse.value(response);
    }

    @PutMapping("/{clientUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ClientResponse> update(@PathVariable UUID clientUuid, @RequestBody ClientUpdateRequest request) {
        ClientUpdate update = ClientUpdate.from(clientUuid, request);
        Void handle = updateUseCase.handle(update);
        Client client = (Client) handle.data();
        ClientResponse response = responseMapper.toDto(client);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{clientUuid}/enable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ClientResponse> enable(@PathVariable UUID clientUuid) {
        ClientUuid uuid = new ClientUuid(clientUuid);
        Void handle = enableUseCase.handle(uuid);
        Client enabled = (Client) handle.data();
        ClientResponse response = responseMapper.toDto(enabled);
        return ApiResponse.value(response);
    }

    @PatchMapping("/{clientUuid}/disable")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<ClientResponse> disable(@PathVariable UUID clientUuid) {
        ClientUuid uuid = new ClientUuid(clientUuid);
        Void handle = disableUseCase.handle(uuid);
        Client disabled = (Client) handle.data();
        ClientResponse response = responseMapper.toDto(disabled);
        return ApiResponse.value(response);
    }

    @DeleteMapping("/{clientUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByClientUuid(@PathVariable UUID clientUuid) {
        ClientUuid uuid = new ClientUuid(clientUuid);
        deleteUseCase.handle(uuid);
    }

    @DeleteMapping("/all-in-application/{applicationUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInApplication(@PathVariable UUID applicationUuid) {
        ClientDeleteAllByApplicationUuid deleteAllByApplicationUuid = new ClientDeleteAllByApplicationUuid(applicationUuid);
        deleteInApplicationUseCase.handle(deleteAllByApplicationUuid);
    }
}
