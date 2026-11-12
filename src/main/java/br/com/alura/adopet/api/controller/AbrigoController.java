package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto.AbrigoDto;
import br.com.alura.adopet.api.dto.AbrigoDto.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto.CadastraPetDto;
import br.com.alura.adopet.api.dto.PetDto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<AbrigoDto>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraAbrigoDto dto) {
        try {
            this.abrigoService.cadastrar(dto);
            return ResponseEntity.ok().body("Abrigo cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
        try {
            List<PetDto> pets = this.abrigoService.listarPets(idOuNome);
            return ResponseEntity.ok(pets);
        } catch (ValidacaoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastraPetDto dto) {
        try {
            Abrigo abrigo = abrigoService.buscaAbrigo(idOuNome);
            this.petService.cadastrarPet(abrigo, dto);
            return ResponseEntity.ok().body("Pet cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
