package com.hgyl.nonsanroy.service;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;

/**
 * @author 명원식
 */
public interface BetService {
	// 경기 정보 받기
	SportsMatch getMatch(Integer matchNo);

	// 경기 추가 메뉴로 이동
	SportsMatch addMatchPage(SportsMatch sportsMatch);

	// 경기 추가 확인
	SportsMatch saveMatch(SportsMatch sportsMatch);

	// 경기 정보 삭제
	void deleteMatch(Integer matchNo);

}
