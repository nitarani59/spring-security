package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.entity.TokenVerification;

public interface TokenVerificationRepository extends JpaRepository<TokenVerification, Long>{
    TokenVerification findByToken(String token);
}
