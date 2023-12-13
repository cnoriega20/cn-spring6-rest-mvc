package com.example.cnspring6restmvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

   @Id
    private UUID id;
    private String customerName;
    private String version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
