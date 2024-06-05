package com.example.iems.repository;

import com.example.iems.model.Enum.Role;
import com.example.iems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String Username);
    Optional<User> findUsersByUsername(String userName);

    @Query("SELECT u.authorities FROM User u WHERE u.username = :username")
    List<String> findAuthoritiesByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a = :role")
    Optional<User> findUserByRole(@Param("role") Role role);

    List<User> findUsersByCity(String city);

    List<User> findUsersByTown(String town);

}
