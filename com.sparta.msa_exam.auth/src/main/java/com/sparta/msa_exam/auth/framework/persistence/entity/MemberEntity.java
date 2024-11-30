package com.sparta.msa_exam.auth.framework.persistence.entity;

import com.sparta.msa_exam.auth.application.domain.Member;
import com.sparta.msa_exam.auth.application.domain.MemberForCreate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public Member toDomain() {
        return new Member(id, username, password);
    }

    public static MemberEntity from(MemberForCreate memberForCreate) {
        return new MemberEntity(
            null, memberForCreate.getUsername(), memberForCreate.getPassword()
        );
    }
}
