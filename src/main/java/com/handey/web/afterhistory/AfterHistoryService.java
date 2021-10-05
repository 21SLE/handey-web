package com.handey.web.afterhistory;

import com.handey.web.common.exception.WeeklyNoDataFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public List<AfterHistory> getAfterListByUserId(Long userId) {
        return afterHistoryRepository.findByUserId(userId);
    }


    public void deleteAfter(Long afterId) {
        AfterHistory afterHistory = afterHistoryRepository.findById(afterId).orElseThrow(WeeklyNoDataFoundException::new);
        afterHistoryRepository.deleteById(afterId);
    }

    public List<AfterHistory> getAfterListByDate(Long userId, LocalDate date) {
        return afterHistoryRepository.findByDate(userId, date);
    }
}
