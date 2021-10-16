package tpp.example.chatserver.repository;

import org.springframework.data.repository.CrudRepository;
import tpp.example.chatserver.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {}
