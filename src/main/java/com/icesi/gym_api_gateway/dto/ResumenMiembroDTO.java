package com.icesi.gym_api_gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResumenMiembroDTO {
    private MiembroDTO miembro;
    private List<ClaseDTO> clases;
}
