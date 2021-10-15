package tpp.example.chatserver.service;

import tpp.example.chatserver.web.model.MessageDto;

public interface MessageHandlerService {

    boolean isMessageHandled(MessageDto message, String type);
}
