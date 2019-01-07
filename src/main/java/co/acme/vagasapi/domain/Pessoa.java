package co.acme.vagasapi.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "pessoas")
public class Pessoa implements Domain<String>{


    @Id
    private String id;
    @NotNull
    private String nome;
    @NotNull
    private String profissao;
    @NotNull
    private String localizacao;
    @NotNull
    private Integer nivel;

}
