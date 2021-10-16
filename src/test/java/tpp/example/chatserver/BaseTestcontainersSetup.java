package tpp.example.chatserver;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@SpringBootTest
public abstract class BaseTestcontainersSetup {

  protected static MySQLContainer<?> mySqlContainer;

  @BeforeAll
  public static void setUpAll() {
    mySqlContainer =
        new MySQLContainer<>("mysql:5.7")
            .withUsername("root")
            .withPassword("root")
            .withDatabaseName("chat_server");

    mySqlContainer.start();
  }

  @DynamicPropertySource
  public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl);
    registry.add("spring.datasource.password", mySqlContainer::getPassword);
    registry.add("spring.datasource.username", mySqlContainer::getUsername);
  }
}
