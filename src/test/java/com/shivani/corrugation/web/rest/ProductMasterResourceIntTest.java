package com.shivani.corrugation.web.rest;

import com.shivani.corrugation.ShivaniCorrugationApp;

import com.shivani.corrugation.domain.ProductMaster;
import com.shivani.corrugation.repository.ProductMasterRepository;
import com.shivani.corrugation.repository.search.ProductMasterSearchRepository;
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

import com.shivani.corrugation.domain.enumeration.ProductType;
/**
 * Test class for the ProductMasterResource REST controller.
 *
 * @see ProductMasterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShivaniCorrugationApp.class)
public class ProductMasterResourceIntTest {

    private static final Integer DEFAULT_PRODUCT_ID = 1;
    private static final Integer UPDATED_PRODUCT_ID = 2;

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER = "BBBBBBBBBB";

    private static final ProductType DEFAULT_TYPE = ProductType.THREEPLY;
    private static final ProductType UPDATED_TYPE = ProductType.FIVEPLY;

    private static final Integer DEFAULT_DECAL = 1;
    private static final Integer UPDATED_DECAL = 2;

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final Integer DEFAULT_WIDTH = 1;
    private static final Integer UPDATED_WIDTH = 2;

    private static final Integer DEFAULT_HEIGHT = 1;
    private static final Integer UPDATED_HEIGHT = 2;

    private static final Float DEFAULT_REEL_SIZE = 1F;
    private static final Float UPDATED_REEL_SIZE = 2F;

    private static final Float DEFAULT_SHEET_SIZE = 1F;
    private static final Float UPDATED_SHEET_SIZE = 2F;

    private static final Float DEFAULT_SURFACE_AREA = 1F;
    private static final Float UPDATED_SURFACE_AREA = 2F;

    private static final Long DEFAULT_PLY_1 = 1L;
    private static final Long UPDATED_PLY_1 = 2L;

    private static final Long DEFAULT_PLY_2 = 1L;
    private static final Long UPDATED_PLY_2 = 2L;

    private static final Long DEFAULT_PLY_3 = 1L;
    private static final Long UPDATED_PLY_3 = 2L;

    private static final Long DEFAULT_PLY_4 = 1L;
    private static final Long UPDATED_PLY_4 = 2L;

    private static final Long DEFAULT_PLY_5 = 1L;
    private static final Long UPDATED_PLY_5 = 2L;

    private static final Long DEFAULT_PLY_1_WEIGHT = 1L;
    private static final Long UPDATED_PLY_1_WEIGHT = 2L;

    private static final Long DEFAULT_PLY_2_WEIGHT = 1L;
    private static final Long UPDATED_PLY_2_WEIGHT = 2L;

    private static final Long DEFAULT_PLY_3_WEIGHT = 1L;
    private static final Long UPDATED_PLY_3_WEIGHT = 2L;

    private static final Long DEFAULT_PLY_4_WEIGHT = 1L;
    private static final Long UPDATED_PLY_4_WEIGHT = 2L;

    private static final Long DEFAULT_PLY_5_WEIGHT = 1L;
    private static final Long UPDATED_PLY_5_WEIGHT = 2L;

    private static final Integer DEFAULT_PLATE_QTY = 1;
    private static final Integer UPDATED_PLATE_QTY = 2;

    private static final Integer DEFAULT_PARTITION_QTY = 1;
    private static final Integer UPDATED_PARTITION_QTY = 2;

    private static final Integer DEFAULT_PLATE_LENGTH = 1;
    private static final Integer UPDATED_PLATE_LENGTH = 2;

    private static final Integer DEFAULT_PLATE_WIDTH = 1;
    private static final Integer UPDATED_PLATE_WIDTH = 2;

    private static final Integer DEFAULT_PARTITION_LENGTH = 1;
    private static final Integer UPDATED_PARTITION_LENGTH = 2;

    private static final Integer DEFAULT_PARTITION_WIDTH = 1;
    private static final Integer UPDATED_PARTITION_WIDTH = 2;

    private static final Long DEFAULT_PLATE_PLY_1 = 1L;
    private static final Long UPDATED_PLATE_PLY_1 = 2L;

    private static final Long DEFAULT_PLATE_PLY_2 = 1L;
    private static final Long UPDATED_PLATE_PLY_2 = 2L;

    private static final Long DEFAULT_PLATE_PLY_3 = 1L;
    private static final Long UPDATED_PLATE_PLY_3 = 2L;

    private static final Long DEFAULT_PLATE_PLY_4 = 1L;
    private static final Long UPDATED_PLATE_PLY_4 = 2L;

    private static final Long DEFAULT_PLATE_PLY_5 = 1L;
    private static final Long UPDATED_PLATE_PLY_5 = 2L;

    private static final Long DEFAULT_PARTITION_PLY_1 = 1L;
    private static final Long UPDATED_PARTITION_PLY_1 = 2L;

    private static final Long DEFAULT_PARTITION_PLY_2 = 1L;
    private static final Long UPDATED_PARTITION_PLY_2 = 2L;

    private static final Long DEFAULT_PARTITION_PLY_3 = 1L;
    private static final Long UPDATED_PARTITION_PLY_3 = 2L;

    private static final Long DEFAULT_PARTITION_PLY_4 = 1L;
    private static final Long UPDATED_PARTITION_PLY_4 = 2L;

    private static final Long DEFAULT_PARTITION_PLY_5 = 1L;
    private static final Long UPDATED_PARTITION_PLY_5 = 2L;

    private static final Long DEFAULT_PLATE_PLY_1_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_PLY_1_WEIGHT = 2L;

    private static final Long DEFAULT_PLATE_PLY_2_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_PLY_2_WEIGHT = 2L;

    private static final Long DEFAULT_PLATE_PLY_3_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_PLY_3_WEIGHT = 2L;

    private static final Long DEFAULT_PLATE_PLY_4_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_PLY_4_WEIGHT = 2L;

    private static final Long DEFAULT_PLATE_PLY_5_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_PLY_5_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_PLY_1_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_PLY_1_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_PLY_2_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_PLY_2_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_PLY_3_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_PLY_3_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_PLY_4_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_PLY_4_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_PLY_5_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_PLY_5_WEIGHT = 2L;

    private static final Long DEFAULT_PLATE_WEIGHT = 1L;
    private static final Long UPDATED_PLATE_WEIGHT = 2L;

    private static final Long DEFAULT_PARTITION_WEIGHT = 1L;
    private static final Long UPDATED_PARTITION_WEIGHT = 2L;

    private static final Long DEFAULT_BOX_WEIGHT = 1L;
    private static final Long UPDATED_BOX_WEIGHT = 2L;

    private static final Long DEFAULT_PRODUCT_WEIGHT = 1L;
    private static final Long UPDATED_PRODUCT_WEIGHT = 2L;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    @Autowired
    private ProductMasterRepository productMasterRepository;

    @Autowired
    private ProductMasterSearchRepository productMasterSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductMasterMockMvc;

    private ProductMaster productMaster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductMasterResource productMasterResource = new ProductMasterResource(productMasterRepository, productMasterSearchRepository);
        this.restProductMasterMockMvc = MockMvcBuilders.standaloneSetup(productMasterResource)
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
    public static ProductMaster createEntity(EntityManager em) {
        ProductMaster productMaster = new ProductMaster()
            .productId(DEFAULT_PRODUCT_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .productDescription(DEFAULT_PRODUCT_DESCRIPTION)
            .customer(DEFAULT_CUSTOMER)
            .type(DEFAULT_TYPE)
            .decal(DEFAULT_DECAL)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT)
            .reelSize(DEFAULT_REEL_SIZE)
            .sheetSize(DEFAULT_SHEET_SIZE)
            .surfaceArea(DEFAULT_SURFACE_AREA)
            .ply1(DEFAULT_PLY_1)
            .ply2(DEFAULT_PLY_2)
            .ply3(DEFAULT_PLY_3)
            .ply4(DEFAULT_PLY_4)
            .ply5(DEFAULT_PLY_5)
            .ply1Weight(DEFAULT_PLY_1_WEIGHT)
            .ply2Weight(DEFAULT_PLY_2_WEIGHT)
            .ply3Weight(DEFAULT_PLY_3_WEIGHT)
            .ply4Weight(DEFAULT_PLY_4_WEIGHT)
            .ply5Weight(DEFAULT_PLY_5_WEIGHT)
            .plateQty(DEFAULT_PLATE_QTY)
            .partitionQty(DEFAULT_PARTITION_QTY)
            .plateLength(DEFAULT_PLATE_LENGTH)
            .plateWidth(DEFAULT_PLATE_WIDTH)
            .partitionLength(DEFAULT_PARTITION_LENGTH)
            .partitionWidth(DEFAULT_PARTITION_WIDTH)
            .platePly1(DEFAULT_PLATE_PLY_1)
            .platePly2(DEFAULT_PLATE_PLY_2)
            .platePly3(DEFAULT_PLATE_PLY_3)
            .platePly4(DEFAULT_PLATE_PLY_4)
            .platePly5(DEFAULT_PLATE_PLY_5)
            .partitionPly1(DEFAULT_PARTITION_PLY_1)
            .partitionPly2(DEFAULT_PARTITION_PLY_2)
            .partitionPly3(DEFAULT_PARTITION_PLY_3)
            .partitionPly4(DEFAULT_PARTITION_PLY_4)
            .partitionPly5(DEFAULT_PARTITION_PLY_5)
            .platePly1Weight(DEFAULT_PLATE_PLY_1_WEIGHT)
            .platePly2Weight(DEFAULT_PLATE_PLY_2_WEIGHT)
            .platePly3Weight(DEFAULT_PLATE_PLY_3_WEIGHT)
            .platePly4Weight(DEFAULT_PLATE_PLY_4_WEIGHT)
            .platePly5Weight(DEFAULT_PLATE_PLY_5_WEIGHT)
            .partitionPly1Weight(DEFAULT_PARTITION_PLY_1_WEIGHT)
            .partitionPly2Weight(DEFAULT_PARTITION_PLY_2_WEIGHT)
            .partitionPly3Weight(DEFAULT_PARTITION_PLY_3_WEIGHT)
            .partitionPly4Weight(DEFAULT_PARTITION_PLY_4_WEIGHT)
            .partitionPly5Weight(DEFAULT_PARTITION_PLY_5_WEIGHT)
            .plateWeight(DEFAULT_PLATE_WEIGHT)
            .partitionWeight(DEFAULT_PARTITION_WEIGHT)
            .boxWeight(DEFAULT_BOX_WEIGHT)
            .productWeight(DEFAULT_PRODUCT_WEIGHT)
            .price(DEFAULT_PRICE);
        return productMaster;
    }

    @Before
    public void initTest() {
        productMasterSearchRepository.deleteAll();
        productMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createProductMaster() throws Exception {
        int databaseSizeBeforeCreate = productMasterRepository.findAll().size();

        // Create the ProductMaster
        restProductMasterMockMvc.perform(post("/api/product-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productMaster)))
            .andExpect(status().isCreated());

        // Validate the ProductMaster in the database
        List<ProductMaster> productMasterList = productMasterRepository.findAll();
        assertThat(productMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ProductMaster testProductMaster = productMasterList.get(productMasterList.size() - 1);
        assertThat(testProductMaster.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testProductMaster.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testProductMaster.getProductDescription()).isEqualTo(DEFAULT_PRODUCT_DESCRIPTION);
        assertThat(testProductMaster.getCustomer()).isEqualTo(DEFAULT_CUSTOMER);
        assertThat(testProductMaster.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testProductMaster.getDecal()).isEqualTo(DEFAULT_DECAL);
        assertThat(testProductMaster.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testProductMaster.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testProductMaster.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testProductMaster.getReelSize()).isEqualTo(DEFAULT_REEL_SIZE);
        assertThat(testProductMaster.getSheetSize()).isEqualTo(DEFAULT_SHEET_SIZE);
        assertThat(testProductMaster.getSurfaceArea()).isEqualTo(DEFAULT_SURFACE_AREA);
        assertThat(testProductMaster.getPly1()).isEqualTo(DEFAULT_PLY_1);
        assertThat(testProductMaster.getPly2()).isEqualTo(DEFAULT_PLY_2);
        assertThat(testProductMaster.getPly3()).isEqualTo(DEFAULT_PLY_3);
        assertThat(testProductMaster.getPly4()).isEqualTo(DEFAULT_PLY_4);
        assertThat(testProductMaster.getPly5()).isEqualTo(DEFAULT_PLY_5);
        assertThat(testProductMaster.getPly1Weight()).isEqualTo(DEFAULT_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPly2Weight()).isEqualTo(DEFAULT_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPly3Weight()).isEqualTo(DEFAULT_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPly4Weight()).isEqualTo(DEFAULT_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPly5Weight()).isEqualTo(DEFAULT_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPlateQty()).isEqualTo(DEFAULT_PLATE_QTY);
        assertThat(testProductMaster.getPartitionQty()).isEqualTo(DEFAULT_PARTITION_QTY);
        assertThat(testProductMaster.getPlateLength()).isEqualTo(DEFAULT_PLATE_LENGTH);
        assertThat(testProductMaster.getPlateWidth()).isEqualTo(DEFAULT_PLATE_WIDTH);
        assertThat(testProductMaster.getPartitionLength()).isEqualTo(DEFAULT_PARTITION_LENGTH);
        assertThat(testProductMaster.getPartitionWidth()).isEqualTo(DEFAULT_PARTITION_WIDTH);
        assertThat(testProductMaster.getPlatePly1()).isEqualTo(DEFAULT_PLATE_PLY_1);
        assertThat(testProductMaster.getPlatePly2()).isEqualTo(DEFAULT_PLATE_PLY_2);
        assertThat(testProductMaster.getPlatePly3()).isEqualTo(DEFAULT_PLATE_PLY_3);
        assertThat(testProductMaster.getPlatePly4()).isEqualTo(DEFAULT_PLATE_PLY_4);
        assertThat(testProductMaster.getPlatePly5()).isEqualTo(DEFAULT_PLATE_PLY_5);
        assertThat(testProductMaster.getPartitionPly1()).isEqualTo(DEFAULT_PARTITION_PLY_1);
        assertThat(testProductMaster.getPartitionPly2()).isEqualTo(DEFAULT_PARTITION_PLY_2);
        assertThat(testProductMaster.getPartitionPly3()).isEqualTo(DEFAULT_PARTITION_PLY_3);
        assertThat(testProductMaster.getPartitionPly4()).isEqualTo(DEFAULT_PARTITION_PLY_4);
        assertThat(testProductMaster.getPartitionPly5()).isEqualTo(DEFAULT_PARTITION_PLY_5);
        assertThat(testProductMaster.getPlatePly1Weight()).isEqualTo(DEFAULT_PLATE_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPlatePly2Weight()).isEqualTo(DEFAULT_PLATE_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPlatePly3Weight()).isEqualTo(DEFAULT_PLATE_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPlatePly4Weight()).isEqualTo(DEFAULT_PLATE_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPlatePly5Weight()).isEqualTo(DEFAULT_PLATE_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPartitionPly1Weight()).isEqualTo(DEFAULT_PARTITION_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPartitionPly2Weight()).isEqualTo(DEFAULT_PARTITION_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPartitionPly3Weight()).isEqualTo(DEFAULT_PARTITION_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPartitionPly4Weight()).isEqualTo(DEFAULT_PARTITION_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPartitionPly5Weight()).isEqualTo(DEFAULT_PARTITION_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPlateWeight()).isEqualTo(DEFAULT_PLATE_WEIGHT);
        assertThat(testProductMaster.getPartitionWeight()).isEqualTo(DEFAULT_PARTITION_WEIGHT);
        assertThat(testProductMaster.getBoxWeight()).isEqualTo(DEFAULT_BOX_WEIGHT);
        assertThat(testProductMaster.getProductWeight()).isEqualTo(DEFAULT_PRODUCT_WEIGHT);
        assertThat(testProductMaster.getPrice()).isEqualTo(DEFAULT_PRICE);

        // Validate the ProductMaster in Elasticsearch
        ProductMaster productMasterEs = productMasterSearchRepository.findOne(testProductMaster.getId());
        assertThat(productMasterEs).isEqualToComparingFieldByField(testProductMaster);
    }

    @Test
    @Transactional
    public void createProductMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productMasterRepository.findAll().size();

        // Create the ProductMaster with an existing ID
        productMaster.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductMasterMockMvc.perform(post("/api/product-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productMaster)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ProductMaster> productMasterList = productMasterRepository.findAll();
        assertThat(productMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProductMasters() throws Exception {
        // Initialize the database
        productMasterRepository.saveAndFlush(productMaster);

        // Get all the productMasterList
        restProductMasterMockMvc.perform(get("/api/product-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].productDescription").value(hasItem(DEFAULT_PRODUCT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].decal").value(hasItem(DEFAULT_DECAL)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].reelSize").value(hasItem(DEFAULT_REEL_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].sheetSize").value(hasItem(DEFAULT_SHEET_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].surfaceArea").value(hasItem(DEFAULT_SURFACE_AREA.doubleValue())))
            .andExpect(jsonPath("$.[*].ply1").value(hasItem(DEFAULT_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].ply2").value(hasItem(DEFAULT_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].ply3").value(hasItem(DEFAULT_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].ply4").value(hasItem(DEFAULT_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].ply5").value(hasItem(DEFAULT_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].ply1Weight").value(hasItem(DEFAULT_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply2Weight").value(hasItem(DEFAULT_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply3Weight").value(hasItem(DEFAULT_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply4Weight").value(hasItem(DEFAULT_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply5Weight").value(hasItem(DEFAULT_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].plateQty").value(hasItem(DEFAULT_PLATE_QTY)))
            .andExpect(jsonPath("$.[*].partitionQty").value(hasItem(DEFAULT_PARTITION_QTY)))
            .andExpect(jsonPath("$.[*].plateLength").value(hasItem(DEFAULT_PLATE_LENGTH)))
            .andExpect(jsonPath("$.[*].plateWidth").value(hasItem(DEFAULT_PLATE_WIDTH)))
            .andExpect(jsonPath("$.[*].partitionLength").value(hasItem(DEFAULT_PARTITION_LENGTH)))
            .andExpect(jsonPath("$.[*].partitionWidth").value(hasItem(DEFAULT_PARTITION_WIDTH)))
            .andExpect(jsonPath("$.[*].platePly1").value(hasItem(DEFAULT_PLATE_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].platePly2").value(hasItem(DEFAULT_PLATE_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].platePly3").value(hasItem(DEFAULT_PLATE_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].platePly4").value(hasItem(DEFAULT_PLATE_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].platePly5").value(hasItem(DEFAULT_PLATE_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly1").value(hasItem(DEFAULT_PARTITION_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly2").value(hasItem(DEFAULT_PARTITION_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly3").value(hasItem(DEFAULT_PARTITION_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly4").value(hasItem(DEFAULT_PARTITION_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly5").value(hasItem(DEFAULT_PARTITION_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].platePly1Weight").value(hasItem(DEFAULT_PLATE_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly2Weight").value(hasItem(DEFAULT_PLATE_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly3Weight").value(hasItem(DEFAULT_PLATE_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly4Weight").value(hasItem(DEFAULT_PLATE_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly5Weight").value(hasItem(DEFAULT_PLATE_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly1Weight").value(hasItem(DEFAULT_PARTITION_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly2Weight").value(hasItem(DEFAULT_PARTITION_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly3Weight").value(hasItem(DEFAULT_PARTITION_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly4Weight").value(hasItem(DEFAULT_PARTITION_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly5Weight").value(hasItem(DEFAULT_PARTITION_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].plateWeight").value(hasItem(DEFAULT_PLATE_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionWeight").value(hasItem(DEFAULT_PARTITION_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].boxWeight").value(hasItem(DEFAULT_BOX_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].productWeight").value(hasItem(DEFAULT_PRODUCT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void getProductMaster() throws Exception {
        // Initialize the database
        productMasterRepository.saveAndFlush(productMaster);

        // Get the productMaster
        restProductMasterMockMvc.perform(get("/api/product-masters/{id}", productMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(productMaster.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.productDescription").value(DEFAULT_PRODUCT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.customer").value(DEFAULT_CUSTOMER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.decal").value(DEFAULT_DECAL))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT))
            .andExpect(jsonPath("$.reelSize").value(DEFAULT_REEL_SIZE.doubleValue()))
            .andExpect(jsonPath("$.sheetSize").value(DEFAULT_SHEET_SIZE.doubleValue()))
            .andExpect(jsonPath("$.surfaceArea").value(DEFAULT_SURFACE_AREA.doubleValue()))
            .andExpect(jsonPath("$.ply1").value(DEFAULT_PLY_1.intValue()))
            .andExpect(jsonPath("$.ply2").value(DEFAULT_PLY_2.intValue()))
            .andExpect(jsonPath("$.ply3").value(DEFAULT_PLY_3.intValue()))
            .andExpect(jsonPath("$.ply4").value(DEFAULT_PLY_4.intValue()))
            .andExpect(jsonPath("$.ply5").value(DEFAULT_PLY_5.intValue()))
            .andExpect(jsonPath("$.ply1Weight").value(DEFAULT_PLY_1_WEIGHT.intValue()))
            .andExpect(jsonPath("$.ply2Weight").value(DEFAULT_PLY_2_WEIGHT.intValue()))
            .andExpect(jsonPath("$.ply3Weight").value(DEFAULT_PLY_3_WEIGHT.intValue()))
            .andExpect(jsonPath("$.ply4Weight").value(DEFAULT_PLY_4_WEIGHT.intValue()))
            .andExpect(jsonPath("$.ply5Weight").value(DEFAULT_PLY_5_WEIGHT.intValue()))
            .andExpect(jsonPath("$.plateQty").value(DEFAULT_PLATE_QTY))
            .andExpect(jsonPath("$.partitionQty").value(DEFAULT_PARTITION_QTY))
            .andExpect(jsonPath("$.plateLength").value(DEFAULT_PLATE_LENGTH))
            .andExpect(jsonPath("$.plateWidth").value(DEFAULT_PLATE_WIDTH))
            .andExpect(jsonPath("$.partitionLength").value(DEFAULT_PARTITION_LENGTH))
            .andExpect(jsonPath("$.partitionWidth").value(DEFAULT_PARTITION_WIDTH))
            .andExpect(jsonPath("$.platePly1").value(DEFAULT_PLATE_PLY_1.intValue()))
            .andExpect(jsonPath("$.platePly2").value(DEFAULT_PLATE_PLY_2.intValue()))
            .andExpect(jsonPath("$.platePly3").value(DEFAULT_PLATE_PLY_3.intValue()))
            .andExpect(jsonPath("$.platePly4").value(DEFAULT_PLATE_PLY_4.intValue()))
            .andExpect(jsonPath("$.platePly5").value(DEFAULT_PLATE_PLY_5.intValue()))
            .andExpect(jsonPath("$.partitionPly1").value(DEFAULT_PARTITION_PLY_1.intValue()))
            .andExpect(jsonPath("$.partitionPly2").value(DEFAULT_PARTITION_PLY_2.intValue()))
            .andExpect(jsonPath("$.partitionPly3").value(DEFAULT_PARTITION_PLY_3.intValue()))
            .andExpect(jsonPath("$.partitionPly4").value(DEFAULT_PARTITION_PLY_4.intValue()))
            .andExpect(jsonPath("$.partitionPly5").value(DEFAULT_PARTITION_PLY_5.intValue()))
            .andExpect(jsonPath("$.platePly1Weight").value(DEFAULT_PLATE_PLY_1_WEIGHT.intValue()))
            .andExpect(jsonPath("$.platePly2Weight").value(DEFAULT_PLATE_PLY_2_WEIGHT.intValue()))
            .andExpect(jsonPath("$.platePly3Weight").value(DEFAULT_PLATE_PLY_3_WEIGHT.intValue()))
            .andExpect(jsonPath("$.platePly4Weight").value(DEFAULT_PLATE_PLY_4_WEIGHT.intValue()))
            .andExpect(jsonPath("$.platePly5Weight").value(DEFAULT_PLATE_PLY_5_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionPly1Weight").value(DEFAULT_PARTITION_PLY_1_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionPly2Weight").value(DEFAULT_PARTITION_PLY_2_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionPly3Weight").value(DEFAULT_PARTITION_PLY_3_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionPly4Weight").value(DEFAULT_PARTITION_PLY_4_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionPly5Weight").value(DEFAULT_PARTITION_PLY_5_WEIGHT.intValue()))
            .andExpect(jsonPath("$.plateWeight").value(DEFAULT_PLATE_WEIGHT.intValue()))
            .andExpect(jsonPath("$.partitionWeight").value(DEFAULT_PARTITION_WEIGHT.intValue()))
            .andExpect(jsonPath("$.boxWeight").value(DEFAULT_BOX_WEIGHT.intValue()))
            .andExpect(jsonPath("$.productWeight").value(DEFAULT_PRODUCT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProductMaster() throws Exception {
        // Get the productMaster
        restProductMasterMockMvc.perform(get("/api/product-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductMaster() throws Exception {
        // Initialize the database
        productMasterRepository.saveAndFlush(productMaster);
        productMasterSearchRepository.save(productMaster);
        int databaseSizeBeforeUpdate = productMasterRepository.findAll().size();

        // Update the productMaster
        ProductMaster updatedProductMaster = productMasterRepository.findOne(productMaster.getId());
        updatedProductMaster
            .productId(UPDATED_PRODUCT_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .productDescription(UPDATED_PRODUCT_DESCRIPTION)
            .customer(UPDATED_CUSTOMER)
            .type(UPDATED_TYPE)
            .decal(UPDATED_DECAL)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT)
            .reelSize(UPDATED_REEL_SIZE)
            .sheetSize(UPDATED_SHEET_SIZE)
            .surfaceArea(UPDATED_SURFACE_AREA)
            .ply1(UPDATED_PLY_1)
            .ply2(UPDATED_PLY_2)
            .ply3(UPDATED_PLY_3)
            .ply4(UPDATED_PLY_4)
            .ply5(UPDATED_PLY_5)
            .ply1Weight(UPDATED_PLY_1_WEIGHT)
            .ply2Weight(UPDATED_PLY_2_WEIGHT)
            .ply3Weight(UPDATED_PLY_3_WEIGHT)
            .ply4Weight(UPDATED_PLY_4_WEIGHT)
            .ply5Weight(UPDATED_PLY_5_WEIGHT)
            .plateQty(UPDATED_PLATE_QTY)
            .partitionQty(UPDATED_PARTITION_QTY)
            .plateLength(UPDATED_PLATE_LENGTH)
            .plateWidth(UPDATED_PLATE_WIDTH)
            .partitionLength(UPDATED_PARTITION_LENGTH)
            .partitionWidth(UPDATED_PARTITION_WIDTH)
            .platePly1(UPDATED_PLATE_PLY_1)
            .platePly2(UPDATED_PLATE_PLY_2)
            .platePly3(UPDATED_PLATE_PLY_3)
            .platePly4(UPDATED_PLATE_PLY_4)
            .platePly5(UPDATED_PLATE_PLY_5)
            .partitionPly1(UPDATED_PARTITION_PLY_1)
            .partitionPly2(UPDATED_PARTITION_PLY_2)
            .partitionPly3(UPDATED_PARTITION_PLY_3)
            .partitionPly4(UPDATED_PARTITION_PLY_4)
            .partitionPly5(UPDATED_PARTITION_PLY_5)
            .platePly1Weight(UPDATED_PLATE_PLY_1_WEIGHT)
            .platePly2Weight(UPDATED_PLATE_PLY_2_WEIGHT)
            .platePly3Weight(UPDATED_PLATE_PLY_3_WEIGHT)
            .platePly4Weight(UPDATED_PLATE_PLY_4_WEIGHT)
            .platePly5Weight(UPDATED_PLATE_PLY_5_WEIGHT)
            .partitionPly1Weight(UPDATED_PARTITION_PLY_1_WEIGHT)
            .partitionPly2Weight(UPDATED_PARTITION_PLY_2_WEIGHT)
            .partitionPly3Weight(UPDATED_PARTITION_PLY_3_WEIGHT)
            .partitionPly4Weight(UPDATED_PARTITION_PLY_4_WEIGHT)
            .partitionPly5Weight(UPDATED_PARTITION_PLY_5_WEIGHT)
            .plateWeight(UPDATED_PLATE_WEIGHT)
            .partitionWeight(UPDATED_PARTITION_WEIGHT)
            .boxWeight(UPDATED_BOX_WEIGHT)
            .productWeight(UPDATED_PRODUCT_WEIGHT)
            .price(UPDATED_PRICE);

        restProductMasterMockMvc.perform(put("/api/product-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProductMaster)))
            .andExpect(status().isOk());

        // Validate the ProductMaster in the database
        List<ProductMaster> productMasterList = productMasterRepository.findAll();
        assertThat(productMasterList).hasSize(databaseSizeBeforeUpdate);
        ProductMaster testProductMaster = productMasterList.get(productMasterList.size() - 1);
        assertThat(testProductMaster.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testProductMaster.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testProductMaster.getProductDescription()).isEqualTo(UPDATED_PRODUCT_DESCRIPTION);
        assertThat(testProductMaster.getCustomer()).isEqualTo(UPDATED_CUSTOMER);
        assertThat(testProductMaster.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testProductMaster.getDecal()).isEqualTo(UPDATED_DECAL);
        assertThat(testProductMaster.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testProductMaster.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testProductMaster.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testProductMaster.getReelSize()).isEqualTo(UPDATED_REEL_SIZE);
        assertThat(testProductMaster.getSheetSize()).isEqualTo(UPDATED_SHEET_SIZE);
        assertThat(testProductMaster.getSurfaceArea()).isEqualTo(UPDATED_SURFACE_AREA);
        assertThat(testProductMaster.getPly1()).isEqualTo(UPDATED_PLY_1);
        assertThat(testProductMaster.getPly2()).isEqualTo(UPDATED_PLY_2);
        assertThat(testProductMaster.getPly3()).isEqualTo(UPDATED_PLY_3);
        assertThat(testProductMaster.getPly4()).isEqualTo(UPDATED_PLY_4);
        assertThat(testProductMaster.getPly5()).isEqualTo(UPDATED_PLY_5);
        assertThat(testProductMaster.getPly1Weight()).isEqualTo(UPDATED_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPly2Weight()).isEqualTo(UPDATED_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPly3Weight()).isEqualTo(UPDATED_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPly4Weight()).isEqualTo(UPDATED_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPly5Weight()).isEqualTo(UPDATED_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPlateQty()).isEqualTo(UPDATED_PLATE_QTY);
        assertThat(testProductMaster.getPartitionQty()).isEqualTo(UPDATED_PARTITION_QTY);
        assertThat(testProductMaster.getPlateLength()).isEqualTo(UPDATED_PLATE_LENGTH);
        assertThat(testProductMaster.getPlateWidth()).isEqualTo(UPDATED_PLATE_WIDTH);
        assertThat(testProductMaster.getPartitionLength()).isEqualTo(UPDATED_PARTITION_LENGTH);
        assertThat(testProductMaster.getPartitionWidth()).isEqualTo(UPDATED_PARTITION_WIDTH);
        assertThat(testProductMaster.getPlatePly1()).isEqualTo(UPDATED_PLATE_PLY_1);
        assertThat(testProductMaster.getPlatePly2()).isEqualTo(UPDATED_PLATE_PLY_2);
        assertThat(testProductMaster.getPlatePly3()).isEqualTo(UPDATED_PLATE_PLY_3);
        assertThat(testProductMaster.getPlatePly4()).isEqualTo(UPDATED_PLATE_PLY_4);
        assertThat(testProductMaster.getPlatePly5()).isEqualTo(UPDATED_PLATE_PLY_5);
        assertThat(testProductMaster.getPartitionPly1()).isEqualTo(UPDATED_PARTITION_PLY_1);
        assertThat(testProductMaster.getPartitionPly2()).isEqualTo(UPDATED_PARTITION_PLY_2);
        assertThat(testProductMaster.getPartitionPly3()).isEqualTo(UPDATED_PARTITION_PLY_3);
        assertThat(testProductMaster.getPartitionPly4()).isEqualTo(UPDATED_PARTITION_PLY_4);
        assertThat(testProductMaster.getPartitionPly5()).isEqualTo(UPDATED_PARTITION_PLY_5);
        assertThat(testProductMaster.getPlatePly1Weight()).isEqualTo(UPDATED_PLATE_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPlatePly2Weight()).isEqualTo(UPDATED_PLATE_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPlatePly3Weight()).isEqualTo(UPDATED_PLATE_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPlatePly4Weight()).isEqualTo(UPDATED_PLATE_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPlatePly5Weight()).isEqualTo(UPDATED_PLATE_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPartitionPly1Weight()).isEqualTo(UPDATED_PARTITION_PLY_1_WEIGHT);
        assertThat(testProductMaster.getPartitionPly2Weight()).isEqualTo(UPDATED_PARTITION_PLY_2_WEIGHT);
        assertThat(testProductMaster.getPartitionPly3Weight()).isEqualTo(UPDATED_PARTITION_PLY_3_WEIGHT);
        assertThat(testProductMaster.getPartitionPly4Weight()).isEqualTo(UPDATED_PARTITION_PLY_4_WEIGHT);
        assertThat(testProductMaster.getPartitionPly5Weight()).isEqualTo(UPDATED_PARTITION_PLY_5_WEIGHT);
        assertThat(testProductMaster.getPlateWeight()).isEqualTo(UPDATED_PLATE_WEIGHT);
        assertThat(testProductMaster.getPartitionWeight()).isEqualTo(UPDATED_PARTITION_WEIGHT);
        assertThat(testProductMaster.getBoxWeight()).isEqualTo(UPDATED_BOX_WEIGHT);
        assertThat(testProductMaster.getProductWeight()).isEqualTo(UPDATED_PRODUCT_WEIGHT);
        assertThat(testProductMaster.getPrice()).isEqualTo(UPDATED_PRICE);

        // Validate the ProductMaster in Elasticsearch
        ProductMaster productMasterEs = productMasterSearchRepository.findOne(testProductMaster.getId());
        assertThat(productMasterEs).isEqualToComparingFieldByField(testProductMaster);
    }

    @Test
    @Transactional
    public void updateNonExistingProductMaster() throws Exception {
        int databaseSizeBeforeUpdate = productMasterRepository.findAll().size();

        // Create the ProductMaster

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProductMasterMockMvc.perform(put("/api/product-masters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productMaster)))
            .andExpect(status().isCreated());

        // Validate the ProductMaster in the database
        List<ProductMaster> productMasterList = productMasterRepository.findAll();
        assertThat(productMasterList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProductMaster() throws Exception {
        // Initialize the database
        productMasterRepository.saveAndFlush(productMaster);
        productMasterSearchRepository.save(productMaster);
        int databaseSizeBeforeDelete = productMasterRepository.findAll().size();

        // Get the productMaster
        restProductMasterMockMvc.perform(delete("/api/product-masters/{id}", productMaster.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean productMasterExistsInEs = productMasterSearchRepository.exists(productMaster.getId());
        assertThat(productMasterExistsInEs).isFalse();

        // Validate the database is empty
        List<ProductMaster> productMasterList = productMasterRepository.findAll();
        assertThat(productMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProductMaster() throws Exception {
        // Initialize the database
        productMasterRepository.saveAndFlush(productMaster);
        productMasterSearchRepository.save(productMaster);

        // Search the productMaster
        restProductMasterMockMvc.perform(get("/api/_search/product-masters?query=id:" + productMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(productMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].productDescription").value(hasItem(DEFAULT_PRODUCT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].customer").value(hasItem(DEFAULT_CUSTOMER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].decal").value(hasItem(DEFAULT_DECAL)))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT)))
            .andExpect(jsonPath("$.[*].reelSize").value(hasItem(DEFAULT_REEL_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].sheetSize").value(hasItem(DEFAULT_SHEET_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].surfaceArea").value(hasItem(DEFAULT_SURFACE_AREA.doubleValue())))
            .andExpect(jsonPath("$.[*].ply1").value(hasItem(DEFAULT_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].ply2").value(hasItem(DEFAULT_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].ply3").value(hasItem(DEFAULT_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].ply4").value(hasItem(DEFAULT_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].ply5").value(hasItem(DEFAULT_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].ply1Weight").value(hasItem(DEFAULT_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply2Weight").value(hasItem(DEFAULT_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply3Weight").value(hasItem(DEFAULT_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply4Weight").value(hasItem(DEFAULT_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].ply5Weight").value(hasItem(DEFAULT_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].plateQty").value(hasItem(DEFAULT_PLATE_QTY)))
            .andExpect(jsonPath("$.[*].partitionQty").value(hasItem(DEFAULT_PARTITION_QTY)))
            .andExpect(jsonPath("$.[*].plateLength").value(hasItem(DEFAULT_PLATE_LENGTH)))
            .andExpect(jsonPath("$.[*].plateWidth").value(hasItem(DEFAULT_PLATE_WIDTH)))
            .andExpect(jsonPath("$.[*].partitionLength").value(hasItem(DEFAULT_PARTITION_LENGTH)))
            .andExpect(jsonPath("$.[*].partitionWidth").value(hasItem(DEFAULT_PARTITION_WIDTH)))
            .andExpect(jsonPath("$.[*].platePly1").value(hasItem(DEFAULT_PLATE_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].platePly2").value(hasItem(DEFAULT_PLATE_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].platePly3").value(hasItem(DEFAULT_PLATE_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].platePly4").value(hasItem(DEFAULT_PLATE_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].platePly5").value(hasItem(DEFAULT_PLATE_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly1").value(hasItem(DEFAULT_PARTITION_PLY_1.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly2").value(hasItem(DEFAULT_PARTITION_PLY_2.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly3").value(hasItem(DEFAULT_PARTITION_PLY_3.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly4").value(hasItem(DEFAULT_PARTITION_PLY_4.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly5").value(hasItem(DEFAULT_PARTITION_PLY_5.intValue())))
            .andExpect(jsonPath("$.[*].platePly1Weight").value(hasItem(DEFAULT_PLATE_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly2Weight").value(hasItem(DEFAULT_PLATE_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly3Weight").value(hasItem(DEFAULT_PLATE_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly4Weight").value(hasItem(DEFAULT_PLATE_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].platePly5Weight").value(hasItem(DEFAULT_PLATE_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly1Weight").value(hasItem(DEFAULT_PARTITION_PLY_1_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly2Weight").value(hasItem(DEFAULT_PARTITION_PLY_2_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly3Weight").value(hasItem(DEFAULT_PARTITION_PLY_3_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly4Weight").value(hasItem(DEFAULT_PARTITION_PLY_4_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionPly5Weight").value(hasItem(DEFAULT_PARTITION_PLY_5_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].plateWeight").value(hasItem(DEFAULT_PLATE_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].partitionWeight").value(hasItem(DEFAULT_PARTITION_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].boxWeight").value(hasItem(DEFAULT_BOX_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].productWeight").value(hasItem(DEFAULT_PRODUCT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductMaster.class);
        ProductMaster productMaster1 = new ProductMaster();
        productMaster1.setId(1L);
        ProductMaster productMaster2 = new ProductMaster();
        productMaster2.setId(productMaster1.getId());
        assertThat(productMaster1).isEqualTo(productMaster2);
        productMaster2.setId(2L);
        assertThat(productMaster1).isNotEqualTo(productMaster2);
        productMaster1.setId(null);
        assertThat(productMaster1).isNotEqualTo(productMaster2);
    }
}
