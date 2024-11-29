package com.sparta.msa_exam.auth.application.domain;

import java.util.Objects;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Member {

    private Long memberId;
    private String username;
    private String password;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Member tryToSignIn(MemberForSignIn memberForSignIn, PasswordEncoder passwordEncoder) {
        if (matchesPassword(memberForSignIn.password(), passwordEncoder) &&
            matchesUsername(memberForSignIn.username())) {
            return this;
        }
        throw new IllegalArgumentException("유효하지 않은 username 혹은 passoword");
    }

    private boolean matchesPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }

    private boolean matchesUsername(String username) {
        return Objects.equals(this.username, username);
    }
}
