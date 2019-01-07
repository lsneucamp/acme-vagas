package co.acme.vagasapi.config;

import com.mongodb.Mongo;
import org.mongeez.Mongeez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
class Config  {

  @Autowired
  @Bean
  MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
    return new MongoTransactionManager(dbFactory);
  }

  @Bean
  @Autowired
  public Mongeez mongeez(Mongo mongo,MongoDbFactory dbFactory) {
    Mongeez mongeez = new Mongeez();
    mongeez.setFile(new ClassPathResource("/config/mongeez/master.xml"));
    mongeez.setMongo(mongo);
    mongeez.setDbName(dbFactory.getDb().getName());
    mongeez.process();
    return mongeez;
  }
}
