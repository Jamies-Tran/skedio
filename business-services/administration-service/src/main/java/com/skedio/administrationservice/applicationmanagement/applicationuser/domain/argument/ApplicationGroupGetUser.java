package com.skedio.administrationservice.applicationmanagement.applicationuser.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.NonNull;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Builder
public record ApplicationGroupGetUser(
        @With
        String search,
        @NonNull
        UUID applicationUuid,
        @NonNull
        String sorter,
        @NonNull
        Integer current,
        @NonNull
        Integer pageSize
) {
    public ApplicationGroupGetUser computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        return this.withSearch(search);
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(search);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
