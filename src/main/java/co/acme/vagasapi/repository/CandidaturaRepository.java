package co.acme.vagasapi.repository;

import co.acme.vagasapi.domain.Candidatura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "candidaturas", path = "candidaturas")
public interface CandidaturaRepository extends MongoRepository<Candidatura, String> {


    @Query("{ 'vaga.id' : ?0 }")
    Page<Candidatura> findByVagaId(@Param("vagaId") String vagaId, Pageable pageable);
}
