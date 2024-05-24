package itbrains.az.blogpage2.services.impl;

import itbrains.az.blogpage2.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendConfirmationEmail(String email,String token) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lia.johns@ethereal.email");
        message.setTo("lia.johns@ethereal.email");
        message.setSubject("Confirm email");
        String res = "http://localhost:9090/auth/confirm?email="+email+"&token="+token;
        message.setText(res);
        mailSender.send(message);
    }
}
