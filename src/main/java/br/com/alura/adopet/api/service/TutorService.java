package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.TutorDto.CadastraTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastraTutorDto dto) {
        var dadosExistem = verificaDados(dto.telefone(), dto.email());

        if (dadosExistem) {
            throw new ValidacaoException("Um ou mais dados ja cadastrados!");
        }
        repository.save(new Tutor(dto));
    }

    public void atualizar(AtualizarTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());

        var dadosExistem = verificaDados(dto.telefone(), dto.email());

        if (dadosExistem) {
            throw new ValidacaoException("Um ou mais dados ja cadastrados!");
        }
        tutor.atualizarDados(dto);

    }

    public boolean verificaDados(String email, String telefone) {
        return repository.existsByEmailOrTelefone(telefone, email);
    }

}

