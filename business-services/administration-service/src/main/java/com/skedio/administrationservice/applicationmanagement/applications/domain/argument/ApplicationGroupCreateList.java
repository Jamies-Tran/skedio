package com.skedio.administrationservice.applicationmanagement.applications.domain.argument;

import java.util.List;

public record ApplicationGroupCreateList(
        List<ApplicationGroupCreate> createList
) {
}
