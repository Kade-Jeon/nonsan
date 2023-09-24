package com.hgyr.nonsanroy.service.impl;

import com.hgyr.nonsanroy.data.dto.bet.BetDto;
import com.hgyr.nonsanroy.data.entity.bet.Bet;
import com.hgyr.nonsanroy.data.entity.bet.Match;
import com.hgyr.nonsanroy.data.repository.BetRepository;
import com.hgyr.nonsanroy.data.repository.MatchRepository;
import com.hgyr.nonsanroy.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
	 * @fieldDeclarationNote matchRepository
	 * @purpose holds the reference to an instance of matchRepository
	 * @otherNote (private)Accessible only in this class and (final)value cannot be changed
	 * Should not be autowired due to testing and maintenance purposes
	 * @author 명원식
	 */
	private final BetRepository betRepository;
	/**
	 * @constructorNote BetServiceImpl Constructor
	 * @purpose inject Match/Bet Repository dependency into BetServiceImpl
	 * Autowired annotation allows Spring to automatically provide an instance of Match/Bet Repository
	 * @author 명원식
	 */
	@Autowired
	public BetServiceImpl(MatchRepository matchRepository,BetRepository betRepository) {
		this.matchRepository = matchRepository;
		this.betRepository = betRepository;
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

	public List<Bet> getAllBets() {
		return betRepository.findAll();
	}

	public void saveBet(BetDto betDto) {
		Bet bet = new Bet();

		bet.setAwayScore(0); // 6
		bet.setHomeScore(0); // 8

		bet.setBetNo(betDto.getBetNo()); // 1
		bet.setBetAmount(betDto.getBetAmount()); // 9
		bet.setPayout(betDto.getPayout()); // 10

		betRepository.save(bet);
	}

	public Double getPoint(String uid){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:1777")
				.path("/valid/point/{var1}")
				.encode()
				.build()
				.expand(uid)
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Double> responseEntity = restTemplate.postForEntity(uri, uid, Double.class);
		return responseEntity.getBody();
	}

	public String updatePoint(String uid, String point){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:1777")
				.path("/valid/point/{var1}/{var2}")
				.encode()
				.build()
				.expand(uid, point)
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, uid, String.class);
		return responseEntity.getBody();
	}
}
