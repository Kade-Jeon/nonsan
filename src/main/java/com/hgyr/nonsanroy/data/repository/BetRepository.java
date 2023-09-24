package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;

import java.util.List;

/**
 * @classNote BetRepository
 * @purpose Repository pattern for interacting with DB (bet table)
 * @author 명원식
 */

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {

	// Bet getPayout(double payout);

	// Bet getBetNo(Integer betNo);

	List<Bet> findAll();

}
