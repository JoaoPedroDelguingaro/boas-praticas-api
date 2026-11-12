package br.com.alura.adopet.api.validacao;

import br.com.alura.adopet.api.dto.AdocaoDto.SolicitacaoAdocaoDto;

public interface ValidacaoSolicitacaoAdocao {
    void validar(SolicitacaoAdocaoDto dto);
}
