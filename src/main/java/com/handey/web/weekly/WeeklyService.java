package com.handey.web.weekly;

import com.handey.web.afterhistory.AfterHistory;
import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.member.Member;
import com.handey.web.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Transactional
@Service
public class WeeklyService {

    private final WeeklyRepository weeklyRepository;
    private final MemberRepository memberRepository;


    public WeeklyService(WeeklyRepository weeklyRepository, MemberRepository memberRepository) {
        this.weeklyRepository = weeklyRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * WeeklyBox 생성
     */
    public Long createWeeklyBox(WeeklyBox weeklyBox) {
        weeklyRepository.save(weeklyBox);
        return weeklyBox.getId();
    }

    /**
     * WeeklyBox 리스트 조회
     */
    public List<WeeklyBox> getWeeklyBoxList() {
        return weeklyRepository.findAll();
    }

    public List<WeeklyBox> getWeeklyBoxListByUserId(Long userId) {
        return weeklyRepository.findByUserId(userId);
    }

    //public List<WeeklyBox> getWeeklyBoxListCleared() {return weeklyRepository.findByClear(true);}

    /**
     * WeeklyBox 단건 조회
     */
    public Optional<WeeklyBox> findOneWeeklyBox(Long id) {
        return weeklyRepository.findById(id);
    }

    // Weekly 객체 생성
    public Long createWeeklyBoxObj(Long userId) {
        WeeklyBox weeklyBox = new WeeklyBox();
        weeklyBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        weeklyRepository.save(weeklyBox);
        return weeklyBox.getId();
    }

    /**
    weekly title 수정
     */
    public boolean updateWeeklyTitle(Long weeklyId, WeeklyParam param) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyBox.updateTitle(param.getTitle());
        return true;
    }

    /**
    weeklyBox 삭제
    */
    public boolean deleteWeekly(Long weeklyId) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyRepository.deleteById(weeklyId);
        return true;
    }

    /**
     weeklyBox clear 상태변경
     */
    public boolean updateWeeklyClear(Long weeklyId) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyBox.updateClear(!weeklyBox.getClear());
        return weeklyBox.getClear();
    }

}
