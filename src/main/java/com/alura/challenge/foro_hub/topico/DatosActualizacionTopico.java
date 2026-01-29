package com.alura.challenge.foro_hub.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String nombreCurso) {
}
