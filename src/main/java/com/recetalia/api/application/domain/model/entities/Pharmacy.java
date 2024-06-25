package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pharmacy")
public class Pharmacy {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

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

  @Lob
  @Column(name = "camera")
  private String camera;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "franchiseId")
  private Franchise franchise;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "logoId")
  private File logo;

  @Column(name = "passwordForgotCode", length = 40)
  private String passwordForgotCode;

  @Column(name = "managerName", nullable = false, length = 150)
  private String managerName;

  @Column(name = "managerLastname", nullable = false, length = 150)
  private String managerLastname;

  @Column(name = "managerCJP", nullable = false, length = 150)
  private String managerCJP;

  @ColumnDefault("'INACTIVE'")
  @Column(name = "status", nullable = false)
  private String status;

  @Lob
  @Column(name = "managerDocument")
  private String managerDocument;

}