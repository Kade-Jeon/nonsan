package com.hgyr.nonsanroy.data.repository;

import com.hgyr.nonsanroy.data.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface PointRepository extends JpaRepository<Point, String> {
	}
