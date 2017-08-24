package com.shivani.corrugation.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shivani.corrugation.domain.Reel;

import com.shivani.corrugation.repository.ReelRepository;
import com.shivani.corrugation.repository.search.ReelSearchRepository;
import com.shivani.corrugation.web.rest.util.HeaderUtil;
import com.shivani.corrugation.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Reel.
 */
@RestController
@RequestMapping("/api")
public class ReelResource {

    private final Logger log = LoggerFactory.getLogger(ReelResource.class);

    private static final String ENTITY_NAME = "reel";

    private final ReelRepository reelRepository;

    private final ReelSearchRepository reelSearchRepository;

    public ReelResource(ReelRepository reelRepository, ReelSearchRepository reelSearchRepository) {
        this.reelRepository = reelRepository;
        this.reelSearchRepository = reelSearchRepository;
    }

    /**
     * POST  /reels : Create a new reel.
     *
     * @param reel the reel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reel, or with status 400 (Bad Request) if the reel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reels")
    @Timed
    public ResponseEntity<Reel> createReel(@RequestBody Reel reel) throws URISyntaxException {
        log.debug("REST request to save Reel : {}", reel);
        if (reel.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new reel cannot already have an ID")).body(null);
        }
        Reel result = reelRepository.save(reel);
        reelSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/reels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reels : Updates an existing reel.
     *
     * @param reel the reel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reel,
     * or with status 400 (Bad Request) if the reel is not valid,
     * or with status 500 (Internal Server Error) if the reel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reels")
    @Timed
    public ResponseEntity<Reel> updateReel(@RequestBody Reel reel) throws URISyntaxException {
        log.debug("REST request to update Reel : {}", reel);
        if (reel.getId() == null) {
            return createReel(reel);
        }
        Reel result = reelRepository.save(reel);
        reelSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reels : get all the reels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reels in body
     */
    @GetMapping("/reels")
    @Timed
    public ResponseEntity<List<Reel>> getAllReels(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Reels");
        Page<Reel> page = reelRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reels/:id : get the "id" reel.
     *
     * @param id the id of the reel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reel, or with status 404 (Not Found)
     */
    @GetMapping("/reels/{id}")
    @Timed
    public ResponseEntity<Reel> getReel(@PathVariable Long id) {
        log.debug("REST request to get Reel : {}", id);
        Reel reel = reelRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reel));
    }

    /**
     * DELETE  /reels/:id : delete the "id" reel.
     *
     * @param id the id of the reel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reels/{id}")
    @Timed
    public ResponseEntity<Void> deleteReel(@PathVariable Long id) {
        log.debug("REST request to delete Reel : {}", id);
        reelRepository.delete(id);
        reelSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/reels?query=:query : search for the reel corresponding
     * to the query.
     *
     * @param query the query of the reel search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/reels")
    @Timed
    public ResponseEntity<List<Reel>> searchReels(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Reels for query {}", query);
        Page<Reel> page = reelSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/reels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
