package com.server.pin.global.log.repository;

import com.server.pin.global.log.domain.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
