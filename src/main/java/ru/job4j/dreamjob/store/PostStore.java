package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {
    private static final PostStore INST = new PostStore();
    Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Java Trainee", "description of Java Trainee", LocalDate.now()));
        posts.put(2, new Post(2, "Java Junior", "description of Java Junior", LocalDate.now()));
        posts.put(3, new Post(3, "Java Lead", "description of Java Lead", LocalDate.now()));
    }

    public static PostStore getInst() {
        return INST;
    }
    public Collection<Post> findAll() {
        return posts.values();
    }
}
