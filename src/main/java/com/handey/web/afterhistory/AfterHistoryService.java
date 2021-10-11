package com.handey.web.afterhistory;

import com.handey.web.after.AfterBox;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.todo.ToDoElm;
import com.handey.web.todohistory.ToDoBoxHst;
import com.handey.web.todohistory.ToDoElmHst;
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

    public void createAfterHistory(Member member, AfterBox afterBox) {
        AfterHistory afterHistory = new AfterHistory();

        // 어제 날짜로 저장
        afterHistory.setSaveDt(LocalDate.now().minus(Period.ofDays(1)));
        afterHistory.setContent(afterBox.getContent());

        afterHistory.setMember(member);
        afterHistoryRepository.save(afterHistory);

        //List<AfterBox> afterHistoryList = afterBox.

    }
}
