package com.server.pin.domain.boards.jobBoard.comtroller;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPost;
import com.server.pin.domain.boards.jobBoard.dto.request.GetApplicantsRequest;
import com.server.pin.domain.boards.jobBoard.dto.request.PatchJobApplicantRequest;
import com.server.pin.domain.boards.jobBoard.dto.request.PostJobRequest;
import com.server.pin.domain.boards.jobBoard.dto.response.JobPostApplicantsResponse;
import com.server.pin.domain.boards.jobBoard.dto.response.PostJobResponse;
import com.server.pin.domain.boards.jobBoard.service.JobBoardService;
import com.server.pin.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/job")
@RequiredArgsConstructor
@Tag(name = "구인구직 게시판 API")
public class JobBoardController {
    private final JobBoardService jobBoardService;

    @PostMapping
    @Operation(summary = "게시물 게시")
    public ResponseEntity<BaseResponse<PostJobResponse>> postJob(@RequestBody PostJobRequest request) {
        return BaseResponse.of(jobBoardService.postJob(request), 200 ,"구인구직 게시물 게시 성공");
    }

    @PostMapping("/{postId}/application")
    @Operation(summary = "구인구직 게시물 지원")
    public ResponseEntity<BaseResponse<Void>> applicationJob(@PathVariable Long postId) {
        jobBoardService.applicate(postId);
        return BaseResponse.of(null, 200, "지원 성공");
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PostJobResponse>>> getApplicants(){
        return BaseResponse.of(jobBoardService.getJobPosts(), 200, "구인구직 게시물 리스트 조회 성공");
    }

    @GetMapping("/{postId}")
    @Operation(summary = "구인구직 게시물 조회")
    public ResponseEntity<BaseResponse<PostJobResponse>> getJob(@PathVariable Long postId) {
        return BaseResponse.of(jobBoardService.getJob(postId), 200, "구인구직 게시물 조회 성공");
    }

    @PatchMapping("/application/agree")
    @Operation(summary = "신청자 승인")
    public ResponseEntity<BaseResponse<Void>> agreeApplicant(@RequestBody PatchJobApplicantRequest request) {
        jobBoardService.agreeApplicant(request);
        return BaseResponse.of(null, 200, "신청자 승인 성공");
    }

    @DeleteMapping("/application/deny")
    @Operation(summary = "신청자 거절")
    public ResponseEntity<BaseResponse<Void>> denyApplicant(@RequestBody PatchJobApplicantRequest request) {
        jobBoardService.denyApplicant(request);
        return BaseResponse.of(null, 200, "신청자 거절 성공");
    }

    @GetMapping("/candidates")
    @Operation(summary = "신청자 리스트 조회(승인된 인원은 앞쪽으로 정렬)", description = "마이페이지용")
    public ResponseEntity<BaseResponse<JobPostApplicantsResponse>> getCandidateJob(@RequestBody GetApplicantsRequest request) {
        return BaseResponse.of(jobBoardService.getApplicants(request.postId()), 200, "신청자 리스트 조회 성공");
    }
}
