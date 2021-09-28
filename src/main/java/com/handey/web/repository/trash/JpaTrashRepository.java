package com.handey.web.repository.trash;

import com.handey.web.domain.history.ToDoBoxHst;
import com.handey.web.domain.history.ToDoElmHst;
import com.handey.web.domain.trash.TrashBox;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTrashRepository implements TrashBoxRepository{
    private final EntityManager em;

    public JpaTrashRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public TrashBox save(TrashBox trashBox) {
        em.persist(trashBox);
        return trashBox;
    }

    @Override
    public Optional<TrashBox> findById(Long id) {
        TrashBox trashBox = em.find(TrashBox.class, id);
        return Optional.ofNullable(trashBox);
    }

    @Override
    public List<TrashBox> findAll() {
        return em.createQuery("select m from TrashBox m", TrashBox.class)
                .getResultList();
    }

    @Override
    public void deleteByDate(LocalDate endDt) {
        List<TrashBox> trashBoxList = em.createQuery("select m from TrashBox m where m.endDt = :endDt", TrashBox.class)
                .setParameter("endDt", endDt)
                .getResultList();
        //Assert.notNull(trashBoxList, "Trash Box must not be null!");
        if(trashBoxList != null && trashBoxList.size() != 0)
            em.createQuery("delete from TrashBox where TrashBox.endDt = :endDt", TrashBox.class)
                .setParameter("endDt", endDt);
    }
}
