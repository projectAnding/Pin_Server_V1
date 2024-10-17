package com.server.pin.domain.posts.service.impl;

import com.server.pin.domain.posts.repository.PostRepository;
import com.server.pin.domain.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;


    @Override
    public void postEventPost() {

    }
}
