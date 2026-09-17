package com.evoluxiontech.evoxa_hiring_portal.candidate.dao;


import org.springframework.stereotype.Repository;

import com.evoluxiontech.evoxa_hiring_portal.candidate.entity.Candidate;
import com.evoluxiontech.evoxa_hiring_portal.candidate.repository.CandidateRepository;
import com.evoluxiontech.evoxa_hiring_portal.exception.DataNotFoundException;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CandidateDao {
	
 private final CandidateRepository candidaterepository;
	
	public Candidate save(Candidate data)
	{
		return candidaterepository.save(data);
		
	}
	public boolean emailUnique(String email)
	{
		return !candidaterepository.existsByEmail(email);
		
	}
	public boolean mobileUnique(Long mobile)
	{
		return !candidaterepository.existsByMobile(mobile);
		
	}
	public Candidate findByEmail(String email)
	{
		return candidaterepository.findByEmail(email).orElseThrow(()->new DataNotFoundException("Email Doesnot Exist"));
	}
	
	

}
