package com.personal.productcatalog.service;

import com.personal.productcatalog.exception.MailException;
import com.personal.productcatalog.model.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.SubmissionPublisher;

@Service
public class EmailService {

    private static final Logger LOG = LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public void sendAsync(Email email) {
        SubmissionPublisher<Email> publisher = new SubmissionPublisher<>();
        publisher.consume(this::send);
        publisher.submit(email);
        publisher.close();
    }

    public void send(Email email) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);

            helper.setTo(email.recipient());
            helper.setSubject(email.subject());
            helper.setText(email.body(), true);

            mailSender.send(mail);
            LOG.info("E-mail sent with success");
        } catch (MessagingException e) {
            LOG.error("Sent e-mail error", e);
            throw new MailException(e);
        }
    }
}
