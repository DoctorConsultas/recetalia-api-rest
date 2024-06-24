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
@Table(name = "medic_especiality")
public class MedicEspeciality {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "name", nullable = false, length = 191)
  private String name;

  @Column(name = "slug", length = 191)
  private String slug;

  @Column(name = "tags")
  private String tags;

  @Column(name = "description")
  private String description;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "updated_at")
  private String updatedAt;

}