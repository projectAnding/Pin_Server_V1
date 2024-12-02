package com.server.pin.domain.boards.clubBoard.service;

import com.server.pin.domain.boards.clubBoard.dto.request.CreateClubBoardRequest;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostDetailResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.CreateClubBoardResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClubBoardService {
    CreateClubBoardResponse createClubPost(CreateClubBoardRequest request, MultipartFile file);
    ClubPostDetailResponse getClubPost(Long id);
    List<ClubPostResponse> getClubPosts();
}
