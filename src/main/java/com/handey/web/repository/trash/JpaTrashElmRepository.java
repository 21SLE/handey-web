package com.handey.web.repository.trash;

import com.handey.web.domain.trash.TrashBox;
import com.handey.web.domain.trash.TrashElm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTrashElmRepository implements TrashElmRepository{
    private final EntityManager em;

    public JpaTrashElmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public TrashElm save(TrashElm trashElm) {
        em.persist(trashElm);
        return trashElm;
    }

    @Override
    public Optional<TrashElm> findById(Long id) {
        TrashElm trashElm = em.find(TrashElm.class, id);
        return Optional.ofNullable(trashElm);
    }

    @Override
    public List<TrashElm> findByTrashBoxId(Long trashBoxId) {
        return em.createQuery("select m from TrashElm m where m.trashBox.id = :trashBoxId", TrashElm.class)
                .setParameter("trashBoxId", trashBoxId)
                .getResultList();
    }

    @Override
    public List<TrashBox> findAll() {
        return em.createQuery("select m from TrashBox m", TrashBox.class)
                .getResultList();
    }
}
