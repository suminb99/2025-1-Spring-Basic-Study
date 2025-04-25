package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    ///  save를 어딘가 해놔야함
    private static Map<Long, Member> store = new HashMap<>();
    /// HashMap 실무에서는 동시성 문제가 있을 수 있어서 이렇게 공유되는 변수일 경우 concurrent HashMap을 사용
    private static long sequence = 0L;
    /// 시퀀스는 0,1,2 이렇게 키값을 생성해주는 애임
    /// long도 실무에서는 동시성 문제 고려해서 atomic long 등을 함

    @Override
    public Member save(Member member) {
        /// 아이디 세팅 후 스토어에 저장
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        /// null이 반환될 가능성이 있으면 optional을 감싸줌
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }


    @Override
    public List<Member> findALL() {
        return new ArrayList<>(store.values());
    }
}
