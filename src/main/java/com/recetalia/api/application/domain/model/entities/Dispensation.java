package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "dispensation")
public class Dispensation {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @ColumnDefault("1")
  @Column(name = "qty", nullable = false)
  private Integer qty;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "prescriptionId", nullable = false)
  private Prescription prescription;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "pharmacyId", nullable = false)
  private Pharmacy pharmacy;

  @ColumnDefault("'DISPENSED'")
  @Column(name = "status", nullable = false)
  private String status;

  @ColumnDefault("'N'")
  @Column(name = "substitute", nullable = false)
  private String substitute;

  @Column(name = "loteNumber", nullable = false, length = 150)
  private String loteNumber;

  @Column(name = "loteExpireAt")
  private Instant loteExpireAt;

  @Column(name = "dispensedToName", nullable = false, length = 150)
  private String dispensedToName;

  @Column(name = "dispensedToLastname", nullable = false, length = 150)
  private String dispensedToLastname;

  @Lob
  @Column(name = "dispensedToDocument", nullable = false)
  private String dispensedToDocument;

  @Column(name = "dispensedToAddressCity", length = 200)
  private String dispensedToAddressCity;

  @Column(name = "dispensedToAddressStreet", length = 200)
  private String dispensedToAddressStreet;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dispensedToAddressCountryId")
  private Country dispensedToAddressCountry;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "dispensedById", nullable = false)
  private PharmacyDispenser dispensedBy;

  @Column(name = "productId", nullable = false, length = 40)
  private String productId;

  @Column(name = "productType", nullable = false, length = 8)
  private String productType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dispensedCancelledById")
  private PharmacyDispenser dispensedCancelledBy;

}