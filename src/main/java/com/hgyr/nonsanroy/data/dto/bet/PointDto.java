package com.hgyr.nonsanroy.data.dto.bet;

import com.hgyr.nonsanroy.data.entity.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class PointDto {

	private String uid;

	@Min(value = 0, message = " point 는 음수 불가.")
	private Double point;

	@Max(value = 100_000_000L, message = "Point 최대값 100억.")
	@Min(value = 0, message = "Add point 는 음수 불가.")
	private Double addPoint;

	@Max(value = 100_000_000L, message = "Point 최대값 100억.")
	@Min(value = 0, message = "minus point 는 음수 불가.")
	private Double minusPoint;
	private String nickName;
	private LocalTime insertDateTime;

	@Builder
	public PointDto(String uid, Double point, Double addPoint, Double minusPoint, String nickName, LocalTime insertDateTime) {
		this.uid = uid;
		this.point = point;
		this.addPoint = addPoint;
		this.minusPoint = minusPoint;
		this.nickName = nickName;
		this.insertDateTime = insertDateTime;
	}

	public static PointDto pointFactory(Point myPoint) {
		return PointDto.builder()
				.uid(myPoint.getUid())
				.point(myPoint.getPoint())
				.addPoint(myPoint.getAddPoint())
				.minusPoint(myPoint.getMinusPoint())
				.nickName(myPoint.getNickName())
				.insertDateTime(LocalTime.now())
				.build();
	}
}