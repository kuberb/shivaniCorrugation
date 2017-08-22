package com.shivani.corrugation.repository;

import com.shivani.corrugation.domain.PaperMaster;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PaperMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaperMasterRepository extends JpaRepository<PaperMaster,Long> {
    
}
