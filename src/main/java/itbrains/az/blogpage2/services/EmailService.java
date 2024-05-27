package itbrains.az.blogpage2.services;

public interface EmailService {

    void sendConfirmationEmail(String email,String token);
}
