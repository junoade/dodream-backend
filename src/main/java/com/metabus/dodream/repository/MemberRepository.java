package com.metabus.dodream.repository;

import com.metabus.dodream.domain.account.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);
    /* 다른건 그냥 findBy로 찾으면됨*/
}
