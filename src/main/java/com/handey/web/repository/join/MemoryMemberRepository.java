package com.handey.web.repository.join;

import com.handey.web.domain.join.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    public static Map<Long, Member> store = new HashMap<Long, Member>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return Optional.ofNullable(store.get(username));
    }

    @Override
    public Optional<Member> findByUsernameAndPassword(String username, String password) {
        return store.values().stream()
                .filter(member -> member.getUsername().equals(username))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}



