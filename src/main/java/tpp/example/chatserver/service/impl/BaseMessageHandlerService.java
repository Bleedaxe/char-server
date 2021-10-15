package tpp.example.chatserver.service.impl;

import tpp.example.chatserver.entity.Message;
import tpp.example.chatserver.exception.InvalidMessageException;
import tpp.example.chatserver.repository.MessageRepository;
import tpp.example.chatserver.service.MessageHandlerService;
import tpp.example.chatserver.web.model.MessageDto;

import java.time.LocalDateTime;

public abstract class BaseMessageHandlerService implements MessageHandlerService {

    protected final MessageRepository messageRepository;

    protected BaseMessageHandlerService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    protected abstract boolean canHandleMessage(String type);

    protected abstract boolean isMessageValid(String type, MessageDto message);

    @Override
    public boolean isMessageHandled(MessageDto message, String type) {
        if (!canHandleMessage(type)) {
            return false;
        }

        if (!isMessageValid(type, message)) {
            throw new InvalidMessageException(type, message.getPayload());
        }

        Message entity = createEntity(message, type);
        messageRepository.save(entity);

        return true;
    }

    private Message createEntity(MessageDto message, String type) {
        Message entity = new Message();

        entity.setPayload(message.getPayload());
        entity.setType(type);
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }
}
