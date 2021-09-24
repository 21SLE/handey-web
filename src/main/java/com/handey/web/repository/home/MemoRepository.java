package com.handey.web.repository.home;

import com.handey.web.domain.home.Memo;

import java.util.Optional;

public interface MemoRepository {
    Memo save(Memo memo);
    Optional<Memo> findById(Long id);
}
