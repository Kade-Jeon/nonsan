package com.hgyr.nonsanroy.data.entity.bet;

import com.hgyr.nonsanroy.data.entity.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @classNote Match (스포츠 경기 및 내기 정보 관련 Entity)
 * @purpose
 * @author 명원식
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "sports_match")
public class Match extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchNo;

	@Column(nullable = false)
	private String matchType;

	@Column(nullable = false)
	private String awayTeam;

	/**
	 * @columnNote awayOdds (Hometeam 패배 확률)
	 * @purpose
	 * @author 명원식
	 */
	@Column(nullable = false)
	private double awayOdds;

	@Column(nullable = false)
	private String homeTeam;

	@Column(nullable = false)
	private double homeOdds;

	@Column(nullable = false)
	private LocalDateTime matchStart;

	@Column(nullable = false)
	private LocalDateTime matchEnd;

}
