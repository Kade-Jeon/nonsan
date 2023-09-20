package com.hgyl.nonsanroy.data.entity.bet;

import com.hgyl.nonsanroy.data.entity.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @classNote sportsMatch (스포츠 경기 관련 Entity)
 * @purpose
 * @requiredFor
 * @author 명원식
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "sportsMatch")
public class SportsMatch extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchNo;

	@Column(nullable = false)
	private String matchType;

	@Column(nullable = false)
	private Integer awayScore;

	@Column(nullable = false)
	private String awayTeam;

	@Column(nullable = false)
	private Integer homeScore;

	@Column(nullable = false)
	private String homeTeam;

	@Column(nullable = false)
	private LocalDateTime startTime;

	@Column(nullable = false)
	private LocalDateTime endTime;

	@Column(nullable = false)
	private String result;

}
