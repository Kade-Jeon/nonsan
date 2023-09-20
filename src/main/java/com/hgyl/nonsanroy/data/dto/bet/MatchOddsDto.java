package com.hgyl.nonsanroy.data.dto.bet;

import lombok.*;

/**
 * @classNote MatchOddsDto
 * @purpose
 * @requiredFor
 * @author 명원식
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchOddsDto {

	private Integer matchNo;

	private Integer betNo;

	private double awayTeamOdds;

	private double homeTeamOdds;

	private double winningPot;

	private long betAmount;
}
