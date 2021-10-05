package com.handey.web.trash;

import java.util.List;
import java.util.Optional;

public interface TrashElmRepository {
    TrashElm save(TrashElm trashElm);
    Optional<TrashElm> findById(Long id);
    List<TrashElm> findByTrashBoxId(Long trashBoxId);
    List<TrashBox> findAll();
}
