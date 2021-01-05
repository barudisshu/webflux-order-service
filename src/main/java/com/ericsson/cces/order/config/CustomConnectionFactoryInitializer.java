package com.ericsson.cces.order.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class CustomConnectionFactoryInitializer {

  @Bean
  public ConnectionFactoryInitializer initializer(
      @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    CompositeDatabasePopulator population = new CompositeDatabasePopulator();
    population.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
    initializer.setDatabasePopulator(population);
    return initializer;
  }
}
