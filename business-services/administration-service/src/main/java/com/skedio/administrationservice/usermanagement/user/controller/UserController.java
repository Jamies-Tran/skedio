package com.skedio.administrationservice.usermanagement.user.controller;

import com.skedio.administrationservice.usermanagement.user.controller.models.*;
import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.*;
import com.skedio.administrationservice.usermanagement.user.service.command.*;
import com.skedio.administrationservice.usermanagement.user.service.query.UserGetDetailByIdUseCase;
import com.skedio.administrationservice.usermanagement.user.service.query.UserGetDetailByUuidUseCase;
import com.skedio.administrationservice.usermanagement.user.service.query.UserSearchPageUseCase;
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
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserCreateUseCase createUseCase;
    UserSearchPageUseCase searchPageUseCase;
    UserGetDetailByIdUseCase getDetailByIdUseCase;
    UserGetDetailByUuidUseCase getDetailByUuidUseCase;
    UserUpdateUseCase updateUseCase;
    UserActiveUseCase activeUseCase;
    UserInActiveUseCase inActiveUseCase;
    UserDeleteUseCase deleteUseCase;
    UserRequestMapper requestMapper;
    UserResponseMapper responseMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<UserResponse> create(@RequestBody @Validated UserRequest request) {
        User user = requestMapper.toDomain(request);
        UserCreate userCreate = new UserCreate(user);
        Void handleSave = createUseCase.handle(userCreate);
        User savedUser = (User)  handleSave.data();
        UserResponse userResponse = responseMapper.toDto(savedUser);
        return ApiResponse.value(userResponse);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<List<UserResponse>> searchPage(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") List<String> statusCodes,
            @RequestParam(required = false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) List<LocalDateTime> timeRange,
            @RequestParam(required = false, defaultValue = "createdAt_asc") String sorter,
            @RequestParam(required = false, defaultValue = "0") Integer current,
            @RequestParam(required = false, defaultValue = "25") Integer pageSize
    ) {
        UserSearch userSearch =  new UserSearch(search, statusCodes, timeRange, sorter, current, pageSize);
        Page<User> users = searchPageUseCase.handle(userSearch);
        Page<UserResponse> responses = users.map(responseMapper::toDto);
        return ApiResponse.page(responses);
    }

    @GetMapping("/get-by-id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> getDetailByUserId(@PathVariable Long userId) {
        UserId userGetDetail = new UserId(userId);
        Void find = getDetailByIdUseCase.handle(userGetDetail);
        User foundUser = (User) find.data();
        UserResponse userResponse = responseMapper.toDto(foundUser);
        return ApiResponse.value(userResponse);
    }

    @GetMapping("/get-by-uuid/{userUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> getDetailByUserUuid(@PathVariable UUID userUuid) {
        UserUuid uuid = new UserUuid(userUuid);
        Void find = getDetailByUuidUseCase.handle(uuid);
        User foundUser = (User) find.data();
        UserResponse userResponse = responseMapper.toDto(foundUser);
        return ApiResponse.value(userResponse);
    }

    @PutMapping("/{userUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> update(@PathVariable UUID userUuid, @RequestBody @Validated UserUpdateRequest request) {
        UserUpdate userUpdate = UserUpdate.from(userUuid, request);
        Void handle = updateUseCase.handle(userUpdate);
        User updated =  (User) handle.data();
        UserResponse userResponse = responseMapper.toDto(updated);
        return ApiResponse.value(userResponse);
    }

    @PatchMapping("/active/{userUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> active(@PathVariable UUID userUuid) {
        UserUuid arg = new UserUuid(userUuid);
        Void active = activeUseCase.handle(arg);
        User user = (User) active.data();
        UserResponse response = responseMapper.toDto(user);
        return ApiResponse.value(response);
    }

    @PatchMapping("/inactive/{userUuid}")
    @ResponseStatus(HttpStatus.OK)
    ApiResponse<UserResponse> inactive(@PathVariable UUID userUuid) {
        UserUuid arg = new UserUuid(userUuid);
        Void inactive = inActiveUseCase.handle(arg);
        User user = (User) inactive.data();
        UserResponse response = responseMapper.toDto(user);
        return ApiResponse.value(response);
    }

    @DeleteMapping("/{userUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID userUuid) {
        UserUuid arg = new UserUuid(userUuid);
        deleteUseCase.handle(arg);
    }
}
