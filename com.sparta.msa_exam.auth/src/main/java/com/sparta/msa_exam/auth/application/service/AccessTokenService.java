package com.sparta.msa_exam.auth.application.service;

import com.sparta.msa_exam.auth.application.domain.Member;
import com.sparta.msa_exam.auth.application.util.DateGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String TOKEN_DELIMITER = " ";
    private static final String CLAIM_NAME_ACTOR_ID = "actorId";

    private final DateGenerator dateGenerator;
    private final SecretKey secretKey;
    private final Long accessExpiration;
    private final String issuer;

    public AccessTokenService(
        DateGenerator dateGenerator,
        @Value("${service.jwt.secret-key}") String secretKey,
        @Value("${service.jwt.access-expiration}") Long accessExpiration,
        @Value("${spring.application.name}") String issuer
    ) {
        this.dateGenerator = dateGenerator;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.accessExpiration = accessExpiration;
        this.issuer = issuer;
    }

    public String createActorToken(Member member) {

        String token = Jwts.builder()
            .claim(CLAIM_NAME_ACTOR_ID, member.getMemberId())
            .expiration(dateGenerator.getExpireDate(accessExpiration))
            .signWith(secretKey)
            .compact();

        return String.join(TOKEN_DELIMITER, TOKEN_PREFIX, token);
    }
}
