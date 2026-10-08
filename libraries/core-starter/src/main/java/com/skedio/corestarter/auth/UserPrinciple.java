package com.skedio.corestarter.auth;

import io.jsonwebtoken.Claims;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@SuppressWarnings("unchecked")
public record UserPrinciple(
        String userId,
        String sid,
        String userName,
        List<String> groups,
        List<String> permissions,
        List<String> clients
) {
    public UserPrinciple {
        sid = UUID.randomUUID().toString();
    }

    public static UserPrinciple parseAccessClaims(Claims claims) {
        return UserPrinciple.builder()
                .userId(claims.getSubject())
                .sid(claims.get("sid", String.class))
                .userName(claims.get("username", String.class))
                .groups(claims.get("groups", List.class))
                .permissions(claims.get("permissions", List.class))
                .clients(claims.get("clients", List.class))
                .build();
    }

    public static UserPrinciple parseRefreshClaims(Claims claims) {
        return UserPrinciple.builder()
                .userId(claims.getSubject())
                .build();
    }
}
