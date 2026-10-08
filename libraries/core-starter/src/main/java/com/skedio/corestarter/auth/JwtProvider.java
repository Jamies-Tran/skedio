package com.skedio.corestarter.auth;

import com.skedio.corestarter.CoreStarterProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtProvider {
    CoreStarterProperties properties;
    
    public String generateAccessToken(UserPrinciple userPrinciple) {
        SecretKey secretKey = generateSecretKey();
        return Jwts
                .builder()
                .signWith(secretKey)
                .subject(userPrinciple.userId())
                .claims(Map.of(
                        "sid", userPrinciple.sid(),
                        "username", userPrinciple.userName(),
                        "groups", userPrinciple.groups(),
                        "permissions", userPrinciple.permissions(),
                        "client", userPrinciple.clients(),
                        "type", "ACCESS"
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(properties.getAccessExpiredMillis())))
                .compact();
    }

    public String generateAccessToken(UserPrinciple userPrinciple, Long expiredMillis) {
        SecretKey secretKey = generateSecretKey();
        return Jwts
                .builder()
                .signWith(secretKey)
                .subject(userPrinciple.userId())
                .claims(Map.of(
                        "sid", userPrinciple.sid(),
                        "username", userPrinciple.userName(),
                        "groups", userPrinciple.groups(),
                        "permissions", userPrinciple.permissions(),
                        "client", userPrinciple.clients(),
                        "type", "ACCESS"
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expiredMillis)))
                .compact();
    }

    
    public String generateRefreshToken(UserPrinciple userPrinciple) {
        SecretKey secretKey = generateSecretKey();
        return Jwts
                .builder()
                .signWith(secretKey)
                .subject(userPrinciple.userId())
                .claims(Map.of(
                        "type", "REFRESH"
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(properties.getRefreshExpiredMillis())))
                .compact();
    }

    public String generateRefreshToken(UserPrinciple userPrinciple, Long expiredMillis) {
        SecretKey secretKey = generateSecretKey();
        return Jwts
                .builder()
                .signWith(secretKey)
                .subject(userPrinciple.userId())
                .claims(Map.of(
                        "type", "REFRESH"
                ))
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(expiredMillis)))
                .compact();
    }

    
    public Optional<UserPrinciple> parseAccessToken(String accessToken) {
        try {
            if(!StringUtils.hasText(accessToken)) {
                return Optional.empty();
            }
            Claims claims = parseClaim(accessToken);
            if(!Objects.equals(claims.get("type", String.class), "ACCESS")) {
                return Optional.empty();
            }
            return Optional.of(claims)
                    .map(UserPrinciple::parseAccessClaims);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    
    public Optional<UserPrinciple> parseRefreshToken(String refreshToken) {
        try {
            if(!StringUtils.hasText(refreshToken)) {
                return Optional.empty();
            }
            Claims claims = parseClaim(refreshToken);
            if(!Objects.equals(claims.get("type", String.class), "REFRESH")) {
                return Optional.empty();
            }
            return Optional.of(claims)
                    .map(UserPrinciple::parseRefreshClaims);
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    private SecretKey generateSecretKey() {
        return Keys.hmacShaKeyFor(properties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    private Claims parseClaim(String jws) {
        try {
            SecretKey secretKey = generateSecretKey();
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jws)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token đã hết hạn.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Loại token không được hỗ trợ.");
        } catch (MalformedJwtException e) {
            throw new JwtException("Token không đúng định dạng.");
        } catch (SecurityException e) {
            throw new JwtException("Chữ ký token không hợp lệ.");
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token không được bỏ trống.");
        }
    }
}
