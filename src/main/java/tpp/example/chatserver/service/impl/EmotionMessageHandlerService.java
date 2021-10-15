package tpp.example.chatserver.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpp.example.chatserver.repository.MessageRepository;
import tpp.example.chatserver.web.model.MessageDto;

import java.util.regex.Pattern;

@Service
public class EmotionMessageHandlerService extends BaseMessageHandlerService {

    private static final String TEXT_TYPE = "send_emotion";
    private static final Pattern PAYLOAD_PATTER = Pattern.compile("^([^0-9]{2,10})$");

    @Autowired
    public EmotionMessageHandlerService(MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    protected boolean canHandleMessage(@NonNull String type) {
        return type.equalsIgnoreCase(TEXT_TYPE);
    }

    @Override
    protected boolean isMessageValid(String type, MessageDto message) {
        String payload = message.getPayload();
        return payload != null && PAYLOAD_PATTER.matcher(payload).matches();
    }
}
