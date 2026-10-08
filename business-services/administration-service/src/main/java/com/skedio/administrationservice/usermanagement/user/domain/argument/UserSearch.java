package com.skedio.administrationservice.usermanagement.user.domain.argument;

import com.skedio.corestarter.utils.SorterUtils;
import com.skedio.corestarter.utils.StringConvertUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserSearch(
        String search,
        List<String> statusCodes,
        List<LocalDateTime> timeRange,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public UserSearch computeSearch() {
        String search = StringConvertUtils.normalizeWhiteSpace(this.search);
        List<String> statusCodes = this.statusCodes.isEmpty() ? List.of() : this.statusCodes;
        List<LocalDateTime> timeRange = this.timeRange.isEmpty() ? List.of() : this.timeRange;
        return UserSearch.builder()
                .search(search)
                .statusCodes(statusCodes)
                .timeRange(timeRange)
                .build();
    }

    public Boolean searchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean statusCodesEmpty() {
        return statusCodes.isEmpty();
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
