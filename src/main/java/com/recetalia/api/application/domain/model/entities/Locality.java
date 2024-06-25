package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "localities")
public class Locality {
  @Id
  @ColumnDefault("''")
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @ColumnDefault("''")
  @Column(name = "region_id", nullable = false, length = 36)
  private String regionId;

  @ColumnDefault("''")
  @Column(name = "name", nullable = false, length = 191)
  private String name;

  @ColumnDefault("''")
  @Column(name = "slug", nullable = false, length = 191)
  private String slug;

  @ColumnDefault("''")
  @Column(name = "lat", nullable = false, length = 191)
  private String lat;

  @ColumnDefault("''")
  @Column(name = "lng", nullable = false, length = 191)
  private String lng;

}