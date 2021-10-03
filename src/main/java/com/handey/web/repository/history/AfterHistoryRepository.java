package com.handey.web.repository.history;

import com.handey.web.domain.history.AfterHistory;
import com.handey.web.domain.home.ToDoBox;

import java.util.List;
import java.util.Optional;

public interface AfterHistoryRepository {
    AfterHistory save(AfterHistory afterHistory);
    Optional<AfterHistory> findById(Long id);
    List<AfterHistory> findByDate(String date);
    List<AfterHistory> findAll();
    void deleteById(Long id);
    List<ToDoBox> findByUserId(Long userId);
}
