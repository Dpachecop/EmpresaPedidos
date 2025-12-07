package com.ingsoftw.EmpresaPedidos.infrastructure.adapters.input.rest;

import com.ingsoftw.EmpresaPedidos.application.ports.output.UserRepositoryPort;
import com.ingsoftw.EmpresaPedidos.domain.model.Usuario;
import com.ingsoftw.EmpresaPedidos.infrastructure.security.JwtService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        var usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol("USER")
                .build();

        userRepositoryPort.save(usuario);

        // Generamos token automáticamente al registrarse (opcional)
        // Para este ejemplo, solo devolvemos mensaje de éxito o token
        return ResponseEntity.ok(new AuthResponse("Usuario registrado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Si la autenticación funciona, buscamos al usuario para generar el token
        var user = userRepositoryPort.findByUsername(request.getUsername()).orElseThrow();
        // Ojo: necesitamos convertir nuestro Usuario de dominio a UserDetails,
        // pero podemos usar un "truco" rápido creando un objeto User de Spring Security:
        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), java.util.Collections.emptyList());

        var jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}

@Data
@lombok.AllArgsConstructor
class AuthResponse {
    private String token;
}