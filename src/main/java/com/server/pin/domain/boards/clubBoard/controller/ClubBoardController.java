package com.server.pin.domain.boards.clubBoard.controller;

import com.server.pin.domain.boards.clubBoard.dto.request.CreateClubBoardRequest;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.CreateClubBoardResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostDetailResponse;
import com.server.pin.domain.boards.clubBoard.service.ClubBoardService;
import com.server.pin.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board/club")
@RequiredArgsConstructor
public class ClubBoardController {
    public final ClubBoardService clubBoardService;

    @PostMapping("/post")
    public ResponseEntity<BaseResponse<CreateClubBoardResponse>> createClubPost(@RequestBody CreateClubBoardRequest request) { //@RequestPart(value = "file") MultipartFile file,
        return BaseResponse.of(clubBoardService.createClubPost(request), 200, "동아리 게시물 생성 완료"); //, file
    }

    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<ClubPostDetailResponse>> getClubPostDetail(@PathVariable Long postId) {
        return BaseResponse.of(clubBoardService.getClubPost(postId), 200, "동아리 게시물 상세 불러오기 성공");
    }

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ClubPostResponse>>> getAllClubPost() {
        return BaseResponse.of(clubBoardService.getClubPosts(), 200, "동아리 게시물 목록 불러오기 성공");
    }

}
