package com.skedio.administrationservice.clientmanagement.clientuser.repository;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserSearch;
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
public class ClientUserRepository {
    ClientUserJpaRepository repository;
    ClientUserEntityMapper entityMapper;
    UserEntityMapper userEntityMapper;

    public List<ClientUser> saveAll(List<ClientUser> clientUsers) {
        List<ClientUserJpaEntity> entities = entityMapper.toEntity(clientUsers);
        List<ClientUserJpaEntity> saved = repository.saveAll(entities);
        return entityMapper.toDomain(saved);
    }

    public Boolean existsClientByClientUuidAndEnabledTrue(UUID clientUuid) {
        return repository.existsClientByClientUuidAndEnabledTrue(clientUuid);
    }

    public Boolean existsByClientUuidAndUserUuidIn(UUID clientUuid, List<UUID> userUuids) {
        return repository.existsByClientUuidAndUserUuidIn(clientUuid, userUuids);
    }

    public Page<ClientUser> findAllUserInClient(ClientUserSearch search, PageRequest pageRequest) {
        return repository.findAllUserInClient(search, pageRequest)
                .map(userEntityMapper::toDomain);
    }
}
