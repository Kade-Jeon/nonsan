package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 명원식
 * @note
 */

public interface SportsMatchRepository extends JpaRepository<SportsMatch, Integer> {

	SportsMatch getByMatchNo(Integer matchNo);
}
