package com.hgyr.nonsanroy.data.dto.bet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @classNote MatchDto
 * @purpose
 * @author 명원식
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {

	private Integer matchNo;

	private String matchType;

	private String awayTeam;

	private double awayOdds;

	private String homeTeam;

	private double homeOdds;

	private LocalDateTime matchStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime matchEnd;

}
