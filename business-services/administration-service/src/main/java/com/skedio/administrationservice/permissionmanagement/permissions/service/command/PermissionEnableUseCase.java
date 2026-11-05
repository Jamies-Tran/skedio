package com.skedio.administrationservice.permissionmanagement.permissions.service.command;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionUuid;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.error.EnumPermissionException;
import com.skedio.administrationservice.permissionmanagement.permissions.repository.PermissionRepository;
import com.skedio.corestarter.advice.ApplicationException;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionEnableUseCase extends Command<PermissionUuid, Void> {
    PermissionRepository permissions;

    @Override
    public Void handle(PermissionUuid command) {
        UUID permissionUuid = command.permissionUuid();
        Permission permission = validatePermissionUuid(permissionUuid);
        Permission enabled = permission.enable();
        Permission computeBeforeSave = enabled.computeBeforeSave();
        Permission saved = permissions.save(computeBeforeSave);
        return Void.of(saved);
    }

    private Permission validatePermissionUuid(UUID permissionUuid) {
        Optional<Permission> find = permissions.findByPermissionUuid(permissionUuid);
        if(find.isEmpty()) {
            throw notFoundException();
        }
        return find.get();
    }

    private ApplicationException notFoundException() {
        return new ApplicationException(EnumPermissionException.PERMISSION_NOT_FOUND, EnumPermissionException.PERMISSION_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
    }
}
