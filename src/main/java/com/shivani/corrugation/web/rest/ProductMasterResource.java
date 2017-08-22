package com.shivani.corrugation.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.shivani.corrugation.domain.ProductMaster;

import com.shivani.corrugation.repository.ProductMasterRepository;
import com.shivani.corrugation.repository.search.ProductMasterSearchRepository;
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
 * REST controller for managing ProductMaster.
 */
@RestController
@RequestMapping("/api")
public class ProductMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProductMasterResource.class);

    private static final String ENTITY_NAME = "productMaster";

    private final ProductMasterRepository productMasterRepository;

    private final ProductMasterSearchRepository productMasterSearchRepository;

    public ProductMasterResource(ProductMasterRepository productMasterRepository, ProductMasterSearchRepository productMasterSearchRepository) {
        this.productMasterRepository = productMasterRepository;
        this.productMasterSearchRepository = productMasterSearchRepository;
    }

    /**
     * POST  /product-masters : Create a new productMaster.
     *
     * @param productMaster the productMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productMaster, or with status 400 (Bad Request) if the productMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-masters")
    @Timed
    public ResponseEntity<ProductMaster> createProductMaster(@RequestBody ProductMaster productMaster) throws URISyntaxException {
        log.debug("REST request to save ProductMaster : {}", productMaster);
        if (productMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new productMaster cannot already have an ID")).body(null);
        }
        ProductMaster result = productMasterRepository.save(productMaster);
        productMasterSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/product-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-masters : Updates an existing productMaster.
     *
     * @param productMaster the productMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productMaster,
     * or with status 400 (Bad Request) if the productMaster is not valid,
     * or with status 500 (Internal Server Error) if the productMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-masters")
    @Timed
    public ResponseEntity<ProductMaster> updateProductMaster(@RequestBody ProductMaster productMaster) throws URISyntaxException {
        log.debug("REST request to update ProductMaster : {}", productMaster);
        if (productMaster.getId() == null) {
            return createProductMaster(productMaster);
        }
        ProductMaster result = productMasterRepository.save(productMaster);
        productMasterSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-masters : get all the productMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of productMasters in body
     */
    @GetMapping("/product-masters")
    @Timed
    public ResponseEntity<List<ProductMaster>> getAllProductMasters(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of ProductMasters");
        Page<ProductMaster> page = productMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/product-masters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /product-masters/:id : get the "id" productMaster.
     *
     * @param id the id of the productMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productMaster, or with status 404 (Not Found)
     */
    @GetMapping("/product-masters/{id}")
    @Timed
    public ResponseEntity<ProductMaster> getProductMaster(@PathVariable Long id) {
        log.debug("REST request to get ProductMaster : {}", id);
        ProductMaster productMaster = productMasterRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productMaster));
    }

    /**
     * DELETE  /product-masters/:id : delete the "id" productMaster.
     *
     * @param id the id of the productMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProductMaster : {}", id);
        productMasterRepository.delete(id);
        productMasterSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/product-masters?query=:query : search for the productMaster corresponding
     * to the query.
     *
     * @param query the query of the productMaster search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/product-masters")
    @Timed
    public ResponseEntity<List<ProductMaster>> searchProductMasters(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of ProductMasters for query {}", query);
        Page<ProductMaster> page = productMasterSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/product-masters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
