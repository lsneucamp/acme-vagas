package co.acme.vagasapi.repository;

import co.acme.vagasapi.VagasApiApplicationTests;
import co.acme.vagasapi.domain.Candidatura;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.domain.Vaga;
import co.acme.vagasapi.fixtures.PessoaFixture;
import co.acme.vagasapi.fixtures.VagasFixture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CandidaturaRepositoryTest extends VagasApiApplicationTests {

    @Autowired
    private CandidaturaRepository candidaturaRepository;
    @Autowired
    private VagaRepository vagaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    @Transactional
    public void saveTest() {
        Pessoa pessoa1 = pessoaRepository.save(
                PessoaFixture.pessoa1
        );

        Vaga vaga1 = vagaRepository.save(
                VagasFixture.vaga1
        );

        Candidatura candidatura1 = Candidatura
                .builder()
                .pessoa(pessoa1)
                .vaga(vaga1)
                .build();


        Candidatura candidatura = candidaturaRepository.save(
                candidatura1
        );

        assertThat(candidatura, hasProperty("id", is(not(nullValue()))));
        assertThat(candidatura,hasProperty("pontuacao",is(87)));
    }

    @Test
    @Transactional
    public void findAll() {
        Pessoa pessoa1 = pessoaRepository.save(
                PessoaFixture.pessoa1
        );

        Pessoa pessoa2 = pessoaRepository.save(
                PessoaFixture.pessoa2
        );

        Vaga vaga1 = vagaRepository.save(
                VagasFixture.vaga1
        );


        Candidatura candidatura1 = Candidatura
                .builder()
                .pessoa(pessoa1)
                .vaga(vaga1)
                .build();

        Candidatura candidatura2 = Candidatura
                .builder()
                .pessoa(pessoa2)
                .vaga(vaga1)
                .build();

        candidaturaRepository.save(
                candidatura1
        );

        candidaturaRepository.save(
                candidatura2
        );

        List<Candidatura> list = candidaturaRepository.findAll();

        assertThat(list, hasSize(greaterThan(1)));
    }

    @Test
    public void findByVagaId(){
        Page<Candidatura> pageResult = candidaturaRepository.findByVagaId("5c2d4a728afb1841c8aaa538", Pageable.unpaged());
        assertThat(pageResult.getContent(), hasSize(greaterThan(1)));
    }


}