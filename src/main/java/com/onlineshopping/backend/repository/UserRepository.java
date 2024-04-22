package com.onlineshopping.backend.repository;

import com.onlineshopping.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existByEmail(String email);

    void deleteByEmail(String email);

    Optional<User> findByEmail(String email);

}
