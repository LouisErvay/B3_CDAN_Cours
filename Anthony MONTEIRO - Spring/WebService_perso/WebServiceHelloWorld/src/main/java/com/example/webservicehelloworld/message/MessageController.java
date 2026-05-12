package com.example.webservicehelloworld.message;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public record CreateMessageRequest(String content) {}

    public record MessageDto(Long id, String content, Instant createdAt) {
        static MessageDto fromEntity(MessageEntity entity) {
            return new MessageDto(entity.getId(), entity.getContent(), entity.getCreatedAt());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto create(@RequestBody CreateMessageRequest request) {
        if (request == null || request.content() == null || request.content().isBlank()) {
            throw new IllegalArgumentException("content est obligatoire");
        }
        return MessageDto.fromEntity(messageService.addMessage(request.content().trim()));
    }

    @GetMapping("/last")
    public List<MessageDto> last10() {
        return messageService.last10Messages().stream().map(MessageDto::fromEntity).toList();
    }
}

