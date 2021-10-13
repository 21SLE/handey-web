package com.handey.web.afterhistory;

import com.handey.web.after.AfterBox;
import com.handey.web.common.exception.AfterNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public void updateAfterSubtitleT(Long Id) {
        AfterHistory afterHistory = afterHistoryRepository.findById(Id).orElseThrow(AfterNoDataFoundException::new);
        afterHistory.setSubtitle(true);
    }

    public boolean createAfterHistory(Member member, WeeklyBox weeklyBox) {
        AfterHistory afterHistory = new AfterHistory();

        // 어제 날짜로 저장
        afterHistory.setSaveDt(LocalDate.now().minus(Period.ofDays(1)));
        afterHistory.setContent(weeklyBox.getTitle());

        afterHistory.setMember(member);
        afterHistoryRepository.save(afterHistory);

        List<WeeklyElm> weeklyElmList = weeklyBox.getWeeklyElmList();
        AtomicBoolean allWeeklyElmCompleted = new AtomicBoolean(true);

        weeklyElmList.forEach(weeklyElm -> {
            if(!weeklyElm.isCompleted()) allWeeklyElmCompleted.set(false);
            afterHistory.setContent(weeklyElm.getContent());
            updateAfterSubtitleT(afterHistory.getId());
            afterHistoryRepository.save(afterHistory);
        });

        return allWeeklyElmCompleted.get();

    }
}
