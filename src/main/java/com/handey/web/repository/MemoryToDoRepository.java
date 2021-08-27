package com.handey.web.repository;

import com.handey.web.domain.ToDoBox;

import java.util.*;

public class MemoryToDoRepository implements ToDoRepository {
    private static Map<Long, ToDoBox> store = new HashMap<>();
    private static long sequence =0L;

    @Override
    public ToDoBox save(ToDoBox toDoBox) {
        toDoBox.setToDoBoxId(++sequence);
        store.put(toDoBox.getToDoBoxId(), toDoBox);
        return toDoBox;
    }

    @Override
    public Optional<ToDoBox> findById(Long toDoBoxId) {
        return Optional.ofNullable(store.get(toDoBoxId));
    }

    @Override
    public Optional<ToDoBox> findByName(String toDoBoxTitle) {
        return store.values().stream().
        filter(toDoBox -> toDoBox.getToDoBoxTitle().equals(toDoBoxTitle)).findAny();

    }

    @Override
    public List<ToDoBox> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
