package com.hgyl.nonsanroy.data.entity.bet;

import com.hgyl.nonsanroy.data.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @classNote MatchOdds (경기 내기 관련 Entity)
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
@Table(name = "matchOdds")
public class MatchOdds extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchNo;

	@Column(nullable = false)
	private double awayTeamOdds;

	@Column(nullable = false)
	private double homeTeamOdds;

	@Column(nullable = false)
	private double winningPot;

}
