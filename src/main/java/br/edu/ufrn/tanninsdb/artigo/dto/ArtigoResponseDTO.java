package br.edu.ufrn.tanninsdb.artigo.dto;

import br.edu.ufrn.tanninsdb.artigo.model.Artigo;
import br.edu.ufrn.tanninsdb.artigo.model.StatusArtigo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados de retorno de um artigo")
public class ArtigoResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Análise de Taninos em Mimosa tenuiflora")
    private String titulo;

    @Schema(example = "https://doi.org/10.1000/xyz123")
    private String link;

    @Schema(example = "15.5")
    private Float fenolicos;

    @Schema(example = "10.2")
    private Float taninos;

    @Schema(example = "5.5")
    private Float tCondensados;

    @Schema(example = "4.7")
    private Float tHidrolisaveis;

    @Schema(example = "Folin-Ciocalteu")
    private String metodologia;

    @Schema(example = "Metanólica")
    private String tipoExtracao;

    @Schema(example = "Mimosa tenuiflora")
    private String especie;

    @Schema(example = "Caicó-RN")
    private String local;

    @Schema(example = "Casca do caule")
    private String partePlanta;

    @Schema(example = "Inverno")
    private String estacao;

    @Schema(example = "APROVADO")
    private StatusArtigo status;

    @Schema(example = "João Silva")
    private String nomeAutor;

    public static ArtigoResponseDTO fromEntity(Artigo artigo) {
        return ArtigoResponseDTO.builder()
                .id(artigo.getId())
                .titulo(artigo.getTitulo())
                .link(artigo.getLink())
                .fenolicos(artigo.getFenolicos())
                .taninos(artigo.getTaninos())
                .tCondensados(artigo.getTCondensados())
                .tHidrolisaveis(artigo.getTHidrolisaveis())
                .metodologia(artigo.getMetodologia())
                .tipoExtracao(artigo.getTipoExtracao())
                .especie(artigo.getEspecie())
                .local(artigo.getLocal())
                .partePlanta(artigo.getPartePlanta())
                .estacao(artigo.getEstacao())
                .status(artigo.getStatus())
                .nomeAutor(artigo.getAutor() != null ? artigo.getAutor().getNome() : null)
                .build();
    }
}
