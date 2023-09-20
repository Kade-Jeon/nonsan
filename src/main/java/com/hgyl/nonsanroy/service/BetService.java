package com.hgyl.nonsanroy.service;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;

/**
 * @classNote
 * @author 명원식
 */
public interface BetService {

	/**
	 * @MethodNote 경기 정보 받기
	 * @author 명원식
	 */
	SportsMatch getMatch(Integer matchNo);

	/**
	 * @MethodNote 경기 추가 메뉴로 이동
	 * @author 명원식
	 */
	SportsMatch addMatchPage(SportsMatch sportsMatch);

	/**
	 * @MethodNote 경기 추가 확인
	 * @author 명원식
	 */
	SportsMatch saveMatch(SportsMatch sportsMatch);

	/**
	 * @MethodNote 경기 정보 삭제
	 * @author 명원식
	 */
	void deleteMatch(Integer matchNo);

}
