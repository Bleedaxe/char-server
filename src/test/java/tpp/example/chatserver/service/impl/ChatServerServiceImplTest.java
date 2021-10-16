package tpp.example.chatserver.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tpp.example.chatserver.exception.InvalidMessageTypeException;
import tpp.example.chatserver.service.MessageHandlerService;
import tpp.example.chatserver.web.model.MessageDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChatServerServiceImplTest {

  private static final String TYPE = "type_test123";
  private static final MessageDto MESSAGE = new MessageDto();

  @Test
  void handleMessage_withNoHandlers_shouldThrowInvalidMessageTypeException() {
    ChatServerServiceImpl chatServerService = new ChatServerServiceImpl(List.of());

    assertThatThrownBy(() -> chatServerService.handleMessage(MESSAGE, TYPE))
        .isInstanceOf(InvalidMessageTypeException.class)
        .hasMessage("Invalid message type [type_test123]");
  }

  @Test
  void handleMessage_withPresentType_shouldDoNothing() {
    MessageHandlerService mock = Mockito.mock(MessageHandlerService.class);
    when(mock.isMessageHandled(MESSAGE, TYPE)).thenReturn(true);
    ChatServerServiceImpl chatServerService = new ChatServerServiceImpl(List.of(mock));

    chatServerService.handleMessage(MESSAGE, TYPE);

    verify(mock).isMessageHandled(MESSAGE, TYPE);
  }
}
