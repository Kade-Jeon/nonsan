package com.hgyr.nonsanroy.data.dto.bet;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @classNote BetCartDto
 * @purpose brings params from betMain cart to betCart
 * @author 명원식
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetCartDto {

	private Integer matchNo;

	private double odds;

	private String homeTeam;

	private String awayTeam;

	private String matchType;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime matchEnd;

	/**
	 * @constructorNote getCartDataMap
	 * @purpose to send all BetCartDto values at once as a map
	 * @return map
	 */
	@ModelAttribute
	public Map<String, Object> getCartDataMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("matchNo", this.matchNo);
		map.put("odds", this.odds);
		map.put("homeTeam", this.homeTeam);
		map.put("awayTeam", this.awayTeam);
		map.put("matchType", this.matchType);
		// map.put("matchEnd", this.matchEnd);

		return map;
	}

}
