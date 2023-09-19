package com.hgyl.nonsanroy.data.entity.bet;

import com.hgyl.nonsanroy.data.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "gameOdds")
public class GameOdds extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameNo;

	@Column(nullable = false)
	private double awayTeamOdds;

	@Column(nullable = false)
	private double homeTeamOdds;

	@Column(nullable = false)
	private double winningPot;

}
