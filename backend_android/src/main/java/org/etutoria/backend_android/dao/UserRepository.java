package org.etutoria.backend_android.dao;

import org.etutoria.backend_android.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


    Optional<User> findByEmail(String email);

}
