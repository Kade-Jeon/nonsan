package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @classNote MatchRepository
 * @purpose Repository pattern for interacting with DB (match table)
 * @requiredFor
 * @author 명원식
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
}
