package com.handey.web.service;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.controller.history.WeeklyParam;
import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.repository.history.WeeklyRepository;
import com.handey.web.repository.join.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    /**
     * WeeklyBox 단건 조회
     */
    public Optional<WeeklyBox> findOneWeeklyBox(Long id) {
        return weeklyRepository.findById(id);
    }

    // Weekly
    public Long createWeeklyBoxObj(Long userId) {
        WeeklyBox weeklyBox = new WeeklyBox();
        weeklyBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        weeklyRepository.save(weeklyBox);
        return weeklyBox.getId();
    }

    public boolean updateWeeklyTitle(Long weeklyId, WeeklyParam param) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyBox.updateTitle(param.getTitle());
        return true;
    }

    public boolean deleteWeekly(Long weeklyId) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyRepository.deleteById(weeklyId);
        return true;
    }

    public boolean updateWeeklyClear(Long weeklyId) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyBox.updateClear(!weeklyBox.getClear());
        return weeklyBox.getClear();
    }


}
