package com.server.pin.domain.email.repository;

import com.server.pin.domain.email.domain.entity.AuthenticationNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<AuthenticationNumber, Long> {
    AuthenticationNumber findByEmail(String email);
    void deleteByEmail(String email);
}
