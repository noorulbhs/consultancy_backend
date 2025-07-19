//package com.altrevo.consultancy.config;
//
//import com.altrevo.consultancy.entity.User;
//import com.altrevo.consultancy.enums.Role;
//import com.altrevo.consultancy.entity.InMemoryUserStore;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import jakarta.annotation.PostConstruct;
//
//@Configuration
//public class InMemoryUserDataLoader {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    public void loadUsers() {
//        User admin = new User();
//        admin.setEmail("admin@altrevo.com");
//        admin.setName("Admin User");
//        admin.setPassword(passwordEncoder.encode("admin123"));
//        admin.setRole(Role.ADMIN);
//        admin.setIsActive(true);
//        admin.setIsEmailVerified(true);
//        admin.setAvatarUrl(null);
//        admin.setPhone("1234567890");
//        InMemoryUserStore.addUser(admin);
//
//        User user = new User();
//        user.setEmail("user@altrevo.com");
//        user.setName("Regular User");
//        user.setPassword(passwordEncoder.encode("user123"));
//        user.setRole(Role.ADMIN);
//        user.setIsActive(true);
//        user.setIsEmailVerified(false);
//        user.setAvatarUrl(null);
//        user.setPhone("0987654321");
//        InMemoryUserStore.addUser(user);
//    }
//}
//
