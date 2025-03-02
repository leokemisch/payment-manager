package com.github.payment_manager.repository;

import com.github.payment_manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    User getByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
