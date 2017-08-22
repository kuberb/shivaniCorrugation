package com.shivani.corrugation.repository.search;

import com.shivani.corrugation.domain.ProductMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ProductMaster entity.
 */
public interface ProductMasterSearchRepository extends ElasticsearchRepository<ProductMaster, Long> {
}
