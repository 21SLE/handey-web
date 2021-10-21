package com.handey.web.memo;

import java.util.Optional;

public interface MemoRepository {
    Memo save(Memo memo);
    Optional<Memo> findByUserId(Long userId);
}
