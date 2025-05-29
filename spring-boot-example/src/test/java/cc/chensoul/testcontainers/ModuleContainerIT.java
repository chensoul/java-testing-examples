package cc.chensoul.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class ModuleContainerIT {

  @Container
  static PostgreSQLContainer database = new PostgreSQLContainer<>("postgres:17")
    .withUsername("postgres")
    .withPassword("secret")
    .withInitScript("config/INIT.sql")
    .withDatabaseName("tescontainers");

  @Test
  void testPostgreSQLModule() {
    System.out.println(database.getJdbcUrl());
    System.out.println(database.getTestQueryString());
  }
}
