package co.acme.vagasapi.config;

import co.acme.vagasapi.domain.Candidatura;
import co.acme.vagasapi.domain.Pessoa;
import co.acme.vagasapi.domain.Vaga;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class RestValidationConfiguration extends RepositoryRestConfigurerAdapter {

  @Bean
  @Primary
  Validator validator() {
    return new LocalValidatorFactoryBean();
  }

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(
      Candidatura.class,
      Vaga.class,
      Pessoa.class
    );

  }


}
