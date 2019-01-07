package co.acme.vagasapi.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "vagas")
public class Vaga implements Domain<String>{


    @Id
    private String id;
    @NotNull
    private String empresa;
    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    private String localizacao;
    @NotNull
    private Integer nivel;

}
