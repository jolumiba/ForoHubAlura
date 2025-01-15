package Aluracursos.com.JLForoHub.domain.respuesta;

import Aluracursos.com.JLForoHub.domain.topico.Topico;
import Aluracursos.com.JLForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long usuarioId
) {
}