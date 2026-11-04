package com.skedio.administrationservice.permissionmanagement.permission.service.command;

import com.skedio.administrationservice.permissionmanagement.permission.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permission.domain.argument.PermissionCreate;
import com.skedio.administrationservice.permissionmanagement.permission.domain.error.EnumPermissionException;
import com.skedio.administrationservice.permissionmanagement.permission.repository.PermissionRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionCreateUseCase extends Command<PermissionCreate, Void> {
    PermissionRepository permissions;

    @Override
    public Void handle(PermissionCreate command) {
        validateBeforeSave(command);
        Permission permission = Permission.from(command);
        Permission computeBeforeSave = permission.computeBeforeSave();
        Permission saved = permissions.save(computeBeforeSave);
        return Void.of(saved);
    }

    private void validateBeforeSave(PermissionCreate command) {
        Boolean duplicated = permissions.existsByPermissionName(command.permissionName());
        if (duplicated) {
            throw duplicateException();
        }
    }

    private ApplicationException duplicateException() {
        throw new ApplicationException(EnumPermissionException.PERMISSION_DUP_NAME, EnumPermissionException.PERMISSION_DUP_NAME.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
