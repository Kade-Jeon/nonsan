package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @classNote SportsMatchRepository Pattern for interacting with DB
 * @author 명원식
 */

public interface SportsMatchRepository extends JpaRepository<SportsMatch, Integer> {

	SportsMatch getByMatchNo(Integer matchNo);
}
