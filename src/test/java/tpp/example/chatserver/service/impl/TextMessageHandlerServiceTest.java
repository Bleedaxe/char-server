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
class TextMessageHandlerServiceTest {

    private static final String VALID_TYPE = "send_text";

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private TextMessageHandlerService textMessageHandlerService;

    @Test
    void canHandleMessage_withValidType_shouldReturnTrue() {
        boolean result = textMessageHandlerService.canHandleMessage(VALID_TYPE);

        assertThat(result).isTrue();
    }

    @Test
    void canHandleMessage_withInvalidType_shouldReturnFalse() {
        String type = "test_type123";

        boolean result = textMessageHandlerService.canHandleMessage(type);

        assertThat(result).isFalse();
    }

    @Test
    void isMessageValid_withValidPayload_shouldReturnTrue() {
        MessageDto message = new MessageDto();
        message.setPayload("valid payload");

        boolean isMessageValid = textMessageHandlerService.isMessageValid(VALID_TYPE, message);

        assertThat(isMessageValid).isTrue();
    }

    @Test
    void isMessageValid_withInvalidPayload_shouldReturnFalse() {
        MessageDto message = new MessageDto();
        message.setPayload("");

        boolean isMessageValid = textMessageHandlerService.isMessageValid(VALID_TYPE, message);

        assertThat(isMessageValid).isFalse();
    }

    @Test
    void isMessageHandled_withValidParameters_shouldReturnTrue() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload("valid payload");

        boolean messageHandled = textMessageHandlerService.isMessageHandled(messageDto, VALID_TYPE);

        assertThat(messageHandled).isTrue();
    }

    @Test
    void isMessageHandled_withInvalidType_shouldReturnFalse() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload("valid payload");
        String type = "test_123";

        boolean messageHandled = textMessageHandlerService.isMessageHandled(messageDto, type);

        assertThat(messageHandled).isFalse();
    }

    @Test
    void isMessageHandled_withInvalidMessage_shouldThrowInvalidMessageException() {
        MessageDto messageDto = new MessageDto();
        messageDto.setPayload("");

        assertThatThrownBy(() -> textMessageHandlerService.isMessageHandled(messageDto, VALID_TYPE))
                .isInstanceOf(InvalidMessageException.class)
                .hasMessage("Invalid message with type [send_text] and payload []");
    }
}