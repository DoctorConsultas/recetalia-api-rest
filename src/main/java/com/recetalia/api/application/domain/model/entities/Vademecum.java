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
@Table(name = "vademecum")
public class Vademecum {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "fileUrl", nullable = false, length = 200)
  private String fileUrl;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

}