package co.acme.vagasapi.repository;

import co.acme.vagasapi.domain.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pessoas", path = "pessoas")
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
}
