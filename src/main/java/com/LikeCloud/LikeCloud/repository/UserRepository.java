package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickNameAndPassword(String nickname, String password다);
    @Query("select u from User u where u.nickName = :nickName")
    List<User> findByUserNickName(@Param("nickName") String nickname);
}