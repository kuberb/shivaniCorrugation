package com.shivani.corrugation.repository.search;

import com.shivani.corrugation.domain.Reel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reel entity.
 */
public interface ReelSearchRepository extends ElasticsearchRepository<Reel, Long> {
}
