package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;
import com.handey.web.domain.home.ToDoElm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JPAToDoElmRepository implements ToDoElmRepository{

    private final EntityManager em;

    public JPAToDoElmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public ToDoElm save(ToDoElm toDoElm) {
//        System.out.println("----------------------------------------------------------------------------------------------------------");
//        ToDoBox toDoBox = new ToDoBox();
//
//
//        // 테스트 투두박스 타이틀
//        toDoBox.setTitle("toDoBoxTest");
//        em.persist(toDoBox);
//        System.out.println("toDoBoxTest id: " + toDoBox.getId());
//
//        toDoElm.setToDoBox(toDoBox);

        em.persist(toDoElm);
        return toDoElm;
    }

    @Override
    public Optional<ToDoElm> findById(Long id) {
        ToDoElm ToDoElm = em.find(ToDoElm.class, id);
        return Optional.ofNullable(ToDoElm);
    }

    @Override
    public List<ToDoElm> findByToDoBoxId(Long toDoBoxId) {
        return em.createQuery("select m from ToDoElm m where m.toDoBox.id = :toDoBoxId", ToDoElm.class)
                .setParameter("toDoBoxId", toDoBoxId)
                .getResultList();
    }

    @Override
    public List<ToDoElm> findAll() {
        return em.createQuery("select m from ToDoElm m", ToDoElm.class)
                .getResultList();
    }
}
