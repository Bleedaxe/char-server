package tpp.example.chatserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("tpp.example.chatserver.repository")
public class JpaConfiguration {
}
