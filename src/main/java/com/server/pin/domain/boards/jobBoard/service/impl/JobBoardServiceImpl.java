package com.server.pin.domain.boards.jobBoard.service.impl;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPost;
import com.server.pin.domain.boards.jobBoard.domain.entity.JobPostApplicant;
import com.server.pin.domain.boards.jobBoard.domain.enums.ApplicantStatus;
import com.server.pin.domain.boards.jobBoard.dto.request.PatchJobApplicantRequest;
import com.server.pin.domain.boards.jobBoard.dto.request.PostJobRequest;
import com.server.pin.domain.boards.jobBoard.dto.response.JobPostApplicantsResponse;
import com.server.pin.domain.boards.jobBoard.dto.response.PostJobResponse;
import com.server.pin.domain.boards.jobBoard.repository.JobBoardRepository;
import com.server.pin.domain.boards.jobBoard.repository.JobPostApplicantRepository;
import com.server.pin.domain.boards.jobBoard.service.JobBoardService;
import com.server.pin.domain.user.repository.UserRepository;
import com.server.pin.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobBoardServiceImpl implements JobBoardService {
    private final JobBoardRepository jobBoardRepository;
    private final SecurityHolder securityHolder;
    private final JobPostApplicantRepository jobPostApplicantRepository;
    private final UserRepository userRepository;

    @Override
    public PostJobResponse postJob(PostJobRequest request) {
        JobPost post = JobPost.builder()
                .imageUrl(request.imageUrl())
                .title(request.title())
                .author(securityHolder.getPrincipal())
                .period(request.period())
                .memberLimit(request.memberLimit())
                .content(request.content())
                .build();

        return PostJobResponse.of(jobBoardRepository.save(post));
    }

    @Override
    public void applicate(Long postId) {
        JobPostApplicant applicant = JobPostApplicant.builder()
                .applicantId(securityHolder.getPrincipal().getId())
                .postId(postId)
                .status(ApplicantStatus.WAITING)
                .build();

        JobPost post =  jobBoardRepository.findById(postId).get();
        post.addApplicant(applicant);
        jobPostApplicantRepository.save(applicant);
    }

    @Override
    public PostJobResponse getJob(Long postId) {
        return PostJobResponse.of(jobBoardRepository.findById(postId).get());
    }

    @Override
    public void agreeApplicant(PatchJobApplicantRequest request) {
        JobPostApplicant applicant = jobPostApplicantRepository.findByPostIdAndApplicantIdAndStatus(request.postId(), request.applicantId(), ApplicantStatus.WAITING); //TODO:
        applicant.setStatus(ApplicantStatus.AGREED);
        jobPostApplicantRepository.save(applicant);
    }

    @Override
    public void denyApplicant(PatchJobApplicantRequest request) {
        JobPostApplicant applicant = jobPostApplicantRepository.findByPostIdAndApplicantIdAndStatus(request.postId(), request.applicantId(), ApplicantStatus.WAITING); //TODO:
        applicant.setStatus(ApplicantStatus.DENIED);
        jobPostApplicantRepository.save(applicant);
    }

    @Override
    public JobPostApplicantsResponse getApplicants(Long postId) {
        return JobPostApplicantsResponse.of(postId, jobPostApplicantRepository, userRepository);
    }

    @Override
    public List<PostJobResponse> getJobPosts() {
        return jobBoardRepository.findAll().stream().map(PostJobResponse::of).toList();
    }
}
