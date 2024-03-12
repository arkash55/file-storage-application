package com.example.FileStorageAppBackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Test
//    public void testCreateUser() {
//        User user = new User(
//                "test@mail.com",
//                "Test-First-Name",
//                "Test-Last-Name",
//                "Test-password"
//        );
//        User newUser = userRepository.save(user);
//        User savedUser = entityManager.find(User.class, newUser.getId());
//        assert(newUser.getEmail().equals(savedUser.getEmail()));
//
//    }
}
