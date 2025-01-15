package Aluracursos.com.JLForoHub.domain.topico;

import Aluracursos.com.JLForoHub.domain.curso.Curso;
import Aluracursos.com.JLForoHub.domain.respuesta.Respuesta;
import Aluracursos.com.JLForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long cursoId,
        @NotNull
        Long usuarioId

) {
}
