package com.hgyr.nonsanroy.data.dto.bet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//	private LocalDateTime betDate;

	private String chosenResult;

	private String matchResult;

	private String status;

}
