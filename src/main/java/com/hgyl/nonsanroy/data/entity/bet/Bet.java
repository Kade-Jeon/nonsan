package com.hgyl.nonsanroy.data.entity.bet;

import com.hgyl.nonsanroy.data.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @classNote Bet (내기 추가 정보 관련 Entity)
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
@Table(name = "bet")
public class Bet extends BaseEntity {


	/**
	 * @columnNote betNo
	 * @purpose Primary Key
	 * @requiredFor
	 * @author 명원식
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer betNo;

	@Column(nullable = false)
	private Integer awayScore;

	@Column(nullable = false)
	private Integer homeScore;

	@Column(nullable = false)
	private double payout;

	@Column(nullable = false)
	private long betAmount;

	@Column(nullable = false)
	private LocalDateTime betDate;

	/**
	 * @columnNote chosenResult
	 * @purpose chosen result ex) Hometeam 패배
	 * @requiredFor
	 * @hidden
	 * @author 명원식
	 */
	@Column(nullable = false)
	private String chosenResult;

	/**
	 * @columnNote matchResult
	 * @purpose actual match result ex) Hometeam 승리
	 * @requiredFor
	 * @author 명원식
	 */
	@Column(nullable = false)
	private String matchResult;

	/**
	 * @columnNote status
	 * @purpose if chosenResult == matchResult, return status = correct
	 * if chosenResult != matchResult, return status = incorrect
	 * @requiredFor if status = correct, receive payout button appears
	 * if payout is received, return status = correct + payout claimed
	 * @author 명원식
	 */
	@Column(nullable = false)
	private String status;

}
