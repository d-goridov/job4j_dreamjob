package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(3);

    private PostStore() {
        posts.put(1, new Post(1, "Java Trainee", "description of Java Trainee",
                LocalDate.now(), false));
        posts.put(2, new Post(2, "Java Junior", "description of Java Junior",
                LocalDate.now(), false));
        posts.put(3, new Post(3, "Java Lead", "description of Java Lead",
                LocalDate.now(), false));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        int id = counter.incrementAndGet();
        post.setId(id);
        posts.putIfAbsent(id, post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        int id = post.getId();
        posts.replace(id, post);
    }
}
