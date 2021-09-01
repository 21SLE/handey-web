package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.WeeklyBox;
import com.handey.web.repository.home.WeeklyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WeeklyService {

    private final WeeklyRepository weeklyRepository;


    public WeeklyService(WeeklyRepository weeklyRepository) {
        this.weeklyRepository = weeklyRepository;
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

    /**
     * WeeklyBox 단건 조회
     */
    public Optional<WeeklyBox> findOneWeeklyBox(Long id) {
        return weeklyRepository.findById(id);
    }

}