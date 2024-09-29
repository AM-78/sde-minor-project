package com.editor.auth.repository;

import com.editor.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE :prefix%")
    List<User> findByUsernameStartingWith(@Param("prefix") String prefix);

    User findById(UUID userId);
}
