package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.entity.bet.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @classNote MatchRepository
 * @purpose Repository pattern for interacting with DB (match table)
 * @author 명원식
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

	/**
	Match findByMatchNo(Integer matchNo);
	**/


	/**
	 * @queryNote saveMatch (경기 추가하기)
	 * @purpose
	 * @author 명원식
	 */
	// Match saveMatch(Integer matchNo);


	/**
	Match findByAwayOdds(double AwayOdds);
	Match findByHomeOdds(double HomeOdds);
	**/

	/**
	 * @queryNote findAll
	 * @purpose 모든 경기 정보를 가져오기
	 * @author 명원식
	 */
	List<Match> findAll();




}
