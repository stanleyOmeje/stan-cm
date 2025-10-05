package com.stan.stancommons.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "account number is required")
    private String accountNumber;

    private String phoneNumber;

    @JsonIgnore
    @OneToOne
    private Transaction transaction;

    @JsonIgnore
    private Date createdAt;

}
