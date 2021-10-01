package com.handey.web.repository.trash;

import com.handey.web.domain.trash.TrashBox;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTrashBoxRepository implements TrashBoxRepository{
    private final EntityManager em;

    public JpaTrashBoxRepository(EntityManager em) {
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
    public List<TrashBox> findByUserId(Long userId) {
        return em.createQuery("select m from TrashBox m where m.member.id = :userId", TrashBox.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<TrashBox> findAll() {
        return em.createQuery("select m from TrashBox m", TrashBox.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteByDate(LocalDate endDt) {
        List<TrashBox> trashBoxList = em.createQuery("select m from TrashBox m where m.endDt = :endDt", TrashBox.class)
                .setParameter("endDt", endDt)
                .getResultList();
//        Assert.notNull(trashBoxList, "Trash Box List must not be null!");

        if(trashBoxList != null && trashBoxList.size() != 0) {
            em.createQuery("delete from TrashBox m where m.endDt = :endDt")
                    .setParameter("endDt", endDt)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) {
        TrashBox trashBox = em.find(TrashBox.class, id);
        Assert.notNull(trashBox,"Trash Box must not be null!");
        em.remove(trashBox);
    }
}
