package com.sparta.msa_exam.auth.framework.persistence.adapter;

import com.sparta.msa_exam.auth.application.domain.Member;
import com.sparta.msa_exam.auth.application.domain.MemberForCreate;
import com.sparta.msa_exam.auth.application.outputport.MemberOutputPort;
import com.sparta.msa_exam.auth.framework.persistence.entity.MemberEntity;
import com.sparta.msa_exam.auth.framework.persistence.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberAdapter implements MemberOutputPort {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username)
            .map(MemberEntity::toDomain)
            .or(Optional::empty);
    }

    @Override
    public Member saveOne(MemberForCreate memberForCreate) {
        MemberEntity member = MemberEntity.from(memberForCreate);
        return memberRepository.save(member).toDomain();
    }
}
