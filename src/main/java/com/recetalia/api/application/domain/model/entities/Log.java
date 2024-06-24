package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "log")
public class Log {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "classname", nullable = false)
  private String classname;

  @Column(name = "title", nullable = false)
  private String title;

  @Lob
  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "action", nullable = false)
  private String action;

  @Column(name = "link", nullable = false)
  private String link;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "updatedAt", nullable = false)
  private Instant updatedAt;

  @Column(name = "deletedAt")
  private Instant deletedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId")
  private User user;

}