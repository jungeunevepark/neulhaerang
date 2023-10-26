package com.finale.neulhaerang.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finale.neulhaerang.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findMemberByKakaoId(long kakaoId);
}
