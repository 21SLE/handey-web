package com.handey.web.service;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.trash.TrashBox;
import com.handey.web.domain.trash.TrashElm;
import com.handey.web.repository.trash.TrashBoxRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Transactional
@Service
public class TrashBoxService {
    private final TrashBoxRepository trashBoxRepository;

    public TrashBoxService(TrashBoxRepository trashBoxRepository) {
        this.trashBoxRepository = trashBoxRepository;
    }

    public Long createTrashBox(ToDoBox toDoBox) {
        TrashBox trashBox = new TrashBox();
        trashBox.setTitle(toDoBox.getTitle());
        trashBox.setNoTitle(toDoBox.isNoTitle());

        LocalDate today = LocalDate.now();

        trashBox.setRegisterDt(today);
        trashBox.setEndDt(today.plus(Period.ofDays(7)));

        toDoBox.getToDoElmList().forEach(toDoElm -> {
            TrashElm trashElm = new TrashElm();
            trashElm.setContent(toDoElm.getContent());
            trashElm.setCompleted(toDoElm.isCompleted());
            trashElm.setTrashBox(trashBox);


        });

        trashBoxRepository.save(trashBox);
        return trashBox.getId();
    }

    public List<TrashBox> getTrashBoxList() {
        return trashBoxRepository.findAll();
    }

    public void deleteTrashBoxListByDate(LocalDate trashBoxDate) {
        trashBoxRepository.deleteByDate(trashBoxDate);
    }

    public LocalDate getYesterdayDate() {
        LocalDate today = LocalDate.now();
        return today.minus(Period.ofDays(1));
    }
}
