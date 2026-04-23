package br.edu.ufrn.tanninsdb.auth.controller;

import br.edu.ufrn.tanninsdb.config.OrcidProperties;
import br.edu.ufrn.tanninsdb.auth.dto.OrcidTokenResponse;
import br.edu.ufrn.tanninsdb.auth.service.OrcidService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final OrcidProperties orcidProperties;
    private final OrcidService orcidService;

    public AuthController(OrcidProperties orcidProperties,
                          OrcidService orcidService) {
        this.orcidProperties = orcidProperties;
        this.orcidService = orcidService;
    }

    @GetMapping("/orcid")
    public void redirectToOrcid(HttpServletResponse response) throws IOException {

        String url = "https://orcid.org/oauth/authorize" +
                "?client_id=" + orcidProperties.getClientId() +
                "&response_type=code" +
                "&scope=/authenticate" +
                "&redirect_uri=" + orcidProperties.getRedirectUri();

        response.sendRedirect(url);
    }

    @GetMapping(value = "/orcid/callback", produces = "text/html")
    public String callback(@RequestParam String code) {

        OrcidTokenResponse response =
                orcidService.exchangeCodeForToken(code);

        return """
    <html>
        <head>
            <meta http-equiv="refresh" content="2;url=/artigos/novo">
        </head>
        <body style="font-family: Arial; margin: 40px;">
            <h2>Login validado com sucesso</h2>
            <p>Redirecionando para cadastro de artigo...</p>
        </body>
    </html>
    """;
    }
}