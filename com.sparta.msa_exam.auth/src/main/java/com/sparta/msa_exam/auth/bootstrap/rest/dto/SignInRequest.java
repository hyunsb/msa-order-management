package com.sparta.msa_exam.auth.bootstrap.rest.dto;

import com.sparta.msa_exam.auth.application.domain.MemberForSignIn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    private String username;
    private String password;

    public MemberForSignIn toDomain() {
        return new MemberForSignIn(username, password);
    }
}
