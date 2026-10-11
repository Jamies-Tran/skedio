package com.skedio.administrationservice.usermanagement.user.repository;

import com.skedio.administrationservice.usermanagement.user.domain.User;
import com.skedio.administrationservice.usermanagement.user.domain.argument.UserSearch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepository {
    UserJpaRepository repository;
    UserEntityMapper mapper;

    public User save(User user) {
        UserJpaEntity userJpaEntity = mapper.toEntity(user);
        UserJpaEntity savedUserJpaEntity = repository.save(userJpaEntity);
        return mapper.toDomain(savedUserJpaEntity);
    }

    public Optional<User> findByUserUuid(UUID userUuid) {
        Optional<UserJpaEntity> entity = repository.findByUserUuid(userUuid);
        return entity.map(mapper::toDomain);
    }

    public Optional<User> findByUserId(Long userId) {
        Optional<UserJpaEntity> entity = repository.findByUserId(userId);
        return entity.map(mapper::toDomain);
    }

    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public Boolean existsByPhone(String phone) {
        return repository.existsByPhone(phone);
    }

    public Page<User> findAll(UserSearch userSearch) {
        PageRequest pageRequest = userSearch.pageRequest();
        userSearch = userSearch.computeSearch();
        return repository.findAll(userSearch, pageRequest)
                .map(mapper::toDomain);
    }

    public void delete(UUID userUuid) {
        Optional<User> find = findByUserUuid(userUuid);
        if(find.isPresent()) {
            User user = find.get();
            repository.deleteById(user.userId());
        }
    }
}
