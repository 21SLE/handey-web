package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.WeeklyBox;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaWeeklyRepository implements WeeklyRepository{

    private final EntityManager em;

    public JpaWeeklyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public WeeklyBox save(WeeklyBox weeklyBox) {
        em.persist(weeklyBox);
        return weeklyBox;
    }

    @Override
    public Optional<WeeklyBox> findById(Long id) {
        WeeklyBox weeklyBox = em.find(WeeklyBox.class, id);
        return Optional.ofNullable(weeklyBox);
    }

    @Override
    public Optional<WeeklyBox> findByTitle(String title) {
        List<WeeklyBox> result = em.createQuery("select m from WeeklyBox m where m.title = :title", WeeklyBox.class)
                .setParameter("title", title)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<WeeklyBox> findAll() {

        //객체 자체를 조회
        return em.createQuery("select m from WeeklyBox as m", WeeklyBox.class)
                .getResultList();
    }
}
