package co.acme.vagasapi.fixtures;

import co.acme.vagasapi.domain.Vaga;

public class VagasFixture {


    public static Vaga vaga1 = Vaga
            .builder()
            .empresa("Acme co")
            .titulo("Engenheiro de Software")
            .localizacao("B")
            .nivel(4)
            .build();

    public static Vaga vaga2 = Vaga
            .builder()
            .empresa("Acme co")
            .titulo("Analista de Teste")
            .nivel(2)
            .localizacao("D")
            .build();
}