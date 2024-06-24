package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "prescription")
public class Prescription {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "expireAt")
  private Instant expireAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "medicId", nullable = false)
  private Medic medic;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "patientId", nullable = false)
  private Patient patient;

  @Column(name = "code", nullable = false, length = 40)
  private String code;

  @ColumnDefault("'AVAILABLE'")
  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "dose")
  private Integer dose;

  @Column(name = "doseUnit", length = 50)
  private String doseUnit;

  @Column(name = "frecuency")
  private Integer frecuency;

  @Column(name = "frecuencyUnit", length = 50)
  private String frecuencyUnit;

  @Column(name = "medicalHistory", length = 500)
  private String medicalHistory;

  @Column(name = "affections", length = 500)
  private String affections;

  @Column(name = "duration")
  private Integer duration;

  @Column(name = "durationUnit", length = 50)
  private String durationUnit;

  @Column(name = "productType", nullable = false, length = 8)
  private String productType;

  @Column(name = "productId", nullable = false, length = 40)
  private String productId;

  @Column(name = "doseType", length = 50)
  private String doseType;

  @ColumnDefault("0")
  @Column(name = "dispensationPendingReminderSended", nullable = false)
  private Byte dispensationPendingReminderSended;

}