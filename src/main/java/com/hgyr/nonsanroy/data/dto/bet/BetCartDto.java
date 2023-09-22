package com.hgyr.nonsanroy.data.dto.bet;

import lombok.*;
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

	private String teamName;

	private String teamName2;

	private String matchType;

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
		map.put("teamName", this.teamName);
		map.put("teamName2", this.teamName2);
		map.put("matchType", this.matchType);

		return map;
	}

}
