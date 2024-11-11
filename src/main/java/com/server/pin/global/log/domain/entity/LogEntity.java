package com.server.pin.global.log.domain.entity;

import com.server.pin.global.log.domain.enums.LogType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String logString;

    @Column(name = "logtype", nullable = false)
    @Enumerated(EnumType.STRING)
    public LogType logType;

}
