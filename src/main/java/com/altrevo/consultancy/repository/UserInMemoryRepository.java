package com.altrevo.consultancy.repository;

import com.altrevo.consultancy.entity.InMemoryUserStore;
import com.altrevo.consultancy.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserInMemoryRepository {
    public Optional<User> findByEmail(String email) {
        return InMemoryUserStore.findByEmail(email);
    }

    public void save(User user) {
        InMemoryUserStore.addUser(user);
    }

    public List<User> findAll() {
        return new ArrayList<>(InMemoryUserStore.getUsers().values());
    }
}

