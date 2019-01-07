package co.acme.vagasapi.repository;

import co.acme.vagasapi.VagasApiApplicationTests;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.domain.Vaga;
import co.acme.vagasapi.fixtures.PessoaFixture;
import co.acme.vagasapi.fixtures.VagasFixture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class VagaRepositoryTest extends VagasApiApplicationTests {

    @Autowired
    private VagaRepository vagaRepository;


    @Test
    @Transactional
    public void saveTest(){
        Vaga vaga = vagaRepository.save(
                VagasFixture.vaga1
        );

        assertThat(vaga,hasProperty("id",is(not(nullValue()))));
    }

    @Test
    @Transactional
    public void findAll(){
        vagaRepository.save(
                VagasFixture.vaga1
        );

        vagaRepository.save(
                VagasFixture.vaga2
        );

        List<Vaga> list = vagaRepository.findAll();

        assertThat(list, hasSize(greaterThan(1)));
    }



}