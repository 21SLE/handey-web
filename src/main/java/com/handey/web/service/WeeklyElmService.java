package com.handey.web.service;

import com.handey.web.common.exception.ToDoNoDataFoundException;
import com.handey.web.domain.history.WeeklyBox;
import com.handey.web.domain.history.WeeklyElm;
import com.handey.web.repository.history.WeeklyElmRepository;
import com.handey.web.repository.history.WeeklyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //WeeklyBox weeklyBox = WeeklyRepository.findById(weeklyId).orElseThrow(ToDoNoDataFoundException::new);
        weeklyElmRepository.save(weeklyElm);
        return weeklyElm.getId();
    }

    public Long createWeeklyElmObj(Long weeklyId) {
        WeeklyElm weeklyElm = new WeeklyElm();
        //WeeklyBox weeklyBox = WeeklyRepository.findById(weeklyId).orElseThrow(ToDoNoDataFoundException::new);
        weeklyElmRepository.save(weeklyElm);
        return weeklyElm.getId();
    }

    public boolean updateWeeklyElmContent(Long weeklyElmId) {
        return true;
    }

}
