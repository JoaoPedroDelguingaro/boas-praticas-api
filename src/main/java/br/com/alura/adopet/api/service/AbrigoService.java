package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto.AbrigoDto;
import br.com.alura.adopet.api.dto.AbrigoDto.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AbrigoRepository repository;

    public List<AbrigoDto> listar() {
        return repository
                .findAll()
                .stream()
                .map(AbrigoDto::new)
                .toList();
    }

    public void cadastrar(CadastraAbrigoDto dto) {
        var dadosRepetidos = repository
                .existsByTelefoneOrNomeOrEmail(dto.telefone(), dto.nome(), dto.email());
        if (dadosRepetidos) {
            throw new ValidacaoException("Um ou mais dados já cadastrados para outro abrigo!");
        }
        repository.save(new Abrigo(dto));
    }

    public List<PetDto> listarPets(String idOuNome) {
        Abrigo abrigo = buscaAbrigo(idOuNome);

        return petRepository
                .findByAbrigo(abrigo)
                .stream()
                .map(PetDto::new)
                .toList();
    }

    public Abrigo buscaAbrigo(String idOuNome) {
        Optional<Abrigo> abrigo;
        try {
            Long id = Long.parseLong(idOuNome);
            abrigo = repository.findById(id);
        } catch (NumberFormatException e) {
            abrigo = repository.findByNome(idOuNome);
        }
        return abrigo.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }
}


