package Myhealth.myhealth.serviceMail;

public interface EmailSenderService {
    void SendEmail(String toEmail,String subject,String body);
}
