package com.hgyl.nonsanroy.service;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;

/**
 * @classNote BetService
 * @purpose
 * @requiredFor
 * @author 명원식
 */
public interface BetService {

	/**
	 * @methodDeclarationNote getMatch (경기 정보 가져오기)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	SportsMatch getMatch(Integer matchNo);

	/**
	 * @methodDeclarationNote addMatchForm (경기 추가 메뉴로 이동)
	 * @purpose 이 메서드를 호출하면, 경기 추가 메뉴로 이동합니다
	 * @requiredFor
	 * @author 명원식
	 */
	SportsMatch addMatchForm(SportsMatch sportsMatch);

	/**
	 * @methodDeclarationNote saveMatch (경기 추가 접수 확인)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	SportsMatch saveMatch(SportsMatch sportsMatch);

	/**
	 * @methodDeclarationNote deleteMatch (경기 정보 삭제)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	void deleteMatch(Integer matchNo);

}
