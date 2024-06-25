package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "medical_provider")
public class MedicalProvider {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "medicalProviderTypeId", nullable = false, length = 36)
  private String medicalProviderTypeId;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "addressCountryId", length = 36)
  private String addressCountryId;

  @Column(name = "addressLocalityId", length = 36)
  private String addressLocalityId;

  @Column(name = "addressStreet", length = 200)
  private String addressStreet;

  @Column(name = "addressNumber", length = 200)
  private String addressNumber;

  @Column(name = "addressComments", length = 200)
  private String addressComments;

  @Lob
  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "email", nullable = false, length = 200)
  private String email;

  @Column(name = "password", nullable = false, length = 300)
  private String password;

  @Column(name = "businessName", nullable = false, length = 150)
  private String businessName;

  @Column(name = "rut", nullable = false, length = 150)
  private String rut;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @Column(name = "logoId", length = 36)
  private String logoId;

  @Column(name = "passwordForgotCode", length = 40)
  private String passwordForgotCode;

  @ColumnDefault("'INACTIVE'")
  @Column(name = "status", nullable = false)
  private String status;

}