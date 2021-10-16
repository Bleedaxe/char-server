package tpp.example.chatserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpp.example.chatserver.exception.InvalidMessageTypeException;
import tpp.example.chatserver.service.ChatServerService;
import tpp.example.chatserver.service.MessageHandlerService;
import tpp.example.chatserver.web.model.MessageDto;

import java.util.List;

@Service
public class ChatServerServiceImpl implements ChatServerService {

  private final List<MessageHandlerService> messageHandlerServices;

  @Autowired
  public ChatServerServiceImpl(List<MessageHandlerService> messageHandlerServices) {
    this.messageHandlerServices = messageHandlerServices;
  }

  @Override
  public void handleMessage(MessageDto message, String type) {
    for (var messageHandlerService : messageHandlerServices) {
      boolean isMessageHandled = messageHandlerService.isMessageHandled(message, type);
      if (isMessageHandled) {
        return;
      }
    }

    throw new InvalidMessageTypeException(type);
  }
}
