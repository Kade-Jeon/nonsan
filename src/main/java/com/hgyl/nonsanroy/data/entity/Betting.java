package com.hgyl.nonsanroy.data.entity;

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
@Table(name = "betting")
public class Betting extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameNo;

	@Column(nullable = false)
	private int AwayScore;

	@Column(nullable = false)
	private String AwayTeam;

	@Column(nullable = false)
	private int HomeScore;

	@Column(nullable = false)
	private String HomeTeam;

	@Column(nullable = false)
	private LocalDateTime StartTime;

	@Column(nullable = false)
	private LocalDateTime EndTime;

	@Column(nullable = false)
	private String Result;

}
