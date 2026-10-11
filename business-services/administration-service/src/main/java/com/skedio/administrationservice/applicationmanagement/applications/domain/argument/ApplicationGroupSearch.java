package com.skedio.administrationservice.applicationmanagement.applications.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApplicationGroupSearch(
        @With
        String search,
        Boolean enabled,
        @With
        List<LocalDateTime> timeRange,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ApplicationGroupSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        List<LocalDateTime> timeRange = this.timeRange == null ? List.of() : this.timeRange;
        return this
                .withSearch(search)
                .withTimeRange(timeRange);
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean enabledEmpty() {
        return enabled == null;
    }

    public Boolean timeRangeEmpty() {
        return  timeRange.isEmpty();
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
