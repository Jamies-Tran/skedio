package com.skedio.administrationservice.scopemanagement.scope.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public record ScopeSearch(
        @With
        String search,
        @With
        List<LocalDateTime> timeRange,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ScopeSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        List<LocalDateTime> timeRange = this.timeRange != null ? this.timeRange : List.of();
        return this
                .withSearch(search)
                .withTimeRange(timeRange);
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean timeRangeEmpty() {
        return timeRange.isEmpty();
    }

    public LocalDateTime timeRangeFirst() {
        if(timeRange.isEmpty()) {
            return null;
        }
        return timeRange.getFirst();
    }

    public LocalDateTime timeRangeLast() {
        if(timeRange.isEmpty()) {
            return null;
        }
        return timeRange.getLast();
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
