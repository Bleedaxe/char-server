package tpp.example.chatserver.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpp.example.chatserver.exception.InvalidMessageException;
import tpp.example.chatserver.repository.MessageRepository;
import tpp.example.chatserver.web.model.MessageDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class EmotionMessageHandlerServiceTest {

    private static final String VALID_TYPE = "send_emotion";

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private EmotionMessageHandlerService emotionMessageHandlerService;

    @Test
    void canHandleMessage_withValidType_shouldReturnTrue() {
        boolean result = emotionMessageHandlerService.canHandleMessage(VALID_TYPE);

        assertThat(result).isTrue();
    }

    @Test
    void canHandleMessage_withInvalidType_shouldReturnFalse() {
        String type = "test_type123";

        boolean result = emotionMessageHandlerService.canHandleMessage(type);

        assertThat(result).isFalse();
    }

    @Test
    void isMessageValid_withValidPayload_shouldReturnTrue() {
        MessageDto message = new MessageDto();
        message.setPayload(":)");

        boolean isMessageValid = emotionMessageHandlerService.isMessageValid(VALID_TYPE, message);

        assertThat(isMessageValid).isTrue();
    }

    @Test
    void isMessageValid_withInvalidPayload_shouldReturnFalse() {
        MessageDto message = new MessageDto();
        message.setPayload(":3");

        boolean isMessageValid = emotionMessageHandlerService.isMessageValid(VALID_TYPE, message);

        assertThat(isMessageValid).isFalse();
    }

    @Test
    void isMessageHandled_withValidParameters_shouldReturnTrue() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload("^_^");

        boolean messageHandled = emotionMessageHandlerService.isMessageHandled(messageDto, VALID_TYPE);

        assertThat(messageHandled).isTrue();
    }

    @Test
    void isMessageHandled_withInvalidType_shouldReturnFalse() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload(":(");
        String type = "test_123";

        boolean messageHandled = emotionMessageHandlerService.isMessageHandled(messageDto, type);

        assertThat(messageHandled).isFalse();
    }

    @Test
    void isMessageHandled_withInvalidMessage_shouldThrowInvalidMessageException() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload("<3");

        assertThatThrownBy(() -> emotionMessageHandlerService.isMessageHandled(messageDto, VALID_TYPE))
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage("Invalid message with type [send_emotion] and payload [<3]");
    }
}