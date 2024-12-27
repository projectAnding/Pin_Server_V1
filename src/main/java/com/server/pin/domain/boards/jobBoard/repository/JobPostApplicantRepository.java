package com.server.pin.domain.boards.jobBoard.repository;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPostApplicant;
import com.server.pin.domain.boards.jobBoard.domain.enums.ApplicantStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostApplicantRepository extends JpaRepository<JobPostApplicant, Long> {
    JobPostApplicant findByPostIdAndApplicantIdAndStatus(Long userId, Long applicantId, ApplicantStatus status); //TODO:
    void deleteByPostIdAndApplicantIdAndStatus(Long postId, Long applicantId, ApplicantStatus status);
    List<JobPostApplicant> findAllByPostId(Long postId);
}
