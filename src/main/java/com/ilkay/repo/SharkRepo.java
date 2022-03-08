package com.ilkay.repo;

import com.ilkay.entity.Shark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharkRepo extends JpaRepository<Shark, Long> {

}

