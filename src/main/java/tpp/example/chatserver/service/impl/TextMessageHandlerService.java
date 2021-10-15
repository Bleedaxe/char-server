package tpp.example.chatserver.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpp.example.chatserver.repository.MessageRepository;
import tpp.example.chatserver.web.model.MessageDto;

@Service
public class TextMessageHandlerService extends BaseMessageHandlerService {

    private static final String TEXT_TYPE = "send_text";
    private static final int PAYLOAD_MIN_LENGTH = 1;
    private static final int PAYLOAD_MAX_LENGTH = 160;

    @Autowired
    public TextMessageHandlerService(MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    protected boolean canHandleMessage(@NonNull String type) {
        return type.equalsIgnoreCase(TEXT_TYPE);
    }

    @Override
    protected boolean isMessageValid(String type, MessageDto message) {
        String payload = message.getPayload();
        return payload != null &&
                payload.length() >= PAYLOAD_MIN_LENGTH &&
                payload.length() <= PAYLOAD_MAX_LENGTH;
    }
}
