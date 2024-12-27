package com.server.pin.domain.boards.jobBoard.dto.response;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPostApplicant;
import com.server.pin.domain.boards.jobBoard.repository.JobPostApplicantRepository;
import com.server.pin.domain.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public record JobPostApplicantsResponse(
        List<JobPostApplicantResponse> jobPostApplicants
) {
    public static JobPostApplicantsResponse of(Long postId, JobPostApplicantRepository repository, UserRepository userRepository) {
        List<JobPostApplicantResponse> applicants = repository.findAllByPostId(postId).stream()
                .map(applicant -> JobPostApplicantResponse.of(applicant, userRepository))
                .collect(Collectors.toList());
        return new JobPostApplicantsResponse(applicants);
    }
}
