package com.hgyr.blogpd.repository;

import com.hgyr.blogpd.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
