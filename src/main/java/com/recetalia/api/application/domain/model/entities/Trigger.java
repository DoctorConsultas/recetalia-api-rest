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
@Table(name = "`trigger`")
public class Trigger {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "event", nullable = false, length = 150)
  private String event;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "entity", nullable = false, length = 150)
  private String entity;

  @Column(name = "entityId", nullable = false, length = 150)
  private String entityId;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("'PENDING'")
  @Column(name = "status", nullable = false)
  private String status;

}