package com.alura.challenge.foro_hub.controller;

import com.alura.challenge.foro_hub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private RegistroDeTopicos registro;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder){
        var topico = registro.registrar(datos);
        var datosRespuesta = new DatosRespuestaTopico(topico);
        var url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size=10, sort={"fechaCreacion"}, direction = Sort.Direction.ASC) Pageable paginacion){
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topico = repository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosListaTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoOptional.get();
        //REGLA DE NEGOCIO: Validar duplicados (titulo y mensaje iguales)
        boolean yaExiste = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

        if (yaExiste && (!topico.getTitulo().equals(datos.titulo()) || !topico.getMensaje().equals(datos.mensaje()))) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        topico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.eliminar();
        return ResponseEntity.noContent().build();
    }

}

