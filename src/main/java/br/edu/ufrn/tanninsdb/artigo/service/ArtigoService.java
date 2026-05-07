package br.edu.ufrn.tanninsdb.artigo.service;

import br.edu.ufrn.tanninsdb.artigo.dto.ArtigoRequestDTO;
import br.edu.ufrn.tanninsdb.artigo.dto.ArtigoResponseDTO;
import br.edu.ufrn.tanninsdb.artigo.model.Artigo;
import br.edu.ufrn.tanninsdb.artigo.model.StatusArtigo;
import br.edu.ufrn.tanninsdb.artigo.repository.ArtigoRepository;
import br.edu.ufrn.tanninsdb.usuario.model.Role;
import br.edu.ufrn.tanninsdb.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtigoService {

    private final ArtigoRepository artigoRepository;

    @Transactional
    public ArtigoResponseDTO salvar(ArtigoRequestDTO dto, Usuario autor) {
        Artigo artigo = Artigo.builder()
                .titulo(dto.getTitulo())
                .link(dto.getLink())
                .fenolicos(dto.getFenolicos())
                .taninos(dto.getTaninos())
                .tCondensados(dto.getTCondensados())
                .tHidrolisaveis(dto.getTHidrolisaveis())
                .metodologia(dto.getMetodologia())
                .tipoExtracao(dto.getTipoExtracao())
                .especie(dto.getEspecie())
                .local(dto.getLocal())
                .partePlanta(dto.getPartePlanta())
                .estacao(dto.getEstacao())
                .status(StatusArtigo.PENDENTE)
                .autor(autor)
                .build();

        Artigo salvo = artigoRepository.save(artigo);
        return ArtigoResponseDTO.fromEntity(salvo);
    }

    public List<ArtigoResponseDTO> listarTodos() {
        return artigoRepository.findAll().stream()
                .map(ArtigoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ArtigoResponseDTO buscarPorId(Long id) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));
        return ArtigoResponseDTO.fromEntity(artigo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!artigoRepository.existsById(id)) {
            throw new RuntimeException("Artigo não encontrado");
        }
        artigoRepository.deleteById(id);
    }

    @Transactional
    public ArtigoResponseDTO atualizar(Long id, ArtigoRequestDTO dto, Usuario usuarioLogado) {
        Artigo artigo = artigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artigo não encontrado"));

        boolean isAutor = artigo.getAutor().getId().equals(usuarioLogado.getId());
        boolean hasPermission = usuarioLogado.getRole() == Role.ADMINISTRADOR || usuarioLogado.getRole() == Role.CURADOR;

        if (!isAutor && !hasPermission) {
            throw new RuntimeException("Você não tem permissão para editar este artigo");
        }

        artigo.setTitulo(dto.getTitulo());
        artigo.setLink(dto.getLink());
        artigo.setFenolicos(dto.getFenolicos());
        artigo.setTaninos(dto.getTaninos());
        artigo.setTCondensados(dto.getTCondensados());
        artigo.setTHidrolisaveis(dto.getTHidrolisaveis());
        artigo.setMetodologia(dto.getMetodologia());
        artigo.setTipoExtracao(dto.getTipoExtracao());
        artigo.setEspecie(dto.getEspecie());
        artigo.setLocal(dto.getLocal());
        artigo.setPartePlanta(dto.getPartePlanta());
        artigo.setEstacao(dto.getEstacao());

        if (isAutor && usuarioLogado.getRole() == Role.PESQUISADOR) {
            artigo.setStatus(StatusArtigo.PENDENTE);
        }

        Artigo atualizado = artigoRepository.save(artigo);
        return ArtigoResponseDTO.fromEntity(atualizado);
    }
}
