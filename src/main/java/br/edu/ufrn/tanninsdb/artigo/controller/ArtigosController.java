package br.edu.ufrn.tanninsdb.artigo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtigosController {

    @GetMapping(value = "/artigos/novo", produces = "text/html")
    public String novoArtigo() {
        return """
        <html>
            <head>
                <meta charset="UTF-8">
                <title>Novo Artigo</title>
            </head>
            <body style="font-family: Arial; margin: 40px;">
                <h2>Cadastro de Artigo</h2>
                <form action="/artigos/salvar" method="post">
                    <label>Título:</label><br>
                    <input type="text" name="titulo"><br><br>
                    
                    <label>Porcentagem de Fenólicos:</label><br>
                    <input type="text" name="porcentagemFenolicos"><br><br>
                    
                    <label>Porcentagem de Taninos:</label><br>
                    <input type="text" name="porcentagemTaninos"><br><br>
                    
                    <label>Taninos condensados(opcional):</label><br>
                    <input type="text" name="taninosCondensados"><br><br>
                    
                    <label>Taninos Hidrolizáveis(opcional):</label><br>
                    <input type="text" name="taninosHidrolizaveis"><br><br>
                    
                    <label>Metodologia utilizada:</label><br>
                    <input type="text" name="metodologia"><br><br>
                    
                    <label>Tipo de extração:</label><br>
                    <input type="text" name="tipoExtracao"><br><br>
                    
                    <label>Espécie:</label><br>
                    <input type="text" name="especie"><br><br>
                    
                    <label>Local coleta:</label><br>
                    <input type="text" name="localColeta"><br><br>
                    
                    <label>Parte da planta:</label><br>
                    <input type="text" name="partePlanta"><br><br>
                    
                    <label>Estação do ano:</label><br>
                    <input type="text" name="estacaoAno"><br><br>
                    
                    <label>Link da publicação:</label><br>
                    <input type="text" name="linkPublicacao"><br><br>
                    
                    <button type="submit">Enviar</button>
                </form>
            </body>
        </html>
        """;
    }


    @PostMapping(value = "/artigos/salvar", produces = "text/html")
    public String salvarArtigo(
            @RequestParam String titulo,
            @RequestParam String porcentagemFenolicos,
            @RequestParam String porcentagemTaninos,
            @RequestParam(required = false) String taninosCondensados,
            @RequestParam(required = false) String taninosHidrolizaveis,
            @RequestParam String metodologia,
            @RequestParam String tipoExtracao,
            @RequestParam String especie,
            @RequestParam String localColeta,
            @RequestParam String partePlanta,
            @RequestParam String estacaoAno,
            @RequestParam String linkPublicacao) {

        return """
    <html>
        <head>
            <meta charset="UTF-8">
            <title>Artigo Salvo</title>
        </head>
        <body style="font-family: Arial; margin: 40px;">
            <h2>Artigo recebido com sucesso!</h2>
            <hr>

            <p><strong>Título:</strong> %s</p>
            <p><strong>%% Fenólicos:</strong> %s</p>
            <p><strong>%% Taninos:</strong> %s</p>
            <p><strong>Taninos Condensados:</strong> %s</p>
            <p><strong>Taninos Hidrolizáveis:</strong> %s</p>
            <p><strong>Metodologia:</strong> %s</p>
            <p><strong>Tipo de Extração:</strong> %s</p>
            <p><strong>Espécie:</strong> %s</p>
            <p><strong>Local de Coleta:</strong> %s</p>
            <p><strong>Parte da Planta:</strong> %s</p>
            <p><strong>Estação do Ano:</strong> %s</p>
            <p><strong>Link:</strong> %s</p>

            <hr>
            <a href="/artigos/novo">Cadastrar novo artigo</a><br>
            <a href="/">Voltar ao início</a>
        </body>
    </html>
    """.formatted(
                titulo,
                porcentagemFenolicos,
                porcentagemTaninos,
                taninosCondensados != null ? taninosCondensados : "Não informado",
                taninosHidrolizaveis != null ? taninosHidrolizaveis : "Não informado",
                metodologia,
                tipoExtracao,
                especie,
                localColeta,
                partePlanta,
                estacaoAno,
                linkPublicacao
        );
    }
}
