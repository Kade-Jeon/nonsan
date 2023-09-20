package com.hgyl.nonsanroy.data.repository;

import com.hgyl.nonsanroy.data.entity.bet.SportsMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @classNote SportsMatchRepository
 * @purpose Repository pattern for interacting with DB (SportsMatch table)
 * @requiredFor
 * @author 명원식
 */
@Repository
public interface SportsMatchRepository extends JpaRepository<SportsMatch, Integer> {

	SportsMatch getByMatchNo(Integer matchNo);
}
