package com.skedio.administrationservice.permissionmanagement.permissions.service.query;

import com.skedio.administrationservice.permissionmanagement.permissions.domain.Permission;
import com.skedio.administrationservice.permissionmanagement.permissions.domain.argument.PermissionSearch;
import com.skedio.administrationservice.permissionmanagement.permissions.repository.PermissionRepository;
import com.skedio.corestarter.template.Query;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionSearchPageUseCase extends Query<PermissionSearch, Page<Permission>> {
    PermissionRepository permissions;

    @Override
    public Page<Permission> handle(PermissionSearch query) {
        PageRequest pageRequest = query.pageRequest();
        PermissionSearch computeSearch = query.computeSearch();
        return permissions.findAll(computeSearch, pageRequest);
    }
}
