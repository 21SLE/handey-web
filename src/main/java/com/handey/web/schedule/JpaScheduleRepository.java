package com.handey.web.schedule;

import com.handey.web.todo.ToDoBox;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class JpaScheduleRepository implements ScheduleRepository {
    private final EntityManager em;

    public JpaScheduleRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Schedule save(Schedule schedule) {
        em.persist(schedule);
        return schedule;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        Schedule schedule = em.find(Schedule.class, id);
        return Optional.ofNullable(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return em.createQuery("select m from Schedule m", Schedule.class)
                .getResultList();
    }

    @Override
    public List<Schedule> findAllByUserId(Long userId) {
        return em.createQuery("select m from Schedule m where m.member.id = :userId", Schedule.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Schedule schedule = em.find(Schedule.class, id);
        Assert.notNull(schedule,"Schedule must not be null!");
        em.remove(schedule);
    }
}
