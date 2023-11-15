package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

}