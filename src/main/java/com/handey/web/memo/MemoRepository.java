package com.handey.web.memo;

import com.handey.web.memo.Memo;

import java.util.Optional;

public interface MemoRepository {
    Memo save(Memo memo);
    Optional<Memo> findByUserId(Long id);
}
