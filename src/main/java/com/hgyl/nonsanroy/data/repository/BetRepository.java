package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @classNote BetRepository
 * @purpose Repository pattern for interacting with DB (bet table)
 * @requiredFor
 * @author 명원식
 */

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
}
