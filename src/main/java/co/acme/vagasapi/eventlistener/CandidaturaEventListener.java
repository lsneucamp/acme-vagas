package co.acme.vagasapi.eventlistener;


import co.acme.vagasapi.domain.Candidatura;
import co.acme.vagasapi.service.CandidaturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CandidaturaEventListener extends AbstractMongoEventListener<Candidatura> {

    private final MongoOperations mongoOperations;
    private final CandidaturaService candidaturaService;

    @Autowired
    public CandidaturaEventListener(MongoOperations mongoOperations, CandidaturaService candidaturaService) {
        this.mongoOperations = mongoOperations;
        this.candidaturaService = candidaturaService;
    }

    /**
     * Antes de salvar, calcular a pontuação
     *
     * @param event
     */
    @Override
    public void onBeforeSave(BeforeSaveEvent<Candidatura> event) {
        if(event.getSource().getPontuacao()==null){
            candidaturaService.onBeforeSave(event.getSource());
            event.getDocument().append("pontuacao",event.getSource().getPontuacao());
        }
    }
}
