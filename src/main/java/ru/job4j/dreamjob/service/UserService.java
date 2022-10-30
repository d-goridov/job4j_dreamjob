package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;


@Service
@ThreadSafe
public class UserService {

    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public void add(User user) {
        store.add(user);
    }

    public User findUserByEmail(String email) {
        return store.findUserByEmail(email);
    }

}
