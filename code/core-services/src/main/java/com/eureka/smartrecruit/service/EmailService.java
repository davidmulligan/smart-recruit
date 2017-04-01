package com.eureka.smartrecruit.service;

import com.eureka.smartrecruit.domain.Message;
import com.eureka.smartrecruit.domain.Notification;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${mail.systemEmail}")
    private String systemEmail;

    public void send(Message message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(new InternetAddress(message.getSender().getEmail(), message.getSender().getFullName()));
            messageHelper.setTo(new InternetAddress(message.getRecipient().getEmail(), message.getRecipient().getFullName()));
            messageHelper.setSubject(message.getType().getSubject());
            messageHelper.setText(templateEngine.process(message.getType().getEmailTemplate(),
                    new Context(Locale.getDefault(), ImmutableMap.of("message", message))), true);
        };
        mailSender.send(messagePreparator);
    }

    public void send(Notification notification) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(new InternetAddress(systemEmail));
            messageHelper.setTo(new InternetAddress(notification.getRecipient().getEmail(), notification.getRecipient().getFullName()));
            messageHelper.setSubject(notification.getType().getSubject());
            messageHelper.setText(templateEngine.process(notification.getType().getEmailTemplate(),
                    new Context(Locale.getDefault(), ImmutableMap.of("notification", notification))), true);
        };
        mailSender.send(messagePreparator);
    }
}
