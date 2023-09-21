package com.hgyr.nonsanroy.service.impl;

import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.data.repository.MatchRepository;
import com.hgyr.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @classNote: BetServiceImpl
 * @purpose:
 * @author 명원식
 */
@Service
public class BetServiceImpl implements BetService {

	/**
	 * @fieldDeclarationNote matchRepository
	 * @purpose holds the reference to an instance of matchRepository
	 * @otherNote (private)Accessible only in this class and (final)value cannot be changed
	 * Should not be autowired due to testing and maintenance purposes
	 * @author 명원식
	 */
	private final MatchRepository matchRepository;

	/**
	 * @constructorNote BetServiceImpl Constructor
	 * @purpose inject MatchRepository dependency into BetServiceImpl
	 * Autowired annotation allows Spring to automatically provide an instance of MatchRepository
	 * @author 명원식
	 */
	@Autowired
	public BetServiceImpl(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public Match getMatch(Integer matchNo) {
		return getMatch(matchNo);
	}

	public Match addMatchForm(Match match) {
		return match;
	}

	public Match addMatch(Match match) {
		return match;
	}

	public void removeMatch(Integer matchNo) {
	}


	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}


}
