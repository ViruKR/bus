package com.bus.repository;


import com.bus.entity.User;
import com.bus.payload.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods if needed.
    UserDTO findByUsername(String username);
}

