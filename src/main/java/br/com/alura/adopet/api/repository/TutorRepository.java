package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.dto.TutorDto.CadastraTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    boolean existsByEmailOrTelefone(String telefone, String email);
}
