package com.sparta.msa_exam.auth.application.service;

import com.sparta.msa_exam.auth.application.domain.Member;
import com.sparta.msa_exam.auth.application.domain.MemberForCreate;
import com.sparta.msa_exam.auth.application.domain.MemberForSignIn;
import com.sparta.msa_exam.auth.application.outputport.MemberOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final MemberOutputPort memberOutputPort;

    public String signIn(MemberForSignIn memberForSignIn) {

        Member member = memberOutputPort.findByUsername(memberForSignIn.username()).orElseThrow(
            () -> new IllegalArgumentException("member Not Found")
        );
        member.tryToSignIn(memberForSignIn, passwordEncoder);

        return accessTokenService.createActorToken(member);
    }

    public void signUp(MemberForCreate memberForCreate) {

        if (memberOutputPort.findByUsername(memberForCreate.getUsername()).isPresent()) {
            throw new IllegalArgumentException("username duplicated");
        }
        memberForCreate = memberForCreate.encryptPassword(passwordEncoder);
        memberOutputPort.saveOne(memberForCreate);
    }
}
