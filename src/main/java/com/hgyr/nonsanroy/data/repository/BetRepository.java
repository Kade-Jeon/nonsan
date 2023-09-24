package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {

	List<Bet> findAll();

}
