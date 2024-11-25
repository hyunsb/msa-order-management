package com.spring_cloud.eureka.client.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
        @Value("${service.jwt.secret-key}") String secretKey) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String signIn(String userId, String password) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid user Id or Password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid user Id or Password");
        }
        return createAccessToken(user.getUserId(), user.getRole());
    }

    public String createAccessToken(String userId, String role) {
        return Jwts.builder()
            .claim("userId", userId)
            .claim("role", role)
            .issuer(issuer)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + accessExpiration))
            .signWith(secretKey, io.jsonwebtoken.SignatureAlgorithm.HS512)
            .compact();
    }

    public User signUp(User user) {
        User encryptedUser = user.encryptPassword(passwordEncoder);
        return userRepository.save(encryptedUser);
    }
}
