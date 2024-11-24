package com.server.pin.domain.email.service;

import com.server.pin.domain.email.exception.MailError;
import com.server.pin.global.exception.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "seohoon0707@gmail.com";
    private static int number;

    @Override
    public String sendSignUpEmailCheckMail(String email) {
        number = (int)(Math.random() * (90000)) + 100000;
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");

            String body = "";
            body += "<div style=\"width: 400px; margin: auto;\">";
            body += "<div style=\"border: 2px solid #ddd; border-radius: 8px; padding: 10px; padding-top: 1px; padding-bottom: 5px; text-align: center; background-color: #f9f9f9;\">";
            body += "<h2 style=\"font-family: Arial, sans-serif; color: #333;\">Verification Code</h2>";
            body += "<h3 style=\"font-family: Arial, sans-serif; color: #333;\">Enter the code below to sign up to Pin.</h3>";
            body += "<div style=\"border: 2px solid #ddd; border-radius: 8px; padding: 20px; text-align: center; background-color: #f9f9f9;\">";
            body += "<h1 style=\"font-size: 32px; color: #007bff; margin: 0;\">" + number + "</h1>";
            body += "</div>";
            body += "</div>";
            body += "</div>";


            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            throw new CustomException(MailError.MAIL_CREATE_FAIL);
        }


        javaMailSender.send(message);

        return String.valueOf(number);
    }
}

