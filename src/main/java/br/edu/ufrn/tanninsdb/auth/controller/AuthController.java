package br.edu.ufrn.tanninsdb.auth.controller;

import br.edu.ufrn.tanninsdb.auth.dto.OrcidTokenResponse;
import br.edu.ufrn.tanninsdb.auth.service.JwtService;
import br.edu.ufrn.tanninsdb.auth.service.OrcidService;
import br.edu.ufrn.tanninsdb.config.OrcidProperties;
import br.edu.ufrn.tanninsdb.usuario.model.Role;
import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import br.edu.ufrn.tanninsdb.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para autenticação via ORCID")
public class AuthController {

    private final OrcidProperties orcidProperties;
    private final OrcidService orcidService;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @GetMapping("/orcid")
    @Operation(summary = "Redireciona para o login do ORCID")
    public void redirectToOrcid(HttpServletResponse response) throws IOException {

        String url = "https://orcid.org/oauth/authorize" +
                "?client_id=" + orcidProperties.getClientId() +
                "&response_type=code" +
                "&scope=/authenticate" +
                "&redirect_uri=" + orcidProperties.getRedirectUri();

        response.sendRedirect(url);
    }

    @GetMapping("/orcid/callback")
    @Operation(summary = "Callback do ORCID", description = "Recebe o código do ORCID, troca pelo token e gera o JWT do sistema")
    public Map<String, String> callback(@RequestParam String code) {

        OrcidTokenResponse orcidResponse = orcidService.exchangeCodeForToken(code);

        Usuario usuario = usuarioService.buscarPorEmail(orcidResponse.getOrcid() + "@orcid.org")
                .orElseGet(() -> {
                    Usuario novo = Usuario.builder()
                            .idOrcid(orcidResponse.getOrcid())
                            .nome(orcidResponse.getName())
                            .email(orcidResponse.getOrcid() + "@orcid.org")
                            .senha("") // Sem senha para usuários ORCID
                            .role(Role.PESQUISADOR)
                            .build();
                    return usuarioService.salvar(novo);
                });

        String token = jwtService.generateToken(usuario);

        return Map.of(
                "access_token", token,
                "name", usuario.getNome(),
                "orcid", orcidResponse.getOrcid()
        );
    }
}
