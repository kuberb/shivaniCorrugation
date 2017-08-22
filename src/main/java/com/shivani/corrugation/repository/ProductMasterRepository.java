package com.shivani.corrugation.repository;

import com.shivani.corrugation.domain.ProductMaster;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the ProductMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster,Long> {
    
    @Query("select distinct product_master from ProductMaster product_master left join fetch product_master.papers")
    List<ProductMaster> findAllWithEagerRelationships();

    @Query("select product_master from ProductMaster product_master left join fetch product_master.papers where product_master.id =:id")
    ProductMaster findOneWithEagerRelationships(@Param("id") Long id);
    
}
