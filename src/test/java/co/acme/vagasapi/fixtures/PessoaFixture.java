package co.acme.vagasapi.fixtures;

import co.acme.vagasapi.domain.Candidatura;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.domain.Vaga;

public class PessoaFixture {


    public static Pessoa pessoa1 = Pessoa
            .builder()
            .nome("Luciano Neucamp")
            .profissao("Eng. de Software")
            .nivel(3)
            .localizacao("B")

            .build();
    public static Pessoa pessoa2 = Pessoa
            .builder()
            .nome("Lucas de Souza")
            .profissao("Eng. de Software")
            .localizacao("A")
            .nivel(4)
            .build();

}