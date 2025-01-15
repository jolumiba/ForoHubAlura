package Aluracursos.com.JLForoHub.controller;

import Aluracursos.com.JLForoHub.domain.respuesta.*;
import Aluracursos.com.JLForoHub.domain.topico.Estado;
import Aluracursos.com.JLForoHub.domain.topico.Topico;
import Aluracursos.com.JLForoHub.domain.topico.TopicoRepository;
import Aluracursos.com.JLForoHub.domain.usuario.Usuario;
import Aluracursos.com.JLForoHub.domain.usuario.UsuarioRepository;
import Aluracursos.com.JLForoHub.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<DatosRespuestaRsta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta dRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder){
        Usuario autor = usuarioRepository.findById(dRegistroRespuesta.usuarioId()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Topico topico = topicoRepository.findById(dRegistroRespuesta.topicoId()).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        Respuesta respuesta = respuestaRepository.save(new Respuesta(dRegistroRespuesta, autor, topico));
        DatosRespuestaRsta datosRespuestaRsta = new DatosRespuestaRsta(respuesta);
        URI url = uriComponentsBuilder.path("respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaRsta);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestas(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(DatosListadoRespuesta::new));
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<DatosRespuestaRsta> retornarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));
        DatosRespuestaRsta datosRespuestaRsta = new DatosRespuestaRsta(respuesta);
        return ResponseEntity.ok(datosRespuestaRsta);
    }

    @PutMapping("/marcar-solucion/{id}/{respuestaId}")
    @Transactional
    public ResponseEntity<DatosRespuestaRsta> marcarSolucion(@PathVariable Long id, @PathVariable Long respuestaId) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
        if (topico.getStatus() == Estado.SOLUCIONADO) {
            return ResponseEntity.badRequest().body(null);
        }
        Respuesta respuesta = respuestaRepository.findById(respuestaId).orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));
        respuesta.setSolucion(true);
        topico.setStatus(Estado.SOLUCIONADO);
        topicoRepository.save(topico);
        respuestaRepository.save(respuesta);
        return ResponseEntity.ok(new DatosRespuestaRsta(respuesta));
    }
}
