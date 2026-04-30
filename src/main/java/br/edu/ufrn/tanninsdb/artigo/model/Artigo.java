package br.edu.ufrn.tanninsdb.artigo.model;

import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artigos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "autor")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String link;

    private Float fenolicos;
    private Float taninos;
    private Float tCondensados;
    private Float tHidrolisaveis;

    @Column(length = 1000)
    private String metodologia;

    private String tipoExtracao;
    private String especie;
    private String local;
    private String partePlanta;
    private String estacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatusArtigo status = StatusArtigo.PENDENTE;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;
}
