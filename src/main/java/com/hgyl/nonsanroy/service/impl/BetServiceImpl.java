package com.hgyl.nonsanroy.service.impl;

import com.hgyl.nonsanroy.data.entity.bet.Match;
import com.hgyl.nonsanroy.data.repository.MatchRepository;
import com.hgyl.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @classNote: BetServiceImpl
 * @purpose:
 * @requiredFor:
 * @author 명원식
 */
@Service
public class BetServiceImpl implements BetService {

	/**
	 * @fieldDeclarationNote matchRepository
	 * @purpose holds the reference to an instance of matchRepository
	 * @otherNote (private)Accessible only in this class and (final)value cannot be changed
	 * Should not be autowired due to testing and maintenance purposes
	 * @requiredFor
	 * @author 명원식
	 */
	private final MatchRepository matchRepository;

	/**
	 * @constructorNote BetServiceImpl Constructor
	 * @purpose inject MatchRepository dependency into BetServiceImpl
	 * Autowired annotation allows Spring to automatically provide an instance of MatchRepository
	 * @requiredFor
	 * @author 명원식
	 */
	@Autowired
	public BetServiceImpl(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public Match addMatchForm(Match match){
	return match;
	}

	public Match addMatch(Match match){
	return match;
	}

	/*
	 * submitBetForm
	 * submitBet
	 *
	 *
	 */

	public void removeMatch(Integer matchNo) {
	}

}
