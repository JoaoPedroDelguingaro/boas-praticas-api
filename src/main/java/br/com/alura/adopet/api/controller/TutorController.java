package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.TutorDto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.TutorDto.CadastraTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraTutorDto dto) {
        try {
            this.tutorService.cadastrar(dto);
            return ResponseEntity.ok().body("Tutor cadastrado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizarTutorDto dto) {
        try {
            this.tutorService.atualizar(dto);
            return ResponseEntity.ok().body("Tutor atualizado com sucesso!");
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
