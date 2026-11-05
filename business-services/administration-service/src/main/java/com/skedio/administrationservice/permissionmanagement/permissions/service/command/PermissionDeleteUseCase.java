package com.skedio.administrationservice.permissionmanagement.permissions.service.command;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionUuid;
import com.skedio.administrationservice.permissionmanagement.permissions.repository.PermissionRepository;
import com.skedio.corestarter.template.Command;
import com.skedio.corestarter.template.Void;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionDeleteUseCase extends Command<PermissionUuid, Void> {
    PermissionRepository permissions;

    @Override
    public Void handle(PermissionUuid command) {
        UUID permissionUuid = command.permissionUuid();
        validateBeforeDelete(permissionUuid);
        permissions.deleteByPermissionUuid(permissionUuid);
        return null;
    }

    private void validateBeforeDelete(UUID permissionUuid) {

    }
}
