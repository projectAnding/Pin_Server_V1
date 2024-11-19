package com.server.pin.domain.posts.domain.entity;

import com.server.pin.domain.posts.domain.enums.PostCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "TABLE_POST")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(nullable = false)
    @NotBlank
    private String content;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "postCategory")
    private PostCategory postCategory;


}
