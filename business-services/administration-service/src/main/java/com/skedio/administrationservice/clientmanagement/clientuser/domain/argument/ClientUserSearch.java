package com.skedio.administrationservice.clientmanagement.clientuser.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import jakarta.validation.constraints.NotNull;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.UUID;

public record ClientUserSearch(
        @NotNull
        UUID clientUuid,
        @With
        String search,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ClientUserSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        return this.withSearch(search);
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(this.search);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
