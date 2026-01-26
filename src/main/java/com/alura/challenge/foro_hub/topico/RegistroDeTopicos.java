package com.alura.challenge.foro_hub.topico;

import com.alura.challenge.foro_hub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Topico registrar(DatosRegistroTopico datos) {
        // Validación: Verificar si ya existe el tópico
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
        }

        var usuario = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));

        // Si pasa la validación, convertimos el DTO a la Entidad y guardamos
        var topico = new Topico(datos, usuario);
        return topicoRepository.save(topico);
    }
}

