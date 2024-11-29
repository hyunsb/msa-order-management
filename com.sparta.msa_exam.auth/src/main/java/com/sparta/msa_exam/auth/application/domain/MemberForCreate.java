package com.sparta.msa_exam.auth.application.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberForCreate {

    private String username;
    private String password;

    public MemberForCreate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MemberForCreate encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
        return this;
    }
}
