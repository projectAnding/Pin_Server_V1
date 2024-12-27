package com.server.pin.domain.boards.jobBoard.domain.entity;

import com.server.pin.domain.boards.jobBoard.domain.enums.ApplicantStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "jobpostapplicants")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class JobPostApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long applicantId;

    private Long postId;

    @Enumerated(EnumType.STRING)
    private ApplicantStatus status;
}
