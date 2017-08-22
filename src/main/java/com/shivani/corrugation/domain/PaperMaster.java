package com.shivani.corrugation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PaperMaster.
 */
@Entity
@Table(name = "paper_master")
@Document(indexName = "papermaster")
public class PaperMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mill")
    private String mill;

    @Column(name = "gsm")
    private Integer gsm;

    @Column(name = "bf")
    private Integer bf;

    @Column(name = "price")
    private Float price;

    @ManyToMany(mappedBy = "papers")
    @JsonIgnore
    private Set<ProductMaster> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PaperMaster name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public PaperMaster description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMill() {
        return mill;
    }

    public PaperMaster mill(String mill) {
        this.mill = mill;
        return this;
    }

    public void setMill(String mill) {
        this.mill = mill;
    }

    public Integer getGsm() {
        return gsm;
    }

    public PaperMaster gsm(Integer gsm) {
        this.gsm = gsm;
        return this;
    }

    public void setGsm(Integer gsm) {
        this.gsm = gsm;
    }

    public Integer getBf() {
        return bf;
    }

    public PaperMaster bf(Integer bf) {
        this.bf = bf;
        return this;
    }

    public void setBf(Integer bf) {
        this.bf = bf;
    }

    public Float getPrice() {
        return price;
    }

    public PaperMaster price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<ProductMaster> getProducts() {
        return products;
    }

    public PaperMaster products(Set<ProductMaster> productMasters) {
        this.products = productMasters;
        return this;
    }

    public PaperMaster addProduct(ProductMaster productMaster) {
        this.products.add(productMaster);
        productMaster.getPapers().add(this);
        return this;
    }

    public PaperMaster removeProduct(ProductMaster productMaster) {
        this.products.remove(productMaster);
        productMaster.getPapers().remove(this);
        return this;
    }

    public void setProducts(Set<ProductMaster> productMasters) {
        this.products = productMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaperMaster paperMaster = (PaperMaster) o;
        if (paperMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paperMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaperMaster{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", mill='" + getMill() + "'" +
            ", gsm='" + getGsm() + "'" +
            ", bf='" + getBf() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }
}
