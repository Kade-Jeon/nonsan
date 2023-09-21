package com.hgyr.nonsanroy.data.dto.bet;

import lombok.*;
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

	private LocalDateTime matchEnd;

}
