package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Message;
import com.eureka.smartrecruit.microservice.exception.ResourceNotFoundException;
import com.eureka.smartrecruit.respository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService extends BaseService {

    private final MessageRepository messageRepository;
    private final EmailService emailService;

    @Transactional
    public void create(final Message message) {
        message.setSender(getCurrentUser());
        messageRepository.save(message);
        emailService.send(message);
    }

    @Transactional
    public void delete(final Long id) {
        messageRepository.delete(id);
    }

    public Message findById(final Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find message with id: %s", id)));
    }
}
