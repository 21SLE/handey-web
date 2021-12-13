package com.handey.web.finishedweekly;

import com.handey.web.common.exception.FwNoDataFoundException;
import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.common.exception.WeeklyNoDataFoundException;
import com.handey.web.member.MemberRepository;
import com.handey.web.weekly.WeeklyBox;
import com.handey.web.weekly.WeeklyElm;
import com.handey.web.weekly.WeeklyElmRepository;
import com.handey.web.weekly.WeeklyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FwService {
    private final FwBoxRepository fwBoxRepository;
    private final FwElmRepository fwElmRepository;
    private final MemberRepository memberRepository;
    private final WeeklyRepository weeklyBoxRepository;
    private final WeeklyElmRepository weeklyElmRepository;

    public FwService(FwBoxRepository fwBoxRepository, FwElmRepository fwElmRepository, MemberRepository memberRepository, WeeklyRepository weeklyBoxRepository, WeeklyElmRepository weeklyElmRepository) {
        this.fwBoxRepository = fwBoxRepository;
        this.fwElmRepository = fwElmRepository;
        this.memberRepository = memberRepository;
        this.weeklyBoxRepository = weeklyBoxRepository;
        this.weeklyElmRepository = weeklyElmRepository;
    }

    public List<FwBox> getFwBoxListByUserIdAndDate(Long userId, LocalDate dt) {
        return fwBoxRepository.findByUserIdAndDate(userId, dt);
    }

    /**
     * 해당 위클리박스 아이디를 가진 fw박스가 있으면 거기에 elm 추가
     * 해당 위클리박스 아이디 가진 fw박스가 없으면 새로 fw박스만듦
     */
    public void handleAddingFwElm(Long userId, WeeklyBox weeklyBox, WeeklyElm weeklyElm) {
        // 해당 위클리박스 아이디를 가진 fw박스가 있는지 찾는다.
        boolean result = fwBoxRepository.findByWeeklyBoxId(weeklyBox.getId()).isPresent();
        if(result) {
            // 해당 위클리박스 아이디를 가진 fw박스가 있다면, 그 fw박스에 fwElm만 추가
            addFwElmToFwBox(weeklyBox, weeklyElm);
        } else {
            // 해당 위클리박스 아이디를 가진 fw박스가 없다면, fw박스 새로 만들고 elm 추가
            createFwBox(userId, weeklyBox, weeklyElm);
        }
    }

    /**
     * 해당 위클리박스 아이디를 가진 fw박스가 없어서 fw박스에 fwElm넣어서 새로 만들기
     */
    public Long createFwBox(Long userId, WeeklyBox weeklyBox, WeeklyElm weeklyElm) {
        FwBox fwBox = new FwBox();
        fwBox.setTitle(weeklyBox.getTitle());
        fwBox.setSaveDt(LocalDate.now());
        fwBox.setWeeklyBox(weeklyBox);
        fwBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));

        fwBoxRepository.save(fwBox);

        FwElm fwElm = new FwElm();
        fwElm.setContent(weeklyElm.getContent());
        fwElm.setFwBox(fwBox);
        fwElm.setWeeklyElm(weeklyElm);

        fwElmRepository.save(fwElm);

        return fwBox.getId();
    }

    /**
     * 해당 위클리박스 아이디를 가진 fw박스에 fwElm추가
     */
    public Long addFwElmToFwBox(WeeklyBox weeklyBox, WeeklyElm weeklyElm) {
        FwBox fwBox = fwBoxRepository.findByWeeklyBoxId(weeklyBox.getId()).orElseThrow(FwNoDataFoundException::new);
        FwElm fwElm = new FwElm();
        fwElm.setContent(weeklyElm.getContent());
        fwElm.setFwBox(fwBox);
        fwElm.setWeeklyElm(weeklyElm);
        fwElmRepository.save(fwElm);
        return fwBox.getId();
    }

    /**
     * fw elm 복구시
     *  1. 현재 weekly list에 해당하는 elm 있다면 fw elm삭제하고 그 weekly elm만 completed == false로 바꿔줌
     *  2. 현재 weekly list에 해당하는 elm이 없다면
     *    2-1) 해당하는 weeklybox는 있다면 해당하는 weekly박스에 weekly elm 추가해줌
     *    2-2) 해당하는 weeklybox도 없다면 weeklybox 새로 만들고 weekly elm 새로 만듦
     *
     *  이때 fwbox에 fw elm 하나도 없으면 fwbox 삭제하기
     */
    public void handleRestoringFwElm(Long userId, FwBox fwBox, FwElm fwElm) {
        boolean isThereWeeklyElm = weeklyElmRepository.findById(fwElm.getWeeklyElm().getId()).isPresent();
        if(isThereWeeklyElm) {
            WeeklyElm weeklyElm = weeklyElmRepository.findById(fwElm.getWeeklyElm().getId()).orElseThrow(WeeklyNoDataFoundException::new);
            weeklyElm.updateCompleted(!weeklyElm.isCompleted());
        } else {
            boolean isThereWeeklyBox = weeklyBoxRepository.findById(fwBox.getWeeklyBox().getId()).isPresent();
            if(isThereWeeklyBox) {
                WeeklyElm weeklyElm = new WeeklyElm();
                weeklyElm.setContent(fwElm.getContent());
                weeklyElm.setCompleted(false);
                WeeklyBox weeklyBox = weeklyBoxRepository.findById(fwBox.getWeeklyBox().getId()).orElseThrow(WeeklyNoDataFoundException::new);
                weeklyElmRepository.save(weeklyElm, weeklyBox);
            } else {
                WeeklyBox weeklyBox = new WeeklyBox();
                weeklyBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
                weeklyBox.setTitle(fwBox.getTitle());
                weeklyBoxRepository.save(weeklyBox);
                WeeklyElm weeklyElm = new WeeklyElm();
                weeklyElm.setContent(fwElm.getContent());
                weeklyElm.setCompleted(false);
                weeklyElmRepository.save(weeklyElm, weeklyBox);
            }
        }

        fwElmRepository.deleteById(fwElm.getId());
        if(fwBox.getFwElmList() == null || fwBox.getFwElmList().size() == 0) {
            fwBoxRepository.deleteById(fwBox.getId());
        }
    }

    public Optional<FwBox> findOneFwBox(Long id) {
        return fwBoxRepository.findById(id);
    }

    public Optional<FwElm> findOneFwElm(Long id) {
        return fwElmRepository.findById(id);
    }
}
