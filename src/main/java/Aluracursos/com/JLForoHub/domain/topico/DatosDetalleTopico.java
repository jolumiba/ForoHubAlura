package Aluracursos.com.JLForoHub.domain.topico;

import Aluracursos.com.JLForoHub.domain.curso.DatosRespuestaCurso;
import Aluracursos.com.JLForoHub.domain.respuesta.DatosRegistroRespuesta;
import Aluracursos.com.JLForoHub.domain.usuario.DatosRespuestaUsuario;

import java.time.LocalDateTime;
import java.util.List;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        DatosRespuestaCurso curso,
        Estado status,
        LocalDateTime fecha,
        DatosRespuestaUsuario autor,
        String mensaje,
        List<DatosRegistroRespuesta> respuestas

) {
}
