package com.shivani.corrugation.repository.search;

import com.shivani.corrugation.domain.PaperMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaperMaster entity.
 */
public interface PaperMasterSearchRepository extends ElasticsearchRepository<PaperMaster, Long> {
}
