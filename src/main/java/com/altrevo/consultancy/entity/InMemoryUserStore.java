package com.altrevo.consultancy.entity;

import com.altrevo.consultancy.enums.Role;

import java.util.*;

public class InMemoryUserStore {
    private static final Map<String, User> users = new HashMap<>();

    static {
        // User from StoreData.sql
        User admin = new User();
        admin.setId(1L);
        admin.setEmail("noorul@altrevo.com");
        admin.setName("Noorul");
        admin.setPassword("$2a$10$H5zxK8RSr.uizhs/n1dRN.XFgZ5dShp9KKoQmLHHyu9a.1Rr/F62C"); // bcrypt hash from SQL
        admin.setRole(Role.ADMIN);
        admin.setIsActive(true); // _binary '1' in SQL
        admin.setIsEmailVerified(true); // _binary '1' in SQL
        admin.setAvatarUrl("https://example.com/avatar-admin.jpg");
        admin.setPhone("+1-555-0001");
        // Add any other fields as needed
        users.put(admin.getEmail(), admin);

        User user1 = new User();
        user1.setId(2L);
        user1.setEmail("asheer@altrevo.com");
        user1.setName("Asheer");
        user1.setPassword("$2a$10$AzJFfmYlSr/gbw5XByJSK.QxjP8nLQi5kpQeYDRUU/Iwv0xiPyzHO"); // bcrypt hash from SQL
        user1.setRole(Role.ADMIN);
        user1.setIsActive(true); // _binary '1' in SQL
        user1.setIsEmailVerified(true); // _binary '1' in SQL
        user1.setAvatarUrl("https://example.com/avatar-admin.jpg");
        user1.setPhone("+1-555-0001");
        // Add any other fields as needed
        users.put(user1.getEmail(), user1);

        User user2 = new User();
        user2.setId(3L);
        user2.setEmail("atif@altrevo.com");
        user2.setName("Atif");
        user2.setPassword("$2a$12$9XKKqOPBRcXpeQUI.9MiquoEJlTehyUBi0Urpm0yoqaly5RGEJaau"); // bcrypt hash from SQL
        user2.setRole(Role.ADMIN);
        user2.setIsActive(true); // _binary '1' in SQL
        user2.setIsEmailVerified(true); // _binary '1' in SQL
        user2.setAvatarUrl("https://example.com/avatar-admin.jpg");
        user2.setPhone("+1-555-0001");
        // Add any other fields as needed
        users.put(user2.getEmail(), user2);
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public static Optional<User> findByEmail(String email) {
//        System.out.println(users.get(email).toString());
        return Optional.ofNullable(users.get(email));
    }

    public static void clear() {
        users.clear();
    }
}
