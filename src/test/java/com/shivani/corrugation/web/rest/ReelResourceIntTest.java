package com.shivani.corrugation.web.rest;

import com.shivani.corrugation.ShivaniCorrugationApp;

import com.shivani.corrugation.domain.Reel;
import com.shivani.corrugation.repository.ReelRepository;
import com.shivani.corrugation.repository.search.ReelSearchRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReelResource REST controller.
 *
 * @see ReelResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShivaniCorrugationApp.class)
public class ReelResourceIntTest {

    private static final Long DEFAULT_REEL_NUMBER = 1L;
    private static final Long UPDATED_REEL_NUMBER = 2L;

    private static final Long DEFAULT_DECAL = 1L;
    private static final Long UPDATED_DECAL = 2L;

    private static final Long DEFAULT_WEIGHT = 1L;
    private static final Long UPDATED_WEIGHT = 2L;

    private static final LocalDate DEFAULT_RECEIVED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RECEIVED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_LOT_NO = 1L;
    private static final Long UPDATED_LOT_NO = 2L;

    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private ReelSearchRepository reelSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReelMockMvc;

    private Reel reel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReelResource reelResource = new ReelResource(reelRepository, reelSearchRepository);
        this.restReelMockMvc = MockMvcBuilders.standaloneSetup(reelResource)
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
    public static Reel createEntity(EntityManager em) {
        Reel reel = new Reel()
            .reelNumber(DEFAULT_REEL_NUMBER)
            .decal(DEFAULT_DECAL)
            .weight(DEFAULT_WEIGHT)
            .receivedOn(DEFAULT_RECEIVED_ON)
            .lotNo(DEFAULT_LOT_NO);
        return reel;
    }

    @Before
    public void initTest() {
        reelSearchRepository.deleteAll();
        reel = createEntity(em);
    }

    @Test
    @Transactional
    public void createReel() throws Exception {
        int databaseSizeBeforeCreate = reelRepository.findAll().size();

        // Create the Reel
        restReelMockMvc.perform(post("/api/reels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reel)))
            .andExpect(status().isCreated());

        // Validate the Reel in the database
        List<Reel> reelList = reelRepository.findAll();
        assertThat(reelList).hasSize(databaseSizeBeforeCreate + 1);
        Reel testReel = reelList.get(reelList.size() - 1);
        assertThat(testReel.getReelNumber()).isEqualTo(DEFAULT_REEL_NUMBER);
        assertThat(testReel.getDecal()).isEqualTo(DEFAULT_DECAL);
        assertThat(testReel.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testReel.getReceivedOn()).isEqualTo(DEFAULT_RECEIVED_ON);
        assertThat(testReel.getLotNo()).isEqualTo(DEFAULT_LOT_NO);

        // Validate the Reel in Elasticsearch
        Reel reelEs = reelSearchRepository.findOne(testReel.getId());
        assertThat(reelEs).isEqualToComparingFieldByField(testReel);
    }

    @Test
    @Transactional
    public void createReelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reelRepository.findAll().size();

        // Create the Reel with an existing ID
        reel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReelMockMvc.perform(post("/api/reels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reel)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Reel> reelList = reelRepository.findAll();
        assertThat(reelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReels() throws Exception {
        // Initialize the database
        reelRepository.saveAndFlush(reel);

        // Get all the reelList
        restReelMockMvc.perform(get("/api/reels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reel.getId().intValue())))
            .andExpect(jsonPath("$.[*].reelNumber").value(hasItem(DEFAULT_REEL_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].decal").value(hasItem(DEFAULT_DECAL.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].receivedOn").value(hasItem(DEFAULT_RECEIVED_ON.toString())))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO.intValue())));
    }

    @Test
    @Transactional
    public void getReel() throws Exception {
        // Initialize the database
        reelRepository.saveAndFlush(reel);

        // Get the reel
        restReelMockMvc.perform(get("/api/reels/{id}", reel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reel.getId().intValue()))
            .andExpect(jsonPath("$.reelNumber").value(DEFAULT_REEL_NUMBER.intValue()))
            .andExpect(jsonPath("$.decal").value(DEFAULT_DECAL.intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.receivedOn").value(DEFAULT_RECEIVED_ON.toString()))
            .andExpect(jsonPath("$.lotNo").value(DEFAULT_LOT_NO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReel() throws Exception {
        // Get the reel
        restReelMockMvc.perform(get("/api/reels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReel() throws Exception {
        // Initialize the database
        reelRepository.saveAndFlush(reel);
        reelSearchRepository.save(reel);
        int databaseSizeBeforeUpdate = reelRepository.findAll().size();

        // Update the reel
        Reel updatedReel = reelRepository.findOne(reel.getId());
        updatedReel
            .reelNumber(UPDATED_REEL_NUMBER)
            .decal(UPDATED_DECAL)
            .weight(UPDATED_WEIGHT)
            .receivedOn(UPDATED_RECEIVED_ON)
            .lotNo(UPDATED_LOT_NO);

        restReelMockMvc.perform(put("/api/reels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReel)))
            .andExpect(status().isOk());

        // Validate the Reel in the database
        List<Reel> reelList = reelRepository.findAll();
        assertThat(reelList).hasSize(databaseSizeBeforeUpdate);
        Reel testReel = reelList.get(reelList.size() - 1);
        assertThat(testReel.getReelNumber()).isEqualTo(UPDATED_REEL_NUMBER);
        assertThat(testReel.getDecal()).isEqualTo(UPDATED_DECAL);
        assertThat(testReel.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testReel.getReceivedOn()).isEqualTo(UPDATED_RECEIVED_ON);
        assertThat(testReel.getLotNo()).isEqualTo(UPDATED_LOT_NO);

        // Validate the Reel in Elasticsearch
        Reel reelEs = reelSearchRepository.findOne(testReel.getId());
        assertThat(reelEs).isEqualToComparingFieldByField(testReel);
    }

    @Test
    @Transactional
    public void updateNonExistingReel() throws Exception {
        int databaseSizeBeforeUpdate = reelRepository.findAll().size();

        // Create the Reel

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restReelMockMvc.perform(put("/api/reels")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reel)))
            .andExpect(status().isCreated());

        // Validate the Reel in the database
        List<Reel> reelList = reelRepository.findAll();
        assertThat(reelList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteReel() throws Exception {
        // Initialize the database
        reelRepository.saveAndFlush(reel);
        reelSearchRepository.save(reel);
        int databaseSizeBeforeDelete = reelRepository.findAll().size();

        // Get the reel
        restReelMockMvc.perform(delete("/api/reels/{id}", reel.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean reelExistsInEs = reelSearchRepository.exists(reel.getId());
        assertThat(reelExistsInEs).isFalse();

        // Validate the database is empty
        List<Reel> reelList = reelRepository.findAll();
        assertThat(reelList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchReel() throws Exception {
        // Initialize the database
        reelRepository.saveAndFlush(reel);
        reelSearchRepository.save(reel);

        // Search the reel
        restReelMockMvc.perform(get("/api/_search/reels?query=id:" + reel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reel.getId().intValue())))
            .andExpect(jsonPath("$.[*].reelNumber").value(hasItem(DEFAULT_REEL_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].decal").value(hasItem(DEFAULT_DECAL.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].receivedOn").value(hasItem(DEFAULT_RECEIVED_ON.toString())))
            .andExpect(jsonPath("$.[*].lotNo").value(hasItem(DEFAULT_LOT_NO.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reel.class);
        Reel reel1 = new Reel();
        reel1.setId(1L);
        Reel reel2 = new Reel();
        reel2.setId(reel1.getId());
        assertThat(reel1).isEqualTo(reel2);
        reel2.setId(2L);
        assertThat(reel1).isNotEqualTo(reel2);
        reel1.setId(null);
        assertThat(reel1).isNotEqualTo(reel2);
    }
}
