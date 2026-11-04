package com.skedio.administrationservice.clientmanagement.references.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.references.user.repository.ClUserEntityMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClClientUserRepository {
    ClClientUserJpaRepository repository;
    ClClientUserEntityMapper entityMapper;
    ClUserEntityMapper userEntityMapper;

    public List<ClClientUser> saveAll(List<ClClientUser> clClientUsers) {
        List<ClClientUserJpaEntity> entities = entityMapper.toEntity(clClientUsers);
        List<ClClientUserJpaEntity> saved = repository.saveAll(entities);
        return entityMapper.toDomain(saved);
    }

    public Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid) {
        return repository.existsClientByClientUuidAndEnabledTrue(clientUuid);
    }

    public Boolean existsClientByClientUuid(UUID clientUuid) {
        return repository.existsClientByClientUuid(clientUuid);
    }

    public Boolean existsByClientUuidAndUserUuidIn(UUID clientUuid, List<UUID> userUuids) {
        return repository.existsByClientUuidAndUserUuidIn(clientUuid, userUuids);
    }

    public Page<ClClientUser> findAllUserInClient(ClientUserSearch search, PageRequest pageRequest) {
        return repository.findAllUserInClient(search, pageRequest)
                .map(userEntityMapper::toDomain);
    }

    public void deleteAllByClientUuid(UUID clientUuid) {
        repository.deleteAllByClientUuid(clientUuid);
    }
}
