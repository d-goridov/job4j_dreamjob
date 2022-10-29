package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostDBStoreTest {

    @Test
    public void whenAddPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java JoB", "Desc for job",
                LocalDate.now(), true, new City(1, "RND"));
        store.add(post);
        Post postDb = store.findById(post.getId());
        assertThat(postDb.getName()).isEqualTo(post.getName());
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(2, "Java JoB", "Desc for job",
                LocalDate.now(), true, new City(1, "RND"));
        store.add(post);
        Post updated = new Post(post.getId(), "Middle Java JoB", "Desc for job",
                LocalDate.now(), true, new City(1, "RND"));

        store.update(updated);
        Post postDB = store.findById(post.getId());
        assertThat(postDB.getName()).isEqualTo(updated.getName());
    }

    @Test
    public void whenFindById() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(3, "Java JoB", "Desc for job",
                LocalDate.now(), true, new City(1, "RND"));
        store.add(post);
        Post postDb = store.findById(post.getId());
        assertThat(postDb).isEqualTo(post);
    }

    @Test
    public void whenShowAllPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post1 = new Post(4, "Java Middle", "Desc for Middle",
                LocalDate.now(), true, new City(1, "RND"));
        Post post2 = new Post(5, "Java Trainee", "Desc for Trainee",
                LocalDate.now(), true, new City(2, "MSK"));
        Post post3 = new Post(6, "Java Senior", "Desc for Senior",
                LocalDate.now(), true, new City(3, "EKB"));
        store.add(post1);
        store.add(post2);
        store.add(post3);
        List<Post> posts = store.findAll();
        assertThat(posts).contains(post1, post2, post3);
    }

}