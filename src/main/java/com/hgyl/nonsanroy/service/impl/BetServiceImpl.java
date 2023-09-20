package com.hgyl.nonsanroy.service.impl;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;
import com.hgyl.nonsanroy.data.repository.SportsMatchRepository;
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
	 * @fieldDeclarationNote sportsMatchRepository
	 * @purpose holds the reference to an instance of SportsMatchRepository
	 * @otherNotes (private)Accessible only in this class and (final)value cannot be changed
	 * Should not be autowired due to testing and maintenance purposes
	 * @requiredFor
	 * @author 명원식
	 */
	private final SportsMatchRepository sportsMatchRepository;

	/**
	 * @constructorNote BetServiceImpl Constructor
	 * @purpose inject SportsMatchRepository dependency into BetServiceImpl
	 * Autowired annotation allows Spring to automatically provide an instance of SportsMatchRepository
	 * @requiredFor
	 * @author 명원식
	 */
	@Autowired
	public BetServiceImpl(SportsMatchRepository sportsMatchRepository) {
		this.sportsMatchRepository = sportsMatchRepository;
	}

	@Override
	public SportsMatch getMatch(Integer matchNo) {
	return sportsMatchRepository.getByMatchNo(matchNo);
	}

	public SportsMatch addMatchForm(SportsMatch sportsMatch){
	return sportsMatch;
	}

	public SportsMatch saveMatch(SportsMatch sportsMatch){
	return sportsMatch;
	}

	public void deleteMatch(Integer matchNo) {
	}

}
