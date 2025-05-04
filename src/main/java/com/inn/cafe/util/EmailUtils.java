package com.inn.cafe.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Objects;
//import java.util.stream.Collectors;

@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        if (to == null || to.trim().isEmpty()) {
            throw new IllegalArgumentException("'to' address cannot be null or empty.");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("eng.moaznageb@gmail.com");
        message.setTo(to.trim());
        message.setSubject(subject);
        message.setText(text);

        if (list != null && !list.isEmpty()) {
            String[] ccArray = getCcArray(list);
            if (ccArray.length > 0) {
                message.setCc(ccArray);
            }
        }

        emailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList) {
        return ccList.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.trim().isEmpty())
                .map(String::trim)
                .toArray(String[]::new);
    }

    public void forgotMAil(String to, String subject, String password) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("eng.moaznageb@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password + "<br><a href=\"http://localhost:4200/\">Click here to login</a></p>";
        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }
}



/*
@Service
public class EmailUtils {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("eng.moaznageb@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if (list != null && !list.isEmpty())
            message.setCc(getCcArray(list));
        emailSender.send(message);
    }

    private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for (int i=0 ; i<ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }
}
*/