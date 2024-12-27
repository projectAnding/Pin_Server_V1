package com.server.pin.domain.boards.jobBoard.service;

import com.server.pin.domain.boards.jobBoard.dto.request.PatchJobApplicantRequest;
import com.server.pin.domain.boards.jobBoard.dto.request.PostJobRequest;
import com.server.pin.domain.boards.jobBoard.dto.response.JobPostApplicantsResponse;
import com.server.pin.domain.boards.jobBoard.dto.response.PostJobResponse;

import java.util.List;

public interface JobBoardService {
    PostJobResponse postJob(PostJobRequest postJobRequest);
    void applicate(Long postId);
    PostJobResponse getJob(Long postId);
    void agreeApplicant(PatchJobApplicantRequest request);
    void denyApplicant(PatchJobApplicantRequest request);
    JobPostApplicantsResponse getApplicants(Long postId);
    List<PostJobResponse> getJobPosts();

}
