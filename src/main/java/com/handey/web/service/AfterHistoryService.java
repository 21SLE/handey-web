package com.handey.web.service;

import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.domain.history.AfterHistory;
import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.repository.history.AfterHistoryRepository;
import org.springframework.stereotype.Service;

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

    public void deleteAfter(Long afterId) {
        AfterHistory afterHistory = afterHistoryRepository.findById(afterId).orElseThrow(WeeklyNoDataFoundException::new);
        afterHistoryRepository.deleteById(afterId);
    }
}
