package com.handey.web.weekly;

import com.handey.web.common.exception.WeeklyNoDataFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WeeklyElmService {

    private final WeeklyElmRepository weeklyElmRepository;
    private final WeeklyRepository weeklyRepository;

    public WeeklyElmService(WeeklyElmRepository weeklyElmRepository, WeeklyRepository weeklyRepository) {
        this.weeklyElmRepository = weeklyElmRepository;
        this.weeklyRepository = weeklyRepository;
    }

    public Long createWeeklyElm(Long weeklyId, WeeklyElm weeklyElm) {
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyElmRepository.save(weeklyElm, weeklyBox);
        return weeklyElm.getId();
    }

    public List<WeeklyElm> getWeeklyElmList(Long weeklyId) {
        return weeklyElmRepository.findByWeeklyId(weeklyId);
    }

    public Long createWeeklyElmObj(Long weeklyId) {
        WeeklyElm weeklyElm = new WeeklyElm();
        WeeklyBox weeklyBox = weeklyRepository.findById(weeklyId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyElmRepository.save(weeklyElm, weeklyBox);
        return weeklyElm.getId();
    }

    public boolean updateWeeklyElmContent(Long weeklyElmId, WeeklyElmParam content) {
        WeeklyElm weeklyElm = weeklyElmRepository.findById(weeklyElmId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyElm.updateContent(content.getContent());
        return true;
    }

    public boolean updateWeeklyElmCompleted(Long weeklyElmId) {
        WeeklyElm weeklyElm = weeklyElmRepository.findById(weeklyElmId).orElseThrow(WeeklyNoDataFoundException::new);
        weeklyElm.updateCompleted(!weeklyElm.isCompleted());
        return weeklyElm.isCompleted();
    }

    public Optional<WeeklyElm> findOneWeeklyElm(Long id) {
        return weeklyElmRepository.findById(id);
    }

    public boolean deleteWeeklyElm(Long weeklyElmId) {
        Optional<WeeklyElm> weeklyElm = weeklyElmRepository.findById(weeklyElmId);
        weeklyElmRepository.deleteById(weeklyElmId);
        return true;
    }

}
