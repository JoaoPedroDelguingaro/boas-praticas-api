package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existsByPetIdAndStatus(Long petId, StatusAdocao status);

    boolean existsByTutorIdAndStatus(Long tutorId, StatusAdocao status);

    List<Adocao> findByTutorIdAndStatus(Long tutorId, StatusAdocao status);
}
