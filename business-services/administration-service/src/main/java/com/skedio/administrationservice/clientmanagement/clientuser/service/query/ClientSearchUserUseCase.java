package com.skedio.administrationservice.clientmanagement.clientuser.service.query;

import com.skedio.administrationservice.clientmanagement.clientuser.domain.ClientUser;
import com.skedio.administrationservice.clientmanagement.clientuser.domain.argument.ClientUserSearch;
import com.skedio.administrationservice.clientmanagement.clientuser.repository.ClientUserRepository;
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
public class ClientSearchUserUseCase extends Query<ClientUserSearch, Page<ClientUser>> {
    ClientUserRepository clientUsers;

    @Override
    public Page<ClientUser> handle(ClientUserSearch query) {
        PageRequest pageRequest = query.pageRequest();
        ClientUserSearch computeSearch = query.computeSearch();
        return clientUsers.findAllUserInClient(computeSearch, pageRequest);
    }
}
