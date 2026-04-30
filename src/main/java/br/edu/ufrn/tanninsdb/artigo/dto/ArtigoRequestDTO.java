package br.edu.ufrn.tanninsdb.artigo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de um novo artigo")
public class ArtigoRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Schema(example = "Análise de Taninos em Mimosa tenuiflora", description = "Título do artigo científico")
    private String titulo;

    @NotBlank(message = "O link da publicação é obrigatório")
    @Schema(example = "https://doi.org/10.1000/xyz123")
    private String link;

    @NotNull(message = "A porcentagem de fenólicos é obrigatória")
    @Schema(example = "15.5", description = "Porcentagem total de fenólicos")
    private Float fenolicos;

    @NotNull(message = "A porcentagem de taninos é obrigatória")
    @Schema(example = "10.2", description = "Porcentagem total de taninos")
    private Float taninos;

    @Schema(example = "5.5", description = "Porcentagem de taninos condensados (opcional)")
    private Float tCondensados;

    @Schema(example = "4.7", description = "Porcentagem de taninos hidrolisáveis (opcional)")
    private Float tHidrolisaveis;

    @NotBlank(message = "A metodologia é obrigatória")
    @Schema(example = "Folin-Ciocalteu", description = "Metodologia utilizada para análise")
    private String metodologia;

    @NotBlank(message = "O tipo de extração é obrigatório")
    @Schema(example = "Metanólica", description = "Tipo de solvente ou método de extração")
    private String tipoExtracao;

    @NotBlank(message = "A espécie é obrigatória")
    @Schema(example = "Mimosa tenuiflora", description = "Espécie da planta analisada")
    private String especie;

    @NotBlank(message = "O local de coleta é obrigatório")
    @Schema(example = "Caicó-RN", description = "Local geográfico de coleta da amostra")
    private String local;

    @NotBlank(message = "A parte da planta é obrigatória")
    @Schema(example = "Casca do caule", description = "Parte da planta utilizada na análise")
    private String partePlanta;

    @NotBlank(message = "A estação do ano é obrigatória")
    @Schema(example = "Inverno", description = "Estação do ano em que a coleta foi realizada")
    private String estacao;
}
