package com.evoluxiontech.evoxa_hiring_portal.candidate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evoluxiontech.evoxa_hiring_portal.candidate.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	
	 boolean existsByEmail(String email);
	 boolean existsByMobile(Long mobile);
	 Optional<Candidate> findByEmail(String email);

}
