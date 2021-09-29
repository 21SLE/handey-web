package com.handey.web.repository.join;

import com.handey.web.domain.join.member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    public static Map<Long, member> store = new HashMap<Long, member>();
    private static long sequence = 0L;

    @Override
    public member save(member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<member> findByUsername(String username) {
        return Optional.ofNullable(store.get(username));
    }

    @Override
    public Optional<member> findByUsernameAndPassword(String username, String password) {
        return store.values().stream()
                .filter(member -> member.getUsername().equals(username))
                .findAny();
    }

    @Override
    public List<member> findAll() {
        return new ArrayList<>(store.values());
    }
}



