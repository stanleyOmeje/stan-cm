package com.stan.stancommons.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class MerchantNotificationConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String merchantId;
    private boolean enableSms;
    private boolean enableEmail;
    private String notificatonUrl;
    private Date createdAt;
    private Date updatedAt;
}
