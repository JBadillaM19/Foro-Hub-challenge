package com.alura.challenge.foro_hub.topico;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime fechaCreacion,
        String nombreAutor) {

    public DatosListaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getNombreCurso(),
                topico.getFechaCreacion(),
                topico.getAutor().getNombre()
        );
    }
}
