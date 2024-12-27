package com.server.pin.domain.boards.jobBoard.dto.response;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPostApplicant;
import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.domain.user.repository.UserRepository;

public record JobPostApplicantResponse(
        String profileImageURL,
        String userId,
        String username,

        String detailDepartment, // 학번

        String email,
        String phoneNumber
) {
    public static JobPostApplicantResponse of(JobPostApplicant jobPostApplicant, UserRepository userRepository) {
        UserEntity user = userRepository.findById(jobPostApplicant.getApplicantId()).get();
        return new JobPostApplicantResponse(user.getProfileImageURL(), user.getUserId(), user.getUsername(), user.getDetailDepartment(), user.getEmail(), user.getPhoneNumber());
    }
}
