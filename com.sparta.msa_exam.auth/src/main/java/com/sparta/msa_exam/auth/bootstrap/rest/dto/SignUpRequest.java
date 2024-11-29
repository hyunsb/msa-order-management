package com.sparta.msa_exam.auth.bootstrap.rest.dto;

import com.sparta.msa_exam.auth.application.domain.MemberForCreate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String username;
    private String password;

    public MemberForCreate toDomain() {
        return new MemberForCreate(username, password);
    }
}
