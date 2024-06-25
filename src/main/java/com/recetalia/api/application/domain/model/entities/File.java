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
@Table(name = "file")
public class File {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "filename", nullable = false, length = 200)
  private String filename;

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "urlThumb")
  private String urlThumb;

  @Column(name = "type", nullable = false, length = 20)
  private String type;

  @Column(name = "extension", nullable = false, length = 20)
  private String extension;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

}