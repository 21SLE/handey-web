package com.handey.web.service;

import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.domain.history.AfterHistory;
import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.repository.history.AfterHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AfterHistoryService {
    private final AfterHistoryRepository afterHistoryRepository;

    public AfterHistoryService(AfterHistoryRepository afterHistoryRepository) {
        this.afterHistoryRepository = afterHistoryRepository;
    }

    public List<AfterHistory> getAfterList() {
        return afterHistoryRepository.findAll();
    }

    public List<ToDoBox> getAfterListByUserId(Long userId) {
        return afterHistoryRepository.findByUserId(userId);
    }


    public void deleteAfter(Long afterId) {
        AfterHistory afterHistory = afterHistoryRepository.findById(afterId).orElseThrow(WeeklyNoDataFoundException::new);
        afterHistoryRepository.deleteById(afterId);
    }
}
