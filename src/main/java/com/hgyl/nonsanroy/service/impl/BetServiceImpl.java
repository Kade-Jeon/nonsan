package com.hgyl.nonsanroy.service.impl;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;
import com.hgyl.nonsanroy.data.repository.SportsMatchRepository;
import com.hgyl.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @classNote
 * @author 명원식
 */
@Service
public class BetServiceImpl implements BetService {

	/**
	 * @fieldDeclarationNote
	 * declared field: sportsMatchRepository = holds the reference to an instance of SportsMatchRepository
	 * type (class): SportsMatchRepository
	 * private final: Accessible only in this class and cannot be changed
	 * @author 명원식
	 */
	private final SportsMatchRepository sportsMatchRepository;

	@Autowired
	public BetServiceImpl(SportsMatchRepository sportsMatchRepository) {
		this.sportsMatchRepository = sportsMatchRepository;
	}

	@Override
	public SportsMatch getMatch(Integer matchNo) {
	return sportsMatchRepository.getByMatchNo(matchNo);
	}

	public SportsMatch addMatchPage(SportsMatch sportsMatch){
	return sportsMatch;
	}

	public SportsMatch saveMatch(SportsMatch sportsMatch){
	return sportsMatch;
	}

	public void deleteMatch(Integer matchNo) {
	}

}
