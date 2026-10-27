package com.skedio.administrationservice.clientmanagement.clientscope.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

public record ClientScopeSearch(
        UUID clientUuid,
        @With
        String search,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ClientScopeSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        return this.withSearch(search);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(this.search);
    }
}
