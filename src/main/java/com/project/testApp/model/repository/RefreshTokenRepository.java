package com.project.testApp.model.repository;

import com.project.testApp.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	RefreshToken findByUser_UserId(Long userId);
}
