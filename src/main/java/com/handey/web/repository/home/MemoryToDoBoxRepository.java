package com.handey.web.repository.home;

import com.handey.web.domain.home.ToDoBox;

import java.util.*;

public class MemoryToDoBoxRepository implements ToDoBoxRepository {
    private static Map<Long, ToDoBox> store = new HashMap<>();
    private static Long sequence =0L;

    @Override
    public ToDoBox save(ToDoBox toDoBox) {
        toDoBox.setId(++sequence);
        store.put(toDoBox.getId(), toDoBox);
        return toDoBox;
    }

    @Override
    public Optional<ToDoBox> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<ToDoBox> findByTitle(String title) {
        return store.values().stream().
        filter(toDoBox -> toDoBox.getTitle().equals(title)).findAny();

    }

    @Override
    public List<ToDoBox> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    public void clearStore() {
        store.clear();
    }
}
