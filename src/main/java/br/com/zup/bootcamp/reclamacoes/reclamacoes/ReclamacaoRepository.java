package br.com.zup.bootcamp.reclamacoes.reclamacoes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao,Long> {
    boolean existsByTelefoneUsuarioOrTexto(String telefoneUsuario, String texto);
}
