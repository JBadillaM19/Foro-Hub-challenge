package com.alura.challenge.foro_hub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotNull Long idUsuario,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String nombreCurso) {
}
