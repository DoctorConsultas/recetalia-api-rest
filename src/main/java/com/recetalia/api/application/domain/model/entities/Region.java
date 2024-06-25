package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region {
  @Id
  @ColumnDefault("''")
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 191)
  private String name;

  @Column(name = "slug", nullable = false, length = 191)
  private String slug;

  @Column(name = "lat", nullable = false, length = 191)
  private String lat;

  @Column(name = "lng", nullable = false, length = 191)
  private String lng;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

}