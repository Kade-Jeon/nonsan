package com.hgyr.nonsanroy.data.dto.bet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;

/**
 * @classNote BetDto
 * @purpose
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

	private double odds;

	private long betAmount;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime betDate;

	private String chosenResult;

	private String matchResult;

	private String status;

}
