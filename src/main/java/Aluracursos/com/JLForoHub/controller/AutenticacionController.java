package Aluracursos.com.JLForoHub.controller;

import Aluracursos.com.JLForoHub.domain.usuario.DatosAutenticacionUser;
import Aluracursos.com.JLForoHub.domain.usuario.Usuario;
import Aluracursos.com.JLForoHub.infra.security.DatosJWTtoken;
import Aluracursos.com.JLForoHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTtoken> autenticarUser(@RequestBody @Valid DatosAutenticacionUser dAutenticacionUser) {
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(dAutenticacionUser.email(), dAutenticacionUser.clave());
            Authentication userAutenticado = authenticationManager.authenticate(authToken);
            String JWTtoken = tokenService.makeToken((Usuario) userAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(new DatosJWTtoken("Credenciales incorrectas"));
        }
    }
}
