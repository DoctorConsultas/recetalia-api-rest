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
@Table(name = "country")
public class Country {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "iso", nullable = false, length = 2)
  private String iso;

  @Column(name = "iso3", nullable = false, length = 3)
  private String iso3;

  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Column(name = "native_name", length = 150)
  private String nativeName;

  @Column(name = "capital", nullable = false, length = 150)
  private String capital;

  @Column(name = "latlng", length = 150)
  private String latlng;

  @Column(name = "flag", length = 150)
  private String flag;

  @Column(name = "currency", length = 150)
  private String currency;

  @Column(name = "currency_symbol", length = 150)
  private String currencySymbol;

  @Column(name = "numcode")
  private Integer numcode;

  @Column(name = "phonecode")
  private Integer phonecode;

  @ColumnDefault("0")
  @Column(name = "sort", nullable = false)
  private Integer sort;

}