package com.server.pin.domain.boards.jobBoard.domain.entity;

import com.server.pin.domain.user.domain.entity.UserEntity;
import com.server.pin.global.common.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobposts")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class JobPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imageUrl;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity author;

    private LocalDate period;

    private Long memberLimit;

    private String content;

    @OneToMany
    @JoinColumn(name = "id")
    public List<JobPostApplicant> applicants  = new ArrayList<>();

    public void addApplicant(JobPostApplicant applicant) {
        this.applicants.add(applicant);
    }
    public Long nowMember() {
        return (this.applicants != null) ? (long) this.applicants.size() : 0;
    }
}
