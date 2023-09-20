package com.hgyl.nonsanroy.data.entity.bet;

import com.hgyl.nonsanroy.data.entity.BaseEntity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @classNote 스포츠 경기 관련 Entity
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
	private Integer gameNo;

	@Column(nullable = false)
	private Integer AwayScore;

	@Column(nullable = false)
	private String AwayTeam;

	@Column(nullable = false)
	private Integer HomeScore;

	@Column(nullable = false)
	private String HomeTeam;

	@Column(nullable = false)
	private LocalDateTime StartTime;

	@Column(nullable = false)
	private LocalDateTime EndTime;

	@Column(nullable = false)
	private String Result;

}
