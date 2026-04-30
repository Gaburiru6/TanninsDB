package br.edu.ufrn.tanninsdb.auth.controller;

import br.edu.ufrn.tanninsdb.auth.service.JwtService;
import br.edu.ufrn.tanninsdb.usuario.model.Role;
import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import br.edu.ufrn.tanninsdb.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth/dev")
@RequiredArgsConstructor
@Profile("dev")
@Tag(name = "Dev Auth", description = "Endpoint de autenticação para desenvolvimento (sem ORCID)")
public class Devlogin {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping
    @Operation(summary = "Gera um token JWT para testes locais", description = "Cria um usuário dummy se não existir e retorna o token")
    public Map<String, String> devLogin() {
        String email = "dev@tanninsdb.com";
        
        Usuario usuario = usuarioService.buscarPorEmail(email)
                .orElseGet(() -> {
                    Usuario novo = Usuario.builder()
                            .nome("Developer User")
                            .email(email)
                            .idOrcid("0000-0000-0000-0000")
                            .senha("")
                            .role(Role.ADMINISTRADOR)
                            .build();
                    return usuarioService.salvar(novo);
                });

        String token = jwtService.generateToken(usuario);

        return Map.of(
                "access_token", token,
                "name", usuario.getNome(),
                "role", usuario.getRole().name()
        );
    }
}
