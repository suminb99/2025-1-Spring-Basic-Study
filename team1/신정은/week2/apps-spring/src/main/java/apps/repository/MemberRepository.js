package hello.helloapps.repository;

import hello.helloapps.domian.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member>
}