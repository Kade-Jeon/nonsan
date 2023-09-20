package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @classNote MatchOddsRepository
 * @purpose Repository pattern for interacting with DB (MatchOdds table)
 * @requiredFor
 * @author 명원식
 */

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Integer> {
}
