package com.handey.web.service;

import com.handey.web.common.exception.MemberNoDataFoundException;
import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.trash.TrashBox;
import com.handey.web.domain.trash.TrashElm;
import com.handey.web.repository.join.MemberRepository;
import com.handey.web.repository.trash.TrashBoxRepository;
import com.handey.web.repository.trash.TrashElmRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TrashBoxService {
    private final TrashBoxRepository trashBoxRepository;
    private final TrashElmRepository trashElmRepository;
    private final MemberRepository memberRepository;

    public TrashBoxService(TrashBoxRepository trashBoxRepository, TrashElmRepository trashElmRepository, MemberRepository memberRepository) {
        this.trashBoxRepository = trashBoxRepository;
        this.trashElmRepository = trashElmRepository;
        this.memberRepository = memberRepository;
    }

    public Long createTrashBox(Long userId, ToDoBox toDoBox) {
        TrashBox trashBox = new TrashBox();
        trashBox.setTitle(toDoBox.getTitle());
        trashBox.setNoTitle(toDoBox.isNoTitle());
        trashBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));

        LocalDate today = LocalDate.now();

        trashBox.setRegisterDt(today);
        trashBox.setEndDt(today.plus(Period.ofDays(7)));

        trashBoxRepository.save(trashBox);

        toDoBox.getToDoElmList().forEach(toDoElm -> {
            TrashElm trashElm = new TrashElm();
            trashElm.setContent(toDoElm.getContent());
            trashElm.setCompleted(toDoElm.isCompleted());
            trashElm.setTrashBox(trashBox);

            trashElmRepository.save(trashElm);
        });

        return trashBox.getId();
    }

    /**
     * only for test
     */
    public void createTrashBoxWithElms(Long userId, TrashBox trashBox) {
        trashBox.setMember(memberRepository.findById(userId).orElseThrow(MemberNoDataFoundException::new));
        trashBoxRepository.save(trashBox);
        trashBox.getTrashElmList().forEach(trashElmRepository::save);
    }

    public Optional<TrashBox> getOneTrashBox(Long trashBoxId) {
        return trashBoxRepository.findById(trashBoxId);
    }

    public List<TrashBox> getTrashBoxList() {
        return trashBoxRepository.findAll();
    }

    public List<TrashBox> getTrashBoxListByUserId(Long userId) {
        return trashBoxRepository.findByUserId(userId);
    }

    public void deleteTrashBoxListByDate(LocalDate trashBoxDate) {
        trashBoxRepository.deleteByDate(trashBoxDate);
    }

    public void deleteTrashBox(Long trashBoxId) {
        trashBoxRepository.deleteById(trashBoxId);
    }
}
