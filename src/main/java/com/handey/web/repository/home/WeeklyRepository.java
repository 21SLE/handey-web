package com.handey.web.repository.home;

import com.handey.web.domain.home.WeeklyBox;

import java.util.List;
import java.util.Optional;

public interface WeeklyRepository {
    WeeklyBox save(WeeklyBox toDoBox);
    Optional<WeeklyBox> findById(Long id);
    Optional<WeeklyBox> findByTitle(String title);
    List<WeeklyBox> findAll();

}
