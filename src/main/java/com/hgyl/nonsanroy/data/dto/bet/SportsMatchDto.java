package com.hgyl.nonsanroy.data.dto.bet;

import lombok.*;
import java.time.LocalDateTime;

/**
 * @classNote SportsMatchDto
 * @purpose
 * @requiredFor
 * @author 명원식
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportsMatchDto {

	private Integer matchNo;

	private String matchType;

	private Integer awayScore;

	private String awayTeam;

	private Integer homeScore;

	private String homeTeam;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private String result;

}
