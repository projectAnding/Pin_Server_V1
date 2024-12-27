package com.server.pin.domain.boards.jobBoard.repository;

import com.server.pin.domain.boards.jobBoard.domain.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobBoardRepository extends JpaRepository<JobPost, Long> {
}
