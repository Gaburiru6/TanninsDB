package br.edu.ufrn.tanninsdb.artigo.controller;

import br.edu.ufrn.tanninsdb.artigo.dto.ArtigoRequestDTO;
import br.edu.ufrn.tanninsdb.artigo.dto.ArtigoResponseDTO;
import br.edu.ufrn.tanninsdb.artigo.service.ArtigoService;
import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artigos")
@RequiredArgsConstructor
@Tag(name = "Artigos", description = "Gerenciamento de artigos científicos")
public class ArtigosController {

    private final ArtigoService artigoService;

    @PostMapping
    @Operation(summary = "Salva um novo artigo", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ArtigoResponseDTO> salvar(
            @RequestBody @Valid ArtigoRequestDTO dto,
            @AuthenticationPrincipal Usuario usuarioLogado) {
        
        ArtigoResponseDTO salvo = artigoService.salvar(dto, usuarioLogado);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    @Operation(summary = "Lista todos os artigos")
    public ResponseEntity<List<ArtigoResponseDTO>> listar() {
        return ResponseEntity.ok(artigoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um artigo por ID")
    public ResponseEntity<ArtigoResponseDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(artigoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um artigo", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        artigoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
