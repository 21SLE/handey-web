package com.handey.web.repository.trash;

import com.handey.web.domain.trash.TrashBox;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrashBoxRepository {
    TrashBox save(TrashBox trashBox);
    Optional<TrashBox> findById(Long id);
    List<TrashBox> findByUserId(Long userId);
    List<TrashBox> findAll();
    void deleteByDate(LocalDate endDt);
    void deleteById(Long id);
}
