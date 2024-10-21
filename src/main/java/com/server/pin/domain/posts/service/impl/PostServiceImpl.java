package com.server.pin.domain.posts.service.impl;

import com.server.pin.domain.posts.repository.PostRepository;
import com.server.pin.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void postEventPost() {

    }
}
