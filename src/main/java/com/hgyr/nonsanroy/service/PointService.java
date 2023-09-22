package com.hgyr.nonsanroy.service;

import com.hgyr.nonsanroy.data.dto.bet.PointDto;
import com.hgyr.nonsanroy.data.entity.Point;
import com.hgyr.nonsanroy.data.repository.PointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService {

	private PointRepository pointRepository;

	public PointService(PointRepository pointRepository) {
		this.pointRepository = pointRepository;
	}

	@Transactional
	public PointDto myPoint(String userId) {
		Point myPoint = pointRepository.findById(userId).orElse(new Point());

		return PointDto.builder()
				.uid(myPoint.getUid())
				.point(myPoint.getPoint())
				.addPoint(myPoint.getAddPoint())
				.nickName(myPoint.getNickName())
				.build();
	}

	@Transactional
	public void recharge(PointDto myPointDTO) {
		String userId = myPointDTO.getUid();
		if (userId == null) {
			throw new IllegalArgumentException("유저 아이디 Null");
		}

		Double depositAmount = myPointDTO.getAddPoint();
		Point existingPoint = pointRepository.findById(userId).orElse(new Point());

		Double currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
		existingPoint.setPoint(currentPoint + depositAmount);
	}

	@Transactional
	public PointDto deposit(String userId) {
		Point myPoint = pointRepository.findById(userId).orElse(new Point());

		return PointDto.builder()
				.uid(myPoint.getUid())
				.point(myPoint.getPoint())
				.nickName(myPoint.getNickName())
				.build();


	}
	@Transactional
	public PointDto withdrawPage(String userId) {
		Point myPoint = pointRepository.findById(userId).orElse(new Point());

		return PointDto.builder()
				.uid(myPoint.getUid())
				.point(myPoint.getPoint())
				.minusPoint(myPoint.getMinusPoint())
				.nickName(myPoint.getNickName())
				.build();
	}

	@Transactional
	public void withdraw(PointDto myPointDTO) {
		String userId = myPointDTO.getUid();
		Double depositAmount = myPointDTO.getMinusPoint();

		// 기존 포인트 엔티티를 찾거나 새로 생성
		Point existingPoint = pointRepository.findById(userId).orElse(new Point());

		// 기존 포인트에 포인트 마이너스
		Double currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
		existingPoint.setPoint(currentPoint - depositAmount);

	}

	@Transactional
	public Double currentPoint(String userId) {
		Point myPoint = pointRepository.findById(userId).orElse(new Point());
		return myPoint.getPoint(); // 사용자의 현재 포인트 반환
	}
}