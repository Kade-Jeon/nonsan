package com.hgyr.nonsanroy.data.dto.bet;

import lombok.*;
import java.time.LocalDateTime;

/**
 * @classNote BetDto
 * @purpose
 * @requiredFor
 * @author 명원식
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetDto {

	private Integer betNo;

	private Integer awayScore;

	private Integer homeScore;

	private double payout;

	private long betAmount;

	private LocalDateTime betDate;

	private String chosenResult;

	private String matchResult;

	private String status;
}
