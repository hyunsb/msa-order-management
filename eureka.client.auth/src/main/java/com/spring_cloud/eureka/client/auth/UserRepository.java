package com.spring_cloud.eureka.client.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserIdAndPassword(String userId, String password);
}
