package br.edu.ufrn.phenolicsdb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return """
        <html>
            <head>
                <meta charset="UTF-8">
                <title>PhenolicsDB</title>
            </head>
            <body style="font-family: Arial; margin: 40px;">
                <h1>PhenolicsDB</h1>
                <p>Plataforma para cadastro de artigos científicos.</p>
                <hr>
                <a href="/auth/orcid">Entrar com ORCID</a>
            </body>
        </html>
        """;
    }
}
