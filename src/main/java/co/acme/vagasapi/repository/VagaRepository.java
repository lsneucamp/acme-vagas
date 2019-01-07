package co.acme.vagasapi.repository;

import co.acme.vagasapi.domain.Vaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "vagas", path = "vagas")
public interface VagaRepository extends MongoRepository<Vaga, String> {
}
