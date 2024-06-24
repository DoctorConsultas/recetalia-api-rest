package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notification_type")
public class NotificationType {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "code", nullable = false, length = 40)
  private String code;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "description", length = 300)
  private String description;

}