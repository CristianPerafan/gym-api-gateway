package com.icesi.gym_api_gateway.controller;


import com.icesi.gym_api_gateway.dto.ClaseDTO;
import com.icesi.gym_api_gateway.dto.MiembroDTO;
import com.icesi.gym_api_gateway.dto.ResumenMiembroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/miembro/resumen")
@RequiredArgsConstructor
public class MiembroResumenController {

    private final WebClient.Builder webClientBuilder;


    @GetMapping("/{id}")
    public Mono<ResumenMiembroDTO> getResumenMiembro(@PathVariable Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        System.out.println("ID: " + id);

        WebClient webClient = webClientBuilder.build();

        Mono<MiembroDTO> miembroMono = webClient.get()
                .uri("http://microservicio-miembro/api/miembros/get/" + id)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(MiembroDTO.class);

        Mono<ClaseDTO[]> clasesMono = webClient.get()
                .uri("http://microservicio-clase/api/clase/clases-miembro/" + id)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(ClaseDTO[].class);

        return Mono.zip(miembroMono, clasesMono)
                .map(tuple -> {
                    MiembroDTO miembroDTO = tuple.getT1();
                    ClaseDTO[] clasesArray = tuple.getT2();

                    return new ResumenMiembroDTO(miembroDTO, List.of(clasesArray));
                });
    }



}
