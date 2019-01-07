package co.acme.vagasapi.service;

import co.acme.vagasapi.domain.Candidatura;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.domain.Vaga;
import co.acme.vagasapi.repository.CandidaturaRepository;
import co.acme.vagasapi.repository.PessoaRepository;
import co.acme.vagasapi.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final PessoaRepository pessoaRepository;
    private final VagaRepository vagaRepository;
    private final DistanciaService distanciaService;

    @Autowired
    public CandidaturaService(CandidaturaRepository candidaturaRepository, PessoaRepository pessoaRepository, VagaRepository vagaRepository, DistanciaService distanciaService) {
        this.candidaturaRepository = candidaturaRepository;
        this.pessoaRepository = pessoaRepository;
        this.vagaRepository = vagaRepository;
        this.distanciaService = distanciaService;
    }


    public void onBeforeSave(Candidatura candidatura) {
        candidatura.setPontuacao(calcularPontuacao(candidatura.getPessoa(),candidatura.getVaga()));
    }

    /**
     * Calcula a pontuação pela distância e a experiência do usuário
     * [(100 - 25*(| vaga.nivel - pessoa.nivel |)) + pontuacaoDistancia] / 2
     *
     * @param pessoa
     * @param vaga
     * @return pontuação do candidato
     */
    private Integer calcularPontuacao(Pessoa pessoa, Vaga vaga) {
        Integer D = distanciaService.calcularPontuacaoDistancia(pessoa.getLocalizacao(), vaga.getLocalizacao());

        Integer N = (100 - 25 * (Math.abs(vaga.getNivel() - pessoa.getNivel())));

        Integer pontuacao = (N + D) / 2;

        return pontuacao;
    }

}
