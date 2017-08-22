package com.shivani.corrugation.web.rest;

import com.shivani.corrugation.ShivaniCorrugationApp;

import com.shivani.corrugation.domain.PaperMaster;
import com.shivani.corrugation.repository.PaperMasterRepository;
import com.shivani.corrugation.repository.search.PaperMasterSearchRepository;
import com.shivani.corrugation.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PaperMasterResource REST controller.
 *
 * @see PaperMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShivaniCorrugationApp.class)
public class PaperMasterResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MILL = "AAAAAAAAAA";
    private static final String UPDATED_MILL = "BBBBBBBBBB";

    private static final Integer DEFAULT_GSM = 1;
    private static final Integer UPDATED_GSM = 2;

    private static final Integer DEFAULT_BF = 1;
    private static final Integer UPDATED_BF = 2;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    @Autowired
    private PaperMasterRepository paperMasterRepository;

    @Autowired
    private PaperMasterSearchRepository paperMasterSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPaperMasterMockMvc;

    private PaperMaster paperMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaperMasterResource paperMasterResource = new PaperMasterResource(paperMasterRepository, paperMasterSearchRepository);
        this.restPaperMasterMockMvc = MockMvcBuilders.standaloneSetup(paperMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PaperMaster createEntity(EntityManager em) {
        PaperMaster paperMaster = new PaperMaster()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .mill(DEFAULT_MILL)
            .gsm(DEFAULT_GSM)
            .bf(DEFAULT_BF)
            .price(DEFAULT_PRICE);
        return paperMaster;
    }

    @Before
    public void initTest() {
        paperMasterSearchRepository.deleteAll();
        paperMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createPaperMaster() throws Exception {
        int databaseSizeBeforeCreate = paperMasterRepository.findAll().size();

        // Create the PaperMaster
        restPaperMasterMockMvc.perform(post("/api/paper-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paperMaster)))
            .andExpect(status().isCreated());

        // Validate the PaperMaster in the database
        List<PaperMaster> paperMasterList = paperMasterRepository.findAll();
        assertThat(paperMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PaperMaster testPaperMaster = paperMasterList.get(paperMasterList.size() - 1);
        assertThat(testPaperMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPaperMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testPaperMaster.getMill()).isEqualTo(DEFAULT_MILL);
        assertThat(testPaperMaster.getGsm()).isEqualTo(DEFAULT_GSM);
        assertThat(testPaperMaster.getBf()).isEqualTo(DEFAULT_BF);
        assertThat(testPaperMaster.getPrice()).isEqualTo(DEFAULT_PRICE);

        // Validate the PaperMaster in Elasticsearch
        PaperMaster paperMasterEs = paperMasterSearchRepository.findOne(testPaperMaster.getId());
        assertThat(paperMasterEs).isEqualToComparingFieldByField(testPaperMaster);
    }

    @Test
    @Transactional
    public void createPaperMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paperMasterRepository.findAll().size();

        // Create the PaperMaster with an existing ID
        paperMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaperMasterMockMvc.perform(post("/api/paper-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paperMaster)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PaperMaster> paperMasterList = paperMasterRepository.findAll();
        assertThat(paperMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPaperMasters() throws Exception {
        // Initialize the database
        paperMasterRepository.saveAndFlush(paperMaster);

        // Get all the paperMasterList
        restPaperMasterMockMvc.perform(get("/api/paper-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paperMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].mill").value(hasItem(DEFAULT_MILL.toString())))
            .andExpect(jsonPath("$.[*].gsm").value(hasItem(DEFAULT_GSM)))
            .andExpect(jsonPath("$.[*].bf").value(hasItem(DEFAULT_BF)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPaperMaster() throws Exception {
        // Initialize the database
        paperMasterRepository.saveAndFlush(paperMaster);

        // Get the paperMaster
        restPaperMasterMockMvc.perform(get("/api/paper-masters/{id}", paperMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paperMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.mill").value(DEFAULT_MILL.toString()))
            .andExpect(jsonPath("$.gsm").value(DEFAULT_GSM))
            .andExpect(jsonPath("$.bf").value(DEFAULT_BF))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPaperMaster() throws Exception {
        // Get the paperMaster
        restPaperMasterMockMvc.perform(get("/api/paper-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaperMaster() throws Exception {
        // Initialize the database
        paperMasterRepository.saveAndFlush(paperMaster);
        paperMasterSearchRepository.save(paperMaster);
        int databaseSizeBeforeUpdate = paperMasterRepository.findAll().size();

        // Update the paperMaster
        PaperMaster updatedPaperMaster = paperMasterRepository.findOne(paperMaster.getId());
        updatedPaperMaster
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .mill(UPDATED_MILL)
            .gsm(UPDATED_GSM)
            .bf(UPDATED_BF)
            .price(UPDATED_PRICE);

        restPaperMasterMockMvc.perform(put("/api/paper-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPaperMaster)))
            .andExpect(status().isOk());

        // Validate the PaperMaster in the database
        List<PaperMaster> paperMasterList = paperMasterRepository.findAll();
        assertThat(paperMasterList).hasSize(databaseSizeBeforeUpdate);
        PaperMaster testPaperMaster = paperMasterList.get(paperMasterList.size() - 1);
        assertThat(testPaperMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPaperMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testPaperMaster.getMill()).isEqualTo(UPDATED_MILL);
        assertThat(testPaperMaster.getGsm()).isEqualTo(UPDATED_GSM);
        assertThat(testPaperMaster.getBf()).isEqualTo(UPDATED_BF);
        assertThat(testPaperMaster.getPrice()).isEqualTo(UPDATED_PRICE);

        // Validate the PaperMaster in Elasticsearch
        PaperMaster paperMasterEs = paperMasterSearchRepository.findOne(testPaperMaster.getId());
        assertThat(paperMasterEs).isEqualToComparingFieldByField(testPaperMaster);
    }

    @Test
    @Transactional
    public void updateNonExistingPaperMaster() throws Exception {
        int databaseSizeBeforeUpdate = paperMasterRepository.findAll().size();

        // Create the PaperMaster

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPaperMasterMockMvc.perform(put("/api/paper-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paperMaster)))
            .andExpect(status().isCreated());

        // Validate the PaperMaster in the database
        List<PaperMaster> paperMasterList = paperMasterRepository.findAll();
        assertThat(paperMasterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePaperMaster() throws Exception {
        // Initialize the database
        paperMasterRepository.saveAndFlush(paperMaster);
        paperMasterSearchRepository.save(paperMaster);
        int databaseSizeBeforeDelete = paperMasterRepository.findAll().size();

        // Get the paperMaster
        restPaperMasterMockMvc.perform(delete("/api/paper-masters/{id}", paperMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean paperMasterExistsInEs = paperMasterSearchRepository.exists(paperMaster.getId());
        assertThat(paperMasterExistsInEs).isFalse();

        // Validate the database is empty
        List<PaperMaster> paperMasterList = paperMasterRepository.findAll();
        assertThat(paperMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPaperMaster() throws Exception {
        // Initialize the database
        paperMasterRepository.saveAndFlush(paperMaster);
        paperMasterSearchRepository.save(paperMaster);

        // Search the paperMaster
        restPaperMasterMockMvc.perform(get("/api/_search/paper-masters?query=id:" + paperMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paperMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].mill").value(hasItem(DEFAULT_MILL.toString())))
            .andExpect(jsonPath("$.[*].gsm").value(hasItem(DEFAULT_GSM)))
            .andExpect(jsonPath("$.[*].bf").value(hasItem(DEFAULT_BF)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaperMaster.class);
        PaperMaster paperMaster1 = new PaperMaster();
        paperMaster1.setId(1L);
        PaperMaster paperMaster2 = new PaperMaster();
        paperMaster2.setId(paperMaster1.getId());
        assertThat(paperMaster1).isEqualTo(paperMaster2);
        paperMaster2.setId(2L);
        assertThat(paperMaster1).isNotEqualTo(paperMaster2);
        paperMaster1.setId(null);
        assertThat(paperMaster1).isNotEqualTo(paperMaster2);
    }
}
