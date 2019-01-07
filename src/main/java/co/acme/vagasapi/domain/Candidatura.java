package co.acme.vagasapi.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "candidaturas")
@CompoundIndexes({
        @CompoundIndex(name = "vaga_pessoa_idx", def = "{'vaga.id' : 1, 'pessoa.id': 1}")
})
public class Candidatura implements Domain<String>{

    @Id
    private String id;
    @NotNull
    @DBRef
    private Vaga vaga;
    @NotNull
    @DBRef
    private Pessoa pessoa;

    private Integer pontuacao;

}
