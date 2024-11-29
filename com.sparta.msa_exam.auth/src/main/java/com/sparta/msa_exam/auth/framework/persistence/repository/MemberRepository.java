package com.sparta.msa_exam.auth.framework.persistence.repository;

import com.sparta.msa_exam.auth.framework.persistence.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {

    Optional<MemberEntity> findByUsername(String username);
}
