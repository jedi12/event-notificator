package ru.project.my.eventnotificator.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import ru.project.my.eventnotificator.exceptions.ConditionUnacceptableException;

import javax.crypto.SecretKey;

@Component
public class JwtTokenManager {
    private final SecretKey secretKey;

    public JwtTokenManager(@Value("${eventnotificator.jwt-secret-key}") String key) {
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public Pair<Long, String> getUserIdAndRole(String jwt) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        Long userId = claims.get("userId", Long.class);
        if (userId == null) {
            throw new ConditionUnacceptableException("В jwt-токене отсутствует поле 'userId'");
        }

        String role = claims.get("role", String.class);
        if (StringUtils.isEmpty(role)) {
            throw new ConditionUnacceptableException("В jwt-токене отсутствует поле 'role'");
        }

        return Pair.of(userId, role);
    }
}
