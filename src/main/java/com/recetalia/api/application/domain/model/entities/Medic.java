package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "medic")
public class Medic {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "lastname", nullable = false, length = 150)
  private String lastname;

  @Column(name = "gender", length = 150)
  private String gender;

  @Column(name = "email", nullable = false, length = 200)
  private String email;

  @Column(name = "password", nullable = false, length = 300)
  private String password;

  @Lob
  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "birthdate", nullable = false, length = 150)
  private String birthdate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "addressCountryId")
  private Country addressCountry;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "addressLocalityId")
  private Locality addressLocality;

  @Column(name = "addressStreet", length = 200)
  private String addressStreet;

  @Column(name = "addressNumber", length = 200)
  private String addressNumber;

  @Column(name = "addressComments", length = 200)
  private String addressComments;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @Column(name = "cjp", nullable = false, length = 150)
  private String cjp;

  @Column(name = "passwordForgotCode", length = 40)
  private String passwordForgotCode;

  @ColumnDefault("'INACTIVE'")
  @Column(name = "status", nullable = false)
  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "especialityId")
  private MedicEspeciality especiality;

  @Column(name = "medicalProviderId", length = 200)
  private String medicalProviderId;

}