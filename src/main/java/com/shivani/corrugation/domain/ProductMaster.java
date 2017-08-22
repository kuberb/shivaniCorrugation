package com.shivani.corrugation.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.shivani.corrugation.domain.enumeration.ProductType;

/**
 * A ProductMaster.
 */
@Entity
@Table(name = "product_master")
@Document(indexName = "productmaster")
public class ProductMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "customer")
    private String customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private ProductType type;

    @Column(name = "decal")
    private Integer decal;

    @Column(name = "length")
    private Integer length;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "reel_size")
    private Float reelSize;

    @Column(name = "sheet_size")
    private Float sheetSize;

    @Column(name = "surface_area")
    private Float surfaceArea;

    @Column(name = "ply_1")
    private Long ply1;

    @Column(name = "ply_2")
    private Long ply2;

    @Column(name = "ply_3")
    private Long ply3;

    @Column(name = "ply_4")
    private Long ply4;

    @Column(name = "ply_5")
    private Long ply5;

    @Column(name = "ply_1_weight")
    private Long ply1Weight;

    @Column(name = "ply_2_weight")
    private Long ply2Weight;

    @Column(name = "ply_3_weight")
    private Long ply3Weight;

    @Column(name = "ply_4_weight")
    private Long ply4Weight;

    @Column(name = "ply_5_weight")
    private Long ply5Weight;

    @Column(name = "plate_qty")
    private Integer plateQty;

    @Column(name = "partition_qty")
    private Integer partitionQty;

    @Column(name = "plate_length")
    private Integer plateLength;

    @Column(name = "plate_width")
    private Integer plateWidth;

    @Column(name = "partition_length")
    private Integer partitionLength;

    @Column(name = "partition_width")
    private Integer partitionWidth;

    @Column(name = "plate_ply_1")
    private Long platePly1;

    @Column(name = "plate_ply_2")
    private Long platePly2;

    @Column(name = "plate_ply_3")
    private Long platePly3;

    @Column(name = "plate_ply_4")
    private Long platePly4;

    @Column(name = "plate_ply_5")
    private Long platePly5;

    @Column(name = "partition_ply_1")
    private Long partitionPly1;

    @Column(name = "partition_ply_2")
    private Long partitionPly2;

    @Column(name = "partition_ply_3")
    private Long partitionPly3;

    @Column(name = "partition_ply_4")
    private Long partitionPly4;

    @Column(name = "partition_ply_5")
    private Long partitionPly5;

    @Column(name = "plate_ply_1_weight")
    private Long platePly1Weight;

    @Column(name = "plate_ply_2_weight")
    private Long platePly2Weight;

    @Column(name = "plate_ply_3_weight")
    private Long platePly3Weight;

    @Column(name = "plate_ply_4_weight")
    private Long platePly4Weight;

    @Column(name = "plate_ply_5_weight")
    private Long platePly5Weight;

    @Column(name = "partition_ply_1_weight")
    private Long partitionPly1Weight;

    @Column(name = "partition_ply_2_weight")
    private Long partitionPly2Weight;

    @Column(name = "partition_ply_3_weight")
    private Long partitionPly3Weight;

    @Column(name = "partition_ply_4_weight")
    private Long partitionPly4Weight;

    @Column(name = "partition_ply_5_weight")
    private Long partitionPly5Weight;

    @Column(name = "plate_weight")
    private Long plateWeight;

    @Column(name = "partition_weight")
    private Long partitionWeight;

    @Column(name = "box_weight")
    private Long boxWeight;

    @Column(name = "product_weight")
    private Long productWeight;

    @Column(name = "price")
    private Float price;

    @ManyToMany
    @JoinTable(name = "product_master_paper",
               joinColumns = @JoinColumn(name="product_masters_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="papers_id", referencedColumnName="id"))
    private Set<PaperMaster> papers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public ProductMaster productId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public ProductMaster productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public ProductMaster productDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCustomer() {
        return customer;
    }

    public ProductMaster customer(String customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ProductType getType() {
        return type;
    }

    public ProductMaster type(ProductType type) {
        this.type = type;
        return this;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getDecal() {
        return decal;
    }

    public ProductMaster decal(Integer decal) {
        this.decal = decal;
        return this;
    }

    public void setDecal(Integer decal) {
        this.decal = decal;
    }

    public Integer getLength() {
        return length;
    }

    public ProductMaster length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public ProductMaster width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public ProductMaster height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Float getReelSize() {
        return reelSize;
    }

    public ProductMaster reelSize(Float reelSize) {
        this.reelSize = reelSize;
        return this;
    }

    public void setReelSize(Float reelSize) {
        this.reelSize = reelSize;
    }

    public Float getSheetSize() {
        return sheetSize;
    }

    public ProductMaster sheetSize(Float sheetSize) {
        this.sheetSize = sheetSize;
        return this;
    }

    public void setSheetSize(Float sheetSize) {
        this.sheetSize = sheetSize;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public ProductMaster surfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
        return this;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Long getPly1() {
        return ply1;
    }

    public ProductMaster ply1(Long ply1) {
        this.ply1 = ply1;
        return this;
    }

    public void setPly1(Long ply1) {
        this.ply1 = ply1;
    }

    public Long getPly2() {
        return ply2;
    }

    public ProductMaster ply2(Long ply2) {
        this.ply2 = ply2;
        return this;
    }

    public void setPly2(Long ply2) {
        this.ply2 = ply2;
    }

    public Long getPly3() {
        return ply3;
    }

    public ProductMaster ply3(Long ply3) {
        this.ply3 = ply3;
        return this;
    }

    public void setPly3(Long ply3) {
        this.ply3 = ply3;
    }

    public Long getPly4() {
        return ply4;
    }

    public ProductMaster ply4(Long ply4) {
        this.ply4 = ply4;
        return this;
    }

    public void setPly4(Long ply4) {
        this.ply4 = ply4;
    }

    public Long getPly5() {
        return ply5;
    }

    public ProductMaster ply5(Long ply5) {
        this.ply5 = ply5;
        return this;
    }

    public void setPly5(Long ply5) {
        this.ply5 = ply5;
    }

    public Long getPly1Weight() {
        return ply1Weight;
    }

    public ProductMaster ply1Weight(Long ply1Weight) {
        this.ply1Weight = ply1Weight;
        return this;
    }

    public void setPly1Weight(Long ply1Weight) {
        this.ply1Weight = ply1Weight;
    }

    public Long getPly2Weight() {
        return ply2Weight;
    }

    public ProductMaster ply2Weight(Long ply2Weight) {
        this.ply2Weight = ply2Weight;
        return this;
    }

    public void setPly2Weight(Long ply2Weight) {
        this.ply2Weight = ply2Weight;
    }

    public Long getPly3Weight() {
        return ply3Weight;
    }

    public ProductMaster ply3Weight(Long ply3Weight) {
        this.ply3Weight = ply3Weight;
        return this;
    }

    public void setPly3Weight(Long ply3Weight) {
        this.ply3Weight = ply3Weight;
    }

    public Long getPly4Weight() {
        return ply4Weight;
    }

    public ProductMaster ply4Weight(Long ply4Weight) {
        this.ply4Weight = ply4Weight;
        return this;
    }

    public void setPly4Weight(Long ply4Weight) {
        this.ply4Weight = ply4Weight;
    }

    public Long getPly5Weight() {
        return ply5Weight;
    }

    public ProductMaster ply5Weight(Long ply5Weight) {
        this.ply5Weight = ply5Weight;
        return this;
    }

    public void setPly5Weight(Long ply5Weight) {
        this.ply5Weight = ply5Weight;
    }

    public Integer getPlateQty() {
        return plateQty;
    }

    public ProductMaster plateQty(Integer plateQty) {
        this.plateQty = plateQty;
        return this;
    }

    public void setPlateQty(Integer plateQty) {
        this.plateQty = plateQty;
    }

    public Integer getPartitionQty() {
        return partitionQty;
    }

    public ProductMaster partitionQty(Integer partitionQty) {
        this.partitionQty = partitionQty;
        return this;
    }

    public void setPartitionQty(Integer partitionQty) {
        this.partitionQty = partitionQty;
    }

    public Integer getPlateLength() {
        return plateLength;
    }

    public ProductMaster plateLength(Integer plateLength) {
        this.plateLength = plateLength;
        return this;
    }

    public void setPlateLength(Integer plateLength) {
        this.plateLength = plateLength;
    }

    public Integer getPlateWidth() {
        return plateWidth;
    }

    public ProductMaster plateWidth(Integer plateWidth) {
        this.plateWidth = plateWidth;
        return this;
    }

    public void setPlateWidth(Integer plateWidth) {
        this.plateWidth = plateWidth;
    }

    public Integer getPartitionLength() {
        return partitionLength;
    }

    public ProductMaster partitionLength(Integer partitionLength) {
        this.partitionLength = partitionLength;
        return this;
    }

    public void setPartitionLength(Integer partitionLength) {
        this.partitionLength = partitionLength;
    }

    public Integer getPartitionWidth() {
        return partitionWidth;
    }

    public ProductMaster partitionWidth(Integer partitionWidth) {
        this.partitionWidth = partitionWidth;
        return this;
    }

    public void setPartitionWidth(Integer partitionWidth) {
        this.partitionWidth = partitionWidth;
    }

    public Long getPlatePly1() {
        return platePly1;
    }

    public ProductMaster platePly1(Long platePly1) {
        this.platePly1 = platePly1;
        return this;
    }

    public void setPlatePly1(Long platePly1) {
        this.platePly1 = platePly1;
    }

    public Long getPlatePly2() {
        return platePly2;
    }

    public ProductMaster platePly2(Long platePly2) {
        this.platePly2 = platePly2;
        return this;
    }

    public void setPlatePly2(Long platePly2) {
        this.platePly2 = platePly2;
    }

    public Long getPlatePly3() {
        return platePly3;
    }

    public ProductMaster platePly3(Long platePly3) {
        this.platePly3 = platePly3;
        return this;
    }

    public void setPlatePly3(Long platePly3) {
        this.platePly3 = platePly3;
    }

    public Long getPlatePly4() {
        return platePly4;
    }

    public ProductMaster platePly4(Long platePly4) {
        this.platePly4 = platePly4;
        return this;
    }

    public void setPlatePly4(Long platePly4) {
        this.platePly4 = platePly4;
    }

    public Long getPlatePly5() {
        return platePly5;
    }

    public ProductMaster platePly5(Long platePly5) {
        this.platePly5 = platePly5;
        return this;
    }

    public void setPlatePly5(Long platePly5) {
        this.platePly5 = platePly5;
    }

    public Long getPartitionPly1() {
        return partitionPly1;
    }

    public ProductMaster partitionPly1(Long partitionPly1) {
        this.partitionPly1 = partitionPly1;
        return this;
    }

    public void setPartitionPly1(Long partitionPly1) {
        this.partitionPly1 = partitionPly1;
    }

    public Long getPartitionPly2() {
        return partitionPly2;
    }

    public ProductMaster partitionPly2(Long partitionPly2) {
        this.partitionPly2 = partitionPly2;
        return this;
    }

    public void setPartitionPly2(Long partitionPly2) {
        this.partitionPly2 = partitionPly2;
    }

    public Long getPartitionPly3() {
        return partitionPly3;
    }

    public ProductMaster partitionPly3(Long partitionPly3) {
        this.partitionPly3 = partitionPly3;
        return this;
    }

    public void setPartitionPly3(Long partitionPly3) {
        this.partitionPly3 = partitionPly3;
    }

    public Long getPartitionPly4() {
        return partitionPly4;
    }

    public ProductMaster partitionPly4(Long partitionPly4) {
        this.partitionPly4 = partitionPly4;
        return this;
    }

    public void setPartitionPly4(Long partitionPly4) {
        this.partitionPly4 = partitionPly4;
    }

    public Long getPartitionPly5() {
        return partitionPly5;
    }

    public ProductMaster partitionPly5(Long partitionPly5) {
        this.partitionPly5 = partitionPly5;
        return this;
    }

    public void setPartitionPly5(Long partitionPly5) {
        this.partitionPly5 = partitionPly5;
    }

    public Long getPlatePly1Weight() {
        return platePly1Weight;
    }

    public ProductMaster platePly1Weight(Long platePly1Weight) {
        this.platePly1Weight = platePly1Weight;
        return this;
    }

    public void setPlatePly1Weight(Long platePly1Weight) {
        this.platePly1Weight = platePly1Weight;
    }

    public Long getPlatePly2Weight() {
        return platePly2Weight;
    }

    public ProductMaster platePly2Weight(Long platePly2Weight) {
        this.platePly2Weight = platePly2Weight;
        return this;
    }

    public void setPlatePly2Weight(Long platePly2Weight) {
        this.platePly2Weight = platePly2Weight;
    }

    public Long getPlatePly3Weight() {
        return platePly3Weight;
    }

    public ProductMaster platePly3Weight(Long platePly3Weight) {
        this.platePly3Weight = platePly3Weight;
        return this;
    }

    public void setPlatePly3Weight(Long platePly3Weight) {
        this.platePly3Weight = platePly3Weight;
    }

    public Long getPlatePly4Weight() {
        return platePly4Weight;
    }

    public ProductMaster platePly4Weight(Long platePly4Weight) {
        this.platePly4Weight = platePly4Weight;
        return this;
    }

    public void setPlatePly4Weight(Long platePly4Weight) {
        this.platePly4Weight = platePly4Weight;
    }

    public Long getPlatePly5Weight() {
        return platePly5Weight;
    }

    public ProductMaster platePly5Weight(Long platePly5Weight) {
        this.platePly5Weight = platePly5Weight;
        return this;
    }

    public void setPlatePly5Weight(Long platePly5Weight) {
        this.platePly5Weight = platePly5Weight;
    }

    public Long getPartitionPly1Weight() {
        return partitionPly1Weight;
    }

    public ProductMaster partitionPly1Weight(Long partitionPly1Weight) {
        this.partitionPly1Weight = partitionPly1Weight;
        return this;
    }

    public void setPartitionPly1Weight(Long partitionPly1Weight) {
        this.partitionPly1Weight = partitionPly1Weight;
    }

    public Long getPartitionPly2Weight() {
        return partitionPly2Weight;
    }

    public ProductMaster partitionPly2Weight(Long partitionPly2Weight) {
        this.partitionPly2Weight = partitionPly2Weight;
        return this;
    }

    public void setPartitionPly2Weight(Long partitionPly2Weight) {
        this.partitionPly2Weight = partitionPly2Weight;
    }

    public Long getPartitionPly3Weight() {
        return partitionPly3Weight;
    }

    public ProductMaster partitionPly3Weight(Long partitionPly3Weight) {
        this.partitionPly3Weight = partitionPly3Weight;
        return this;
    }

    public void setPartitionPly3Weight(Long partitionPly3Weight) {
        this.partitionPly3Weight = partitionPly3Weight;
    }

    public Long getPartitionPly4Weight() {
        return partitionPly4Weight;
    }

    public ProductMaster partitionPly4Weight(Long partitionPly4Weight) {
        this.partitionPly4Weight = partitionPly4Weight;
        return this;
    }

    public void setPartitionPly4Weight(Long partitionPly4Weight) {
        this.partitionPly4Weight = partitionPly4Weight;
    }

    public Long getPartitionPly5Weight() {
        return partitionPly5Weight;
    }

    public ProductMaster partitionPly5Weight(Long partitionPly5Weight) {
        this.partitionPly5Weight = partitionPly5Weight;
        return this;
    }

    public void setPartitionPly5Weight(Long partitionPly5Weight) {
        this.partitionPly5Weight = partitionPly5Weight;
    }

    public Long getPlateWeight() {
        return plateWeight;
    }

    public ProductMaster plateWeight(Long plateWeight) {
        this.plateWeight = plateWeight;
        return this;
    }

    public void setPlateWeight(Long plateWeight) {
        this.plateWeight = plateWeight;
    }

    public Long getPartitionWeight() {
        return partitionWeight;
    }

    public ProductMaster partitionWeight(Long partitionWeight) {
        this.partitionWeight = partitionWeight;
        return this;
    }

    public void setPartitionWeight(Long partitionWeight) {
        this.partitionWeight = partitionWeight;
    }

    public Long getBoxWeight() {
        return boxWeight;
    }

    public ProductMaster boxWeight(Long boxWeight) {
        this.boxWeight = boxWeight;
        return this;
    }

    public void setBoxWeight(Long boxWeight) {
        this.boxWeight = boxWeight;
    }

    public Long getProductWeight() {
        return productWeight;
    }

    public ProductMaster productWeight(Long productWeight) {
        this.productWeight = productWeight;
        return this;
    }

    public void setProductWeight(Long productWeight) {
        this.productWeight = productWeight;
    }

    public Float getPrice() {
        return price;
    }

    public ProductMaster price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<PaperMaster> getPapers() {
        return papers;
    }

    public ProductMaster papers(Set<PaperMaster> paperMasters) {
        this.papers = paperMasters;
        return this;
    }

    public ProductMaster addPaper(PaperMaster paperMaster) {
        this.papers.add(paperMaster);
        paperMaster.getProducts().add(this);
        return this;
    }

    public ProductMaster removePaper(PaperMaster paperMaster) {
        this.papers.remove(paperMaster);
        paperMaster.getProducts().remove(this);
        return this;
    }

    public void setPapers(Set<PaperMaster> paperMasters) {
        this.papers = paperMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProductMaster productMaster = (ProductMaster) o;
        if (productMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductMaster{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", productDescription='" + getProductDescription() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", type='" + getType() + "'" +
            ", decal='" + getDecal() + "'" +
            ", length='" + getLength() + "'" +
            ", width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            ", reelSize='" + getReelSize() + "'" +
            ", sheetSize='" + getSheetSize() + "'" +
            ", surfaceArea='" + getSurfaceArea() + "'" +
            ", ply1='" + getPly1() + "'" +
            ", ply2='" + getPly2() + "'" +
            ", ply3='" + getPly3() + "'" +
            ", ply4='" + getPly4() + "'" +
            ", ply5='" + getPly5() + "'" +
            ", ply1Weight='" + getPly1Weight() + "'" +
            ", ply2Weight='" + getPly2Weight() + "'" +
            ", ply3Weight='" + getPly3Weight() + "'" +
            ", ply4Weight='" + getPly4Weight() + "'" +
            ", ply5Weight='" + getPly5Weight() + "'" +
            ", plateQty='" + getPlateQty() + "'" +
            ", partitionQty='" + getPartitionQty() + "'" +
            ", plateLength='" + getPlateLength() + "'" +
            ", plateWidth='" + getPlateWidth() + "'" +
            ", partitionLength='" + getPartitionLength() + "'" +
            ", partitionWidth='" + getPartitionWidth() + "'" +
            ", platePly1='" + getPlatePly1() + "'" +
            ", platePly2='" + getPlatePly2() + "'" +
            ", platePly3='" + getPlatePly3() + "'" +
            ", platePly4='" + getPlatePly4() + "'" +
            ", platePly5='" + getPlatePly5() + "'" +
            ", partitionPly1='" + getPartitionPly1() + "'" +
            ", partitionPly2='" + getPartitionPly2() + "'" +
            ", partitionPly3='" + getPartitionPly3() + "'" +
            ", partitionPly4='" + getPartitionPly4() + "'" +
            ", partitionPly5='" + getPartitionPly5() + "'" +
            ", platePly1Weight='" + getPlatePly1Weight() + "'" +
            ", platePly2Weight='" + getPlatePly2Weight() + "'" +
            ", platePly3Weight='" + getPlatePly3Weight() + "'" +
            ", platePly4Weight='" + getPlatePly4Weight() + "'" +
            ", platePly5Weight='" + getPlatePly5Weight() + "'" +
            ", partitionPly1Weight='" + getPartitionPly1Weight() + "'" +
            ", partitionPly2Weight='" + getPartitionPly2Weight() + "'" +
            ", partitionPly3Weight='" + getPartitionPly3Weight() + "'" +
            ", partitionPly4Weight='" + getPartitionPly4Weight() + "'" +
            ", partitionPly5Weight='" + getPartitionPly5Weight() + "'" +
            ", plateWeight='" + getPlateWeight() + "'" +
            ", partitionWeight='" + getPartitionWeight() + "'" +
            ", boxWeight='" + getBoxWeight() + "'" +
            ", productWeight='" + getProductWeight() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }
}
