package com.handey.web.service;

import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.controller.history.WeeklyElmParam;
import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.history.WeeklyElm;
import com.handey.web.domain.home.ToDoElm;
import com.handey.web.repository.history.WeeklyElmRepository;
import com.handey.web.repository.history.WeeklyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
