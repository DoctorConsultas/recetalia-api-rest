package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "lastname", nullable = false, length = 150)
  private String lastname;

  @Column(name = "email", length = 200)
  private String email;

  @Lob
  @Column(name = "phone", nullable = false)
  private String phone;

  @Lob
  @Column(name = "document", nullable = false)
  private String document;

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

  @Column(name = "user", nullable = false, length = 200)
  private String user;

  @Column(name = "password", nullable = false, length = 300)
  private String password;

  @Column(name = "birthdate", nullable = false, length = 150)
  private String birthdate;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "avatarId")
  private File avatar;

  @Column(name = "sex", length = 200)
  private String sex;

}