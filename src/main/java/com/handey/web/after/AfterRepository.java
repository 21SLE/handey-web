package com.handey.web.after;

import java.util.List;
import java.util.Optional;

public interface AfterRepository {
    AfterBox save(AfterBox after);
    Optional<AfterBox> findById(Long id);
    List<AfterBox> findAll();
    void deleteById(Long id);
    List<AfterBox> findByUserId(Long userId);
}
