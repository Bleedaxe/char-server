package tpp.example.chatserver.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tpp.example.chatserver.BaseTestcontainersSetup;
import tpp.example.chatserver.entity.Message;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRepositoryTest extends BaseTestcontainersSetup {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    @Transactional
    void save_validEntity_shouldSaveItIntoDatabase() {
        String type = "test_type";
        String payload = "test_payload";

        Message message = getMessage(type, payload);

        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();

        assertThat(messages).isNotNull()
                .hasSize(1);

        Message fromDb = messages.iterator().next();

        assertThat(fromDb).isNotNull()
                .extracting(Message::getType, Message::getPayload)
                .containsExactly(type, payload);
    }

    @Test
    @Transactional
    void save_multipleEntities_shouldSaveThemIntoDatabase() {
        String type = "test_type";
        String payload = "test_payload";

        messageRepository.save(getMessage(type, payload));
        messageRepository.save(getMessage(type, payload));
        messageRepository.save(getMessage(type, payload));

        Iterable<Message> messages = messageRepository.findAll();

        assertThat(messages).isNotNull()
                .hasSize(3);
    }

    private Message getMessage(String type, String payload) {
        Message message = new Message();

        message.setType(type);
        message.setPayload(payload);
        message.setCreatedAt(LocalDateTime.now());

        return message;
    }
}