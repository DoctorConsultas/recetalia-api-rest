package com.recetalia.api.application.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "notification_old")
public class NotificationOld {
  @Id
  @Column(name = "id", nullable = false, length = 36)
  private String id;

  @Column(name = "phone", nullable = false, length = 200)
  private String phone;

  @Column(name = "subject", nullable = false, length = 200)
  private String subject;

  @Lob
  @Column(name = "message", nullable = false)
  private String message;

  @Column(name = "link", nullable = false, length = 300)
  private String link;

  @ColumnDefault("0")
  @Column(name = "sendBySms", nullable = false)
  private Byte sendBySms;

  @Column(name = "sendedSmsAt")
  private Instant sendedSmsAt;

  @ColumnDefault("0")
  @Column(name = "sendByWhatsapp", nullable = false)
  private Byte sendByWhatsapp;

  @Column(name = "sendedWhatsappAt")
  private Instant sendedWhatsappAt;

  @ColumnDefault("0")
  @Column(name = "sendByEmail", nullable = false)
  private Byte sendByEmail;

  @Column(name = "sendedEmailAt")
  private Instant sendedEmailAt;

  @ColumnDefault("current_timestamp(6)")
  @Column(name = "createdAt", nullable = false)
  private Instant createdAt;

  @ColumnDefault("'PENDING'")
  @Lob
  @Column(name = "status", nullable = false)
  private String status;

  @Column(name = "typeId", nullable = false, length = 36)
  private String typeId;

  @Column(name = "patientId", length = 36)
  private String patientId;

  @Column(name = "medicId", length = 36)
  private String medicId;

  @Column(name = "pharmacyId", length = 36)
  private String pharmacyId;

}