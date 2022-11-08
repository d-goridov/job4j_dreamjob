package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PostControllerTest {

    @Test
    public void whenPosts() {
        User user = new User(1, "Dmitriy", "email", "password");
        List<Post> posts = List.of(
                new Post(1, "New post", "description",
                        LocalDate.now(), true, new City(1, "RnD")),
                new Post(2, "New post", "description",
                        LocalDate.now(), false, new City(2, "MSK"))
        );
        Model model = mock(Model.class);
        model.addAttribute("user", user);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        when(postService.findAll()).thenReturn(posts);
        PostController postController = new PostController(postService, cityService);
        String page = postController.posts(model, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "New post", "description",
                LocalDate.now(), true, new City(1, "RnD"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        List<Post> posts = List.of(
                new Post(1, "New post", "description",
                        LocalDate.now(), true, new City(1, "RnD"))
        );
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.updatePost(new Post(1, "Update post", "update description",
                LocalDate.now(), true, new City(1, "RnD")));
        verify(postService).update(posts.get(0));
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        User user = new User(1, "Dmitriy", "email", "password");
        List<City> cities = List.of(new City(1, "MSK"), new City(2, "SPB"));
        Model model = mock(Model.class);
        model.addAttribute("user", user);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        HttpSession session = mock(HttpSession.class);
        when(cityService.getAllCities()).thenReturn(cities);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.addPost(model, session);
        verify(model).addAttribute("user", user);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void whenFormUpdatePost() {
        List<Post> posts = List.of(
                new Post(1, "New post #1", "description",
                        LocalDate.now(), true, new City(1, "RnD")),
                new Post(2, "New post #2", "description",
                                LocalDate.now(), true, new City(2, "SPB")
        ));
        List<City> cities = List.of(new City(1, "RnD"), new City(2, "SPB"));
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        when(cityService.getAllCities()).thenReturn(cities);
        when(postService.findAll()).thenReturn(posts);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.formUpdatePost(model, posts.get(0).getId());
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updatePost"));
    }
}