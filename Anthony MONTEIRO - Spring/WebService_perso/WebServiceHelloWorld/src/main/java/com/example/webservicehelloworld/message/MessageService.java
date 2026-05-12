package com.example.webservicehelloworld.message;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public MessageEntity addMessage(String content) {
        var message = new MessageEntity(content);
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<MessageEntity> last10Messages() {
        return messageRepository.findTop10ByOrderByCreatedAtDesc();
    }
}

