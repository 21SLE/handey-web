package com.handey.web.afterhistory;

import com.handey.web.common.exception.AfterNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Transactional
@Service
public class AfterHistoryService {
    private final AfterHistoryRepository afterHistoryRepository;

    public AfterHistoryService(AfterHistoryRepository afterHistoryRepository) {
        this.afterHistoryRepository = afterHistoryRepository;
    }

    public boolean createAfterHistory(Member member, WeeklyBox weeklyBox) {
        AfterHistory afterHistory1 = new AfterHistory();

        // 어제 날짜로 저장
        afterHistory1.setHist_date(LocalDate.now().minus(Period.ofDays(1)));
        afterHistory1.setContent(weeklyBox.getTitle());
        afterHistory1.setSubtitle(true);

        afterHistory1.setMember(member);
        afterHistoryRepository.save(afterHistory1);
        updateAfterSubtitleT(afterHistory1.getId());

        AfterHistory afterHistory2 = new AfterHistory();

        afterHistory2.setHist_date(LocalDate.now().minus(Period.ofDays(1)));
        afterHistory2.setMember(member);
        List<WeeklyElm> weeklyElmList = weeklyBox.getWeeklyElmList();

        weeklyElmList.forEach(weeklyElm -> {
            if(weeklyElm.isCompleted()) {
                afterHistory2.setContent(weeklyElm.getContent());
                afterHistoryRepository.save(afterHistory2);
            }
        });
        return true;
    }

    public List<AfterHistory> getAfterList() {
        return afterHistoryRepository.findAll();
    }

    public List<AfterHistory> getAfterListByUserId(Long userId) {
        return afterHistoryRepository.findByUserId(userId);
    }


    public void deleteAfter(Long afterId) {
        AfterHistory afterHistory = afterHistoryRepository.findById(afterId).orElseThrow(AfterNoDataFoundException::new);
        afterHistoryRepository.deleteById(afterId);
    }

    public List<AfterHistory> getAfterListByDate(Long userId, LocalDate date) {
        return afterHistoryRepository.findByDate(date);
    }

    public void updateAfterSubtitleT(Long Id) {
        AfterHistory afterHistory = afterHistoryRepository.findById(Id).orElseThrow(AfterNoDataFoundException::new);
        afterHistory.setSubtitle(true);
    }
}
