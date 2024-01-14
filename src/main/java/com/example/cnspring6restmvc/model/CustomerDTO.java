package com.example.cnspring6restmvc.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private UUID id;
    private String customerName;
    private Long version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}


