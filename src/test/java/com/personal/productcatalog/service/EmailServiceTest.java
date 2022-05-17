package com.personal.productcatalog.service;

import com.personal.productcatalog.fixture.EmailFixture;
import com.personal.productcatalog.model.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.personal.productcatalog.utils.MessageUtils.NOT_NULL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;
    @Mock
    private JavaMailSender mailSender;
    @Mock
    private MimeMessage mail;

    @Test
    public void shouldSendEmail() {
        Email email = EmailFixture.get().buildRandom();

        given(mailSender.createMimeMessage()).willReturn(mail);

        emailService.send(email);

        then(mailSender).should().send(mail);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull() {
        NullPointerException ex = assertThrows(NullPointerException.class, () -> emailService.send(null));

        assertNotNull(ex);
        assertEquals(NOT_NULL, ex.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenFailedSentEmail() {
        Email email = EmailFixture.get().buildRandom();

        given(mailSender.createMimeMessage()).willReturn(mail);

        doThrow(new RuntimeException()).when(mailSender).send(mail);

        assertThrows(RuntimeException.class, () -> emailService.send(email));
    }
}
