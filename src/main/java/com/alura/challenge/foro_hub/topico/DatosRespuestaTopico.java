package com.alura.challenge.foro_hub.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fechaCreacion,
        Long idUsuario) {

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getNombreCurso(),
                topico.getFechaCreacion(),
                topico.getAutor().getId());
    }
}
