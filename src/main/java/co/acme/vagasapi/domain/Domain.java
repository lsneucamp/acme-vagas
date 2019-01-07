package co.acme.vagasapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


public interface Domain<ID extends Serializable> extends Serializable {

    ID getId();
    void setId(ID id);

}
