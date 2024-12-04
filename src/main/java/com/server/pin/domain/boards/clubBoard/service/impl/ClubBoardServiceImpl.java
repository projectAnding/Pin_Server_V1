package com.server.pin.domain.boards.clubBoard.service.impl;

import com.server.pin.domain.boards.boardAuthorMapper.controller.BoardAuthorMapperController;
import com.server.pin.domain.boards.boardAuthorMapper.domain.enums.BoardType;
import com.server.pin.domain.boards.clubBoard.domain.entity.ClubPost;
import com.server.pin.domain.boards.clubBoard.dto.request.CreateClubBoardRequest;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostDetailResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.ClubPostResponse;
import com.server.pin.domain.boards.clubBoard.dto.response.CreateClubBoardResponse;
import com.server.pin.domain.boards.clubBoard.repository.ClubPostRepository;
import com.server.pin.domain.boards.clubBoard.service.ClubBoardService;
import com.server.pin.domain.boards.enums.PostStatus;
import com.server.pin.domain.boards.exception.PostError;
import com.server.pin.domain.file.controller.FileController;
import com.server.pin.global.exception.CustomException;
import com.server.pin.global.security.holder.SecurityHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubBoardServiceImpl implements ClubBoardService {
    private final ClubPostRepository clubPostRepository;
    private final FileController fileController;
    private final SecurityHolder securityHolder;
    private final BoardAuthorMapperController authorMapper;


    @Override
    public CreateClubBoardResponse createClubPost(CreateClubBoardRequest request) { //, MultipartFile file

        ClubPost post = ClubPost.builder()
                .title(request.title())
                .author(securityHolder.getPrincipal())
                .period(request.period())
                .memberLimit(request.memberLimit())
                .content(request.content())
                .imageURL(request.imageURL())
                .status(PostStatus.POSTING)
                .build();

        post = clubPostRepository.save(post);

        try {
            authorMapper.postAuthorMap(post.getId(), securityHolder.getPrincipal().getId(), BoardType.CLUB);
        } catch (CustomException e) {
            throw new CustomException(PostError.POST_AUTHOR_MAP_FAILED);
        }

        return CreateClubBoardResponse.of(clubPostRepository.save(post));
    }


    @Override
    public ClubPostDetailResponse getClubPost(Long postId) {

        ClubPost post = clubPostRepository.findById(postId).orElseThrow(
                () ->  new CustomException(PostError.POST_NOT_FOUND)
        );

        return ClubPostDetailResponse.of(post);
    }

    @Override
    public List<ClubPostResponse> getClubPosts() {
        return clubPostRepository.findAllByStatus(PostStatus.POSTING).stream().map(ClubPostResponse::of).toList();
    }

}