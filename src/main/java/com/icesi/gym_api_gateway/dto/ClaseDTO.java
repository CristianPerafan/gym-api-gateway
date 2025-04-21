package com.icesi.gym_api_gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClaseDTO {
    private Long id;
    private String nombre;
    private LocalDateTime horario;
}
