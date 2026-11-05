package com.skedio.administrationservice.permissionmanagement.permissions.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.With;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public record PermissionSearch(
        @With
        String search,
        @With
        List<LocalDateTime> timeRange,
        @With
        Boolean enabled,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public PermissionSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        List<LocalDateTime> timeRange = this.timeRange != null ? this.timeRange : List.of();
        Boolean enabled = this.enabled != null ? this.enabled : true;
        return this
                .withSearch(search)
                .withEnabled(enabled)
                .withTimeRange(timeRange);
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean timeRangeEmpty() {
        return timeRange == null || timeRange.isEmpty();
    }

    public Boolean enabledEmpty() {
        return enabled == null;
    }

    public LocalDateTime timeRangeFirst() {
        if(timeRange == null || timeRange.isEmpty()) {
            return null;
        }
        return timeRange.getFirst();
    }

    public LocalDateTime timeRangeLast() {
        if(timeRange == null || timeRange.isEmpty()) {
            return null;
        }
        return timeRange.getLast();
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
