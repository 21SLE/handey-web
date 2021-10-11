package com.handey.web.after;

import com.handey.web.common.exception.AfterNoDataFoundException;
import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.member.MemberRepository;
import com.handey.web.todo.ToDoElm;
import com.handey.web.todohistory.ToDoBoxHst;
import com.handey.web.todohistory.ToDoElmHst;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import com.handey.web.weekly.WeeklyParam;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Transactional
@Service
public class AfterService {
    private final AfterRepository afterRepository;
    private final MemberRepository memberRepository;

    public AfterService(AfterRepository afterRepository, MemberRepository memberRepository) {
        this.afterRepository = afterRepository;
        this.memberRepository = memberRepository;
    }

//    public Long createAfterBox(AfterBox afterBox) {
//        afterRepository.save(afterBox);
//        return afterBox.getId();
//    }

    public List<AfterBox> getAfterBoxList() {
        return afterRepository.findAll();
    }

    public List<AfterBox> getAfterBoxListByUserId(Long userId) {
        return afterRepository.findByUserId(userId);
    }

    public Optional<AfterBox> findOneAfterBox(Long id) {
        return afterRepository.findById(id);
    }

    // Weekly
    public Long createAfterBoxObj(Long userId) {
        AfterBox afterBox = new AfterBox();
        afterBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        afterRepository.save(afterBox);
        return afterBox.getId();
    }
    

    public boolean deleteAfterBox(Long afterId) {
        AfterBox afterBox = afterRepository.findById(afterId).orElseThrow(AfterNoDataFoundException::new);
        afterRepository.deleteById(afterId);
        return true;
    }

    public boolean updateAfterClear(Long afterId) {
        AfterBox afterBox = afterRepository.findById(afterId).orElseThrow(AfterNoDataFoundException::new);
        afterBox.updateClear(!afterBox.isClear());
        return afterBox.isClear();
    }

    public boolean updateAfterSubtitle(Long afterId) {
        AfterBox afterBox = afterRepository.findById(afterId).orElseThrow(AfterNoDataFoundException::new);
        afterBox.updateSubtitle(!afterBox.isSubtitle());
        return afterBox.isSubtitle();
    }

    /**
     weeklyBox afterBox로 복사
     */
    public void createAfterBox(Member member, WeeklyBox weeklyBox) {
        AfterBox afterBox = new AfterBox();
        afterBox.setContent(weeklyBox.getTitle());
        updateAfterSubtitle(afterBox.getId());
        updateAfterClear(afterBox.getId());

        afterBox.setMember(member);
        afterRepository.save(afterBox);

        List<WeeklyElm> weeklyElmList = weeklyBox.getWeeklyElmList();
        AtomicBoolean allWeeklyElmCompleted = new AtomicBoolean(true);

        weeklyElmList.forEach(weeklyElm -> {
            if (!weeklyElm.isCompleted()) afterBox.setClear(true);
            afterBox.setContent(weeklyElm.getContent());
            afterRepository.save(afterBox);

        });
    }

}
