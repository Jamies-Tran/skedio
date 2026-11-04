package com.skedio.administrationservice.clientmanagement.references.clientuser.service.query;

import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.ClClientUser;
import com.skedio.administrationservice.clientmanagement.references.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.references.clientuser.repository.ClClientUserRepository;
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
public class ClientSearchUserUseCase extends Query<ClientUserSearch, Page<ClClientUser>> {
    ClClientUserRepository clientUsers;

    @Override
    public Page<ClClientUser> handle(ClientUserSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ClientUserSearch computeSearch = query.computeSearch();
        return clientUsers.findAllUserInClient(computeSearch, pageRequest);
    }
}
