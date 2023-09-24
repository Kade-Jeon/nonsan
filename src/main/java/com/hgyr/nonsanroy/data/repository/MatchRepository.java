package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @classNote MatchRepository
 * @purpose Repository pattern for interacting with DB (match table)
 * @author 명원식
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

	/**
	 * @queryNote findAll
	 * @purpose 모든 경기 정보를 가져오기
	 * @author 명원식
	 */
	List<Match> findAll();

	LocalDateTime findMatchEndByMatchNo(Integer matchNo);
}
