package com.shivani.corrugation.repository;

import com.shivani.corrugation.domain.Reel;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Reel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReelRepository extends JpaRepository<Reel,Long> {
    
}
