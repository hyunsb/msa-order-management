package com.sparta.msa_exam.auth.application.outputport;

import com.sparta.msa_exam.auth.application.domain.Member;
import com.sparta.msa_exam.auth.application.domain.MemberForCreate;
import java.util.Optional;

public interface MemberOutputPort {

    Optional<Member> findByUsername(String username);

    Member saveOne(MemberForCreate memberForCreate);
}
