package com.hgyr.nonsanroy.service;

import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @classNote BetService
 * @purpose
 * @author 명원식
 */
public interface BetService {

	/**
	 * @methodDeclarationNote getMatch (경기 선택하기)
	 * @purpose
	 * @author 명원식
	 */
	Match getMatch(Integer matchNo);

	/**
	 * @methodDeclarationNote addMatchForm (경기 추가 메뉴로 이동)
	 * @purpose 이 메서드를 호출하면, 경기 추가 메뉴로 이동합니다
	 * @author 명원식
	 */
	Match addMatchForm(Match match);

	/**
	 * @methodDeclarationNote addMatch (경기 추가 접수 확인)
	 * @purpose
	 * @author 명원식
	 */
	Match addMatch(Match match);

	/**
	 * @methodDeclarationNote removeMatch (경기 정보 제거)
	 * @purpose 이름 변경 필요할듯
	 * @author 명원식
	 */
	void removeMatch(Integer matchNo);

	List<Match> getAllMatches();

	List<Bet> getAllBets();

	void saveBet(BetDto betDto);

	Double getPoint(String uid);

	String updatePoint(String uid, String point);

	// LocalDateTime findMatchEndByMatchNo(Integer matchNo);
}
