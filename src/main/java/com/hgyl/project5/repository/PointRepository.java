package com.hgyl.project5.repository;

import com.hgyl.project5.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
