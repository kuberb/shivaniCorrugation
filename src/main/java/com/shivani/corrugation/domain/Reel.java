package com.shivani.corrugation.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Reel.
 */
@Entity
@Table(name = "reel")
@Document(indexName = "reel")
public class Reel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reel_number")
    private Long reelNumber;

    @Column(name = "decal")
    private Long decal;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "received_on")
    private LocalDate receivedOn;

    @Column(name = "lot_no")
    private Long lotNo;

    @ManyToOne
    private PaperMaster paper;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReelNumber() {
        return reelNumber;
    }

    public Reel reelNumber(Long reelNumber) {
        this.reelNumber = reelNumber;
        return this;
    }

    public void setReelNumber(Long reelNumber) {
        this.reelNumber = reelNumber;
    }

    public Long getDecal() {
        return decal;
    }

    public Reel decal(Long decal) {
        this.decal = decal;
        return this;
    }

    public void setDecal(Long decal) {
        this.decal = decal;
    }

    public Long getWeight() {
        return weight;
    }

    public Reel weight(Long weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public LocalDate getReceivedOn() {
        return receivedOn;
    }

    public Reel receivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
        return this;
    }

    public void setReceivedOn(LocalDate receivedOn) {
        this.receivedOn = receivedOn;
    }

    public Long getLotNo() {
        return lotNo;
    }

    public Reel lotNo(Long lotNo) {
        this.lotNo = lotNo;
        return this;
    }

    public void setLotNo(Long lotNo) {
        this.lotNo = lotNo;
    }

    public PaperMaster getPaper() {
        return paper;
    }

    public Reel paper(PaperMaster paperMaster) {
        this.paper = paperMaster;
        return this;
    }

    public void setPaper(PaperMaster paperMaster) {
        this.paper = paperMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reel reel = (Reel) o;
        if (reel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reel{" +
            "id=" + getId() +
            ", reelNumber='" + getReelNumber() + "'" +
            ", decal='" + getDecal() + "'" +
            ", weight='" + getWeight() + "'" +
            ", receivedOn='" + getReceivedOn() + "'" +
            ", lotNo='" + getLotNo() + "'" +
            "}";
    }
}
