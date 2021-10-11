package com.handey.web.weekly;

import java.util.List;
import java.util.Optional;

public interface WeeklyRepository {
    WeeklyBox save(WeeklyBox weeklyBox);
    Optional<WeeklyBox> findById(Long id);
    Optional<WeeklyBox> findByTitle(String title);
    List<WeeklyBox> findAll();
    void deleteById(Long id);
    List<WeeklyBox> findByUserId(Long userId);
    List<WeeklyBox> findByClear(boolean clear);
}
