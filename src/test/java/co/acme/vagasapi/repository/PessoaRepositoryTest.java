package co.acme.vagasapi.repository;

import co.acme.vagasapi.VagasApiApplicationTests;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.fixtures.PessoaFixture;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class PessoaRepositoryTest extends VagasApiApplicationTests {

    @Autowired
    private PessoaRepository pessoaRepository;


    @Test
    @Transactional
    public void saveTest(){
        Pessoa pessoa = pessoaRepository.save(
                PessoaFixture.pessoa1
        );

        assertThat(pessoa,hasProperty("id",is(not(nullValue()))));
    }

    @Test
    @Transactional
    public void findAll(){
        pessoaRepository.save(
                PessoaFixture.pessoa1
        );

        pessoaRepository.save(
                PessoaFixture.pessoa2
        );


        List<Pessoa> list = pessoaRepository.findAll();

        assertThat(list, hasSize(greaterThan(1)));
    }



}