package tpp.example.chatserver.service;

import tpp.example.chatserver.web.model.MessageDto;

public interface ChatServerService {

    void handleMessage(MessageDto message, String type);
}
