package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "notification_template")
public class NotificationTemplate {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "subject", nullable = false, length = 150)
  private String subject;

  @Lob
  @Column(name = "template", nullable = false)
  private String template;

  @ColumnDefault("0")
  @Column(name = "sendBySms", nullable = false)
  private Byte sendBySms;

  @ColumnDefault("0")
  @Column(name = "sendByWhatsapp", nullable = false)
  private Byte sendByWhatsapp;

  @ColumnDefault("0")
  @Column(name = "sendByEmail", nullable = false)
  private Byte sendByEmail;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "typeId", nullable = false)
  private NotificationType type;

}