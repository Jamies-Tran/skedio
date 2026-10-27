package com.skedio.administrationservice.scopemanagement.scope.repository;

import com.skedio.administrationservice.scopemanagement.scope.domain.Scope;
import com.skedio.administrationservice.scopemanagement.scope.domain.argument.ScopeSearch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScopeRepository {
    ScopeJpaRepository repository;
    ScopeEntityMapper entityMapper;

    public Scope save(Scope scope) {
        ScopeJpaEntity entity = entityMapper.toEntity(scope);
        ScopeJpaEntity saved = repository.save(entity);
        return entityMapper.toDomain(saved);
    }

    public Boolean existsByScopeName(String scopeName) {
        return repository.existsByScopeName(scopeName);
    }

    public Page<Scope> findAll(ScopeSearch search, PageRequest pageRequest) {
        return repository.findAll(search, pageRequest)
                .map(entityMapper::toDomain);
    }

    public Optional<Scope> findByScopeUuid(UUID scopeUuid) {
        return repository.findByScopeUuid(scopeUuid)
                .map(entityMapper::toDomain);
    }

    public void deleteByScopeUuid(UUID scopeUuid) {
        repository.deleteByScopeUuid(scopeUuid);
    }

    public Boolean existsScopeInClient(UUID scopeUuid) {
        return repository.existsScopeInClient(scopeUuid);
    }
}
