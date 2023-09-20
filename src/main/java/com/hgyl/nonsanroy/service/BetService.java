package com.hgyl.nonsanroy.service;

import com.hgyl.nonsanroy.data.entity.bet.Match;

/**
 * @classNote BetService
 * @purpose
 * @requiredFor
 * @author 명원식
 */
public interface BetService {

	/**
	 * @methodDeclarationNote addMatchForm (경기 추가 메뉴로 이동)
	 * @purpose 이 메서드를 호출하면, 경기 추가 메뉴로 이동합니다
	 * @requiredFor
	 * @author 명원식
	 */
	Match addMatchForm(Match match);

	/**
	 * @methodDeclarationNote addMatch (경기 추가 접수 확인)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	Match addMatch(Match match);

	/**
	 * @methodDeclarationNote removeMatch (경기 정보 제거)
	 * @purpose
	 * @requiredFor
	 * @author 명원식
	 */
	void removeMatch(Integer matchNo);

}
