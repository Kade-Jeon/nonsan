package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @classNote MatchRepository
 * @purpose Repository pattern for interacting with DB (match table)
 * @requiredFor
 * @author 명원식
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

	Match findByMatchNo(Integer matchNo);

	/**
	 * @queryNote saveMatch (경기 추가하기)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	Match saveMatch(Match match);

	List<Match> findAll();

	Match findByAwayOdds(double AwayOdds);
	Match findByHomeOdds(double HomeOdds);



}
